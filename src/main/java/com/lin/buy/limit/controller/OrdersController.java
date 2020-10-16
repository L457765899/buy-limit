package com.lin.buy.limit.controller;

import com.lin.buy.limit.db.entity.Orders;
import com.lin.buy.limit.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 新增订单
     * @param order
     * @return
     */
    @PostMapping("/add.json")
    public Map<String, Object> add(Orders order) {
        return ordersService.add1(order);
    }
}
