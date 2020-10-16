package com.lin.buy.limit.service;

import com.lin.buy.limit.db.entity.Orders;

import java.util.Map;

public interface OrdersService {

    Map<String, Object> add1(Orders order);

    Map<String, Object> add2(Orders order);
}
