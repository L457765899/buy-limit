package com.lin.buy.limit.service.test;

import com.google.gson.Gson;
import com.lin.buy.limit.db.entity.Orders;
import com.lin.buy.limit.service.OrdersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersServiceTest {

    @Autowired
    private OrdersService ordersService;

    @Test
    public void add1() {
        Orders order = new Orders();
        order.setUserId(3);
        order.setGoodsId(3);
        order.setInfo("商品的快照信息");
        order.setAllCount(2);
        order.setSpecialCount(2);
        order.setOriginalCount(0);
        order.setAllPrice(order.getSpecialCount() * 6 + order.getOriginalCount() * 10);
        Map<String, Object> result = ordersService.add1(order);
        String json = new Gson().toJson(result);
        System.out.println(json);
    }

    @Test
    public void add2() {
        Orders order = new Orders();
        order.setUserId(4);
        order.setGoodsId(3);
        order.setInfo("商品的快照信息");
        order.setAllCount(2);
        order.setSpecialCount(2);
        order.setOriginalCount(0);
        order.setAllPrice(order.getSpecialCount() * 6 + order.getOriginalCount() * 10);
        Map<String, Object> result = ordersService.add2(order);
        String json = new Gson().toJson(result);
        System.out.println(json);
    }
}
