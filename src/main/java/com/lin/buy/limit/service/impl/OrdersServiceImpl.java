package com.lin.buy.limit.service.impl;

import com.lin.buy.limit.db.dao.BuyMapper;
import com.lin.buy.limit.db.dao.GoodsMapper;
import com.lin.buy.limit.db.dao.OrdersMapper;
import com.lin.buy.limit.db.entity.Buy;
import com.lin.buy.limit.db.entity.Goods;
import com.lin.buy.limit.db.entity.Orders;
import com.lin.buy.limit.service.OrdersService;
import com.lin.buy.limit.util.RetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private BuyMapper buyMapper;

    /**
     * 新增订单，方案一，采用乐观锁加重试的方式。
     * 由于mysql默认隔离级别是可重复读，那么递归获取数据时都是一样的数据。所以设置隔离级别为读已提交
     * @param order
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Map<String, Object> add1(Orders order) {
        Goods goods = goodsMapper.selectByPrimaryKey(order.getGoodsId());
        if(goods == null) {
            return RetUtil.getRetValue(false, "商品不存在");
        }

        //验证用户购买的特价商品数量是否合理，在服务端判断之前，客户端应该在UI界面上限制用户选择特价商品的数量
        Buy buyRecord = new Buy();
        buyRecord.setUserId(order.getUserId());
        buyRecord.setGoodsId(order.getGoodsId());
        Buy buy = buyMapper.selectOne(buyRecord);//userId + goodsId上有唯一索引
        if(buy == null) {
            //用户首次购买超过限制
            if(order.getSpecialCount() > goods.getUserDiscountCount()) {
                return RetUtil.getRetValue(false,
                        "每个用户首次购买" + goods.getUserDiscountCount() + "个以内的商品才能享受特价");
            }
        }else{
            //用户再次购买，购买特价商品超过限制
            int count = goods.getUserDiscountCount() - buy.getCount();
            if(count <= 0 || order.getSpecialCount() > count) {
                return RetUtil.getRetValue(false,
                        "您已购买" + buy.getCount() + "个商品，不能再购买" + order.getSpecialCount() + "个特价商品");
            }
        }

        //检查库存是否足够，特价商品是否足够
        Map<String, Object> check = this.check(goods, order);
        if(check != null) {
            return check;
        }

        return this.doAdd(goods, order, buy);
    }

    /**
     * 新增订单，方案二，可以使用分布式锁的方式，就不需要重试
     * redis和zookeeper都有对应的分布式锁实现方式，我这里用的是mysql排它锁，达到的效果是一样的
     * 分布式锁可以加上mq消息队列，消息队列异步执行可以大幅减少锁的占用时间。
     * @param order
     * @return
     */
    @Transactional
    @Override
    public Map<String, Object> add2(Orders order) {
        //-----------这里的校验和上面一样start------------
        Goods goods = goodsMapper.selectByPrimaryKey(order.getGoodsId());
        if(goods == null) {
            return RetUtil.getRetValue(false, "商品不存在");
        }

        //验证用户购买的特价商品数量是否合理，在服务端判断之前，客户端应该在UI界面上限制用户选择特价商品的数量
        Buy buyRecord = new Buy();
        buyRecord.setUserId(order.getUserId());
        buyRecord.setGoodsId(order.getGoodsId());
        Buy buy = buyMapper.selectOne(buyRecord);//userId + goodsId上有唯一索引
        if(buy == null) {
            //用户首次购买超过限制
            if(order.getSpecialCount() > goods.getUserDiscountCount()) {
                return RetUtil.getRetValue(false,
                        "每个用户首次购买" + goods.getUserDiscountCount() + "个以内的商品才能享受特价");
            }
        }else{
            //用户再次购买，购买特价商品超过限制
            int count = goods.getUserDiscountCount() - buy.getCount();
            if(count <= 0 || order.getSpecialCount() > count) {
                return RetUtil.getRetValue(false,
                        "您已购买" + buy.getCount() + "个商品，不能再购买" + order.getSpecialCount() + "个特价商品");
            }
        }

        //检查库存是否足够，特价商品是否足够
        Map<String, Object> check = this.check(goods, order);
        if(check != null) {
            return check;
        }
        //-----------这里的校验和上面一样end------------


        //当前线程获取到锁，其它线程就会阻塞直到该事物完成
        //当前使用的是mysql的排它锁
        Goods lockGoods = goodsMapper.selectByPrimaryKeyAndLock(goods.getId());
        Map<String, Object> param = new HashMap<>();
        param.put("id", lockGoods.getId());
        param.put("allCount", order.getAllCount());
        param.put("specialCount", order.getSpecialCount());
        param.put("oldStockCount", lockGoods.getStockCount());
        param.put("oldSpecialCount", lockGoods.getSpecialCount());
        param.put("oldSaledCount", lockGoods.getSaledCount());
        param.put("version", lockGoods.getVersion());
        int cut = goodsMapper.cutStockCount(param);
        if(cut > 0) {
            //库存已经修改成功，生成订单及购买记录
            if(buy == null){
                buy = new Buy();
                buy.setUserId(order.getUserId());
                buy.setGoodsId(order.getGoodsId());
                buy.setCount(order.getAllCount());
                //如果一个用户有重复提交的情况（客户端限制好，网络不抖动，一般不会出现），userId + goodsId上的唯一索引能让第二次提交回滚
                buyMapper.insertSelective(buy);
            }else{
                Map<String, Object> incrParam = new HashMap<>();
                incrParam.put("id", buy.getId());
                incrParam.put("count", order.getAllCount());
                incrParam.put("oldCount", buy.getCount());
                int incrCount = buyMapper.incrCount(incrParam);
                //如果一个用户有重复提交的情况（客户端限制好，网络不抖动，一般不会出现），类似于乐观锁的机制，第二次提交不会修改成功
                if(incrCount == 0) {
                    throw new RuntimeException("有脏数据修改，本次操作回滚");
                }
            }
            ordersMapper.insertSelective(order);
            return RetUtil.getRetValue(true, "下单成功");
        }

        return RetUtil.getRetValue(true, "库存不足，请刷新重试");
    }

    /**
     * 修改库存
     * @param goods
     * @param order
     * @param buy
     * @return
     */
    private Map<String, Object> doAdd(Goods goods, Orders order, Buy buy) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", goods.getId());
        param.put("allCount", order.getAllCount());
        param.put("specialCount", order.getSpecialCount());
        param.put("oldStockCount", goods.getStockCount());
        param.put("oldSpecialCount", goods.getSpecialCount());
        param.put("oldSaledCount", goods.getSaledCount());
        param.put("version", goods.getVersion());
        int cut = goodsMapper.cutStockCount(param);
        if(cut > 0) {
            //库存已经修改成功，生成订单及购买记录
            if(buy == null){
                buy = new Buy();
                buy.setUserId(order.getUserId());
                buy.setGoodsId(order.getGoodsId());
                buy.setCount(order.getAllCount());
                //如果一个用户有重复提交的情况（客户端限制好，网络不抖动，一般不会出现），userId + goodsId上的唯一索引能让第二次提交回滚
                buyMapper.insertSelective(buy);
            }else{
                Map<String, Object> incrParam = new HashMap<>();
                incrParam.put("id", buy.getId());
                incrParam.put("count", order.getAllCount());
                incrParam.put("oldCount", buy.getCount());
                int incrCount = buyMapper.incrCount(incrParam);
                //如果一个用户有重复提交的情况（客户端限制好，网络不抖动，一般不会出现），类似于乐观锁的机制，第二次提交不会修改成功
                if(incrCount == 0) {
                    throw new RuntimeException("有脏数据修改，本次操作回滚");
                }
            }
            ordersMapper.insertSelective(order);
            return RetUtil.getRetValue(true, "下单成功");
        }else{
            //隔离级别为读已提交，所以可以读取已提交的数据
            Goods newGoods = goodsMapper.selectByPrimaryKey(order.getGoodsId());
            //检查库存是否足够，特价商品是否足够
            Map<String, Object> check = this.check(newGoods, order);
            if(check != null) {
                return check;
            }
            return this.doAdd(newGoods, order, buy);
        }
    }

    /**
     * 检查库存是否足够
     * @param goods
     * @param order
     * @return
     */
    private Map<String, Object> check(Goods goods, Orders order) {
        if(order.getAllCount() > goods.getStockCount()) {
            return RetUtil.getRetValue(false, "商品库存不足，请刷新重试");
        }
        if(order.getSpecialCount() > goods.getSpecialCount()) {
            return RetUtil.getRetValue(false, "特价商品数量不足，请刷新重试");
        }
        return null;
    }
}
