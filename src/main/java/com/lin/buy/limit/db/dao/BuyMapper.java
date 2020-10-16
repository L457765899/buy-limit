package com.lin.buy.limit.db.dao;

import com.lin.buy.limit.db.entity.Buy;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.Map;

public interface BuyMapper extends BaseMapper<Buy> {

    int incrCount(Map<String, Object> param);
}