package com.lin.buy.limit.service.test;

import com.google.gson.Gson;
import com.lin.buy.limit.db.entity.Goods;
import com.lin.buy.limit.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTest {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void add() {
        Goods goods = new Goods();
        goods.setName("测试商品B");
        goods.setStockCount(100);
        goods.setSpecialCount(10);
        goods.setUserDiscountCount(3);
        goods.setPrice(10);
        goods.setSpecialPrice(6);
        goodsService.add(goods);
        System.out.println("商品添加成功");
    }

    @Test
    public void getList(){
        List<Goods> list = goodsService.getList();
        String json = new Gson().toJson(list);
        System.out.println(json);
    }
}
