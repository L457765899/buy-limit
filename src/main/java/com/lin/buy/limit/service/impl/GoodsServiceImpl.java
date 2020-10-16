package com.lin.buy.limit.service.impl;

import com.lin.buy.limit.db.dao.GoodsMapper;
import com.lin.buy.limit.db.entity.Goods;
import com.lin.buy.limit.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> getList() {
        return goodsMapper.selectAll();
    }

    @Override
    public void add(Goods goods) {
        goods.setSaledCount(0);
        goodsMapper.insertSelective(goods);
    }

}
