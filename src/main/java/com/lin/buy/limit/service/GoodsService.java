package com.lin.buy.limit.service;

import com.lin.buy.limit.db.entity.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> getList();

    void add(Goods goods);

}
