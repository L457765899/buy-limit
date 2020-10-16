package com.lin.buy.limit.db.entity;

import javax.persistence.*;

public class Orders {
    /**
     * 订单id自增
     */
    @Id
    private Integer id;

    /**
     * 关联用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 关联商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 订单快照信息
     */
    private String info;

    /**
     * 总购买数量all_count=special_count+original_count
     */
    @Column(name = "all_count")
    private Integer allCount;

    /**
     * 特价商品数量
     */
    @Column(name = "special_count")
    private Integer specialCount;

    /**
     * 原价商品数量
     */
    @Column(name = "original_count")
    private Integer originalCount;

    /**
     * 总价格
     */
    @Column(name = "all_price")
    private Integer allPrice;

    /**
     * 获取订单id自增
     *
     * @return id - 订单id自增
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置订单id自增
     *
     * @param id 订单id自增
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取关联用户id
     *
     * @return user_id - 关联用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置关联用户id
     *
     * @param userId 关联用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取关联商品id
     *
     * @return goods_id - 关联商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置关联商品id
     *
     * @param goodsId 关联商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取订单快照信息
     *
     * @return info - 订单快照信息
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置订单快照信息
     *
     * @param info 订单快照信息
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * 获取总购买数量all_count=special_count+original_count
     *
     * @return all_count - 总购买数量all_count=special_count+original_count
     */
    public Integer getAllCount() {
        return allCount;
    }

    /**
     * 设置总购买数量all_count=special_count+original_count
     *
     * @param allCount 总购买数量all_count=special_count+original_count
     */
    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

    /**
     * 获取特价商品数量
     *
     * @return special_count - 特价商品数量
     */
    public Integer getSpecialCount() {
        return specialCount;
    }

    /**
     * 设置特价商品数量
     *
     * @param specialCount 特价商品数量
     */
    public void setSpecialCount(Integer specialCount) {
        this.specialCount = specialCount;
    }

    /**
     * 获取原价商品数量
     *
     * @return original_count - 原价商品数量
     */
    public Integer getOriginalCount() {
        return originalCount;
    }

    /**
     * 设置原价商品数量
     *
     * @param originalCount 原价商品数量
     */
    public void setOriginalCount(Integer originalCount) {
        this.originalCount = originalCount;
    }

    /**
     * 获取总价格
     *
     * @return all_price - 总价格
     */
    public Integer getAllPrice() {
        return allPrice;
    }

    /**
     * 设置总价格
     *
     * @param allPrice 总价格
     */
    public void setAllPrice(Integer allPrice) {
        this.allPrice = allPrice;
    }
}