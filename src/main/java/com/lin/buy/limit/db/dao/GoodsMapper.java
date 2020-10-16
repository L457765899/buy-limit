package com.lin.buy.limit.db.dao;

import com.lin.buy.limit.db.entity.Goods;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.Map;

public interface GoodsMapper extends BaseMapper<Goods> {

    int cutStockCount(Map<String, Object> param);

    Goods selectByPrimaryKeyAndLock(int id);
}