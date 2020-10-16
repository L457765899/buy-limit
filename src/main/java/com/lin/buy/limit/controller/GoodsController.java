package com.lin.buy.limit.controller;


import com.lin.buy.limit.db.entity.Goods;
import com.lin.buy.limit.service.GoodsService;
import com.lin.buy.limit.util.RetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 商品列表
     * @return
     */
    @GetMapping("/getList.json")
    public Map<String, Object> getList() {
        return RetUtil.getRetValue(goodsService.getList());
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @PostMapping("/add.json")
    public Map<String, Object> add(Goods goods) {
        goodsService.add(goods);
        return RetUtil.getRetValue(true);
    }
}
