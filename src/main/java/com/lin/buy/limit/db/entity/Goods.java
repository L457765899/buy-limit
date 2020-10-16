package com.lin.buy.limit.db.entity;

import javax.persistence.*;

public class Goods {
    /**
     * 商品id自增
     */
    @Id
    private Integer id;

    /**
     * 商品名字
     */
    private String name;

    /**
     * 库存数量
     */
    @Column(name = "stock_count")
    private Integer stockCount;

    /**
     * 特价商品数量
     */
    @Column(name = "special_count")
    private Integer specialCount;

    /**
     * 前几个用户享受优惠
     */
    @Column(name = "user_discount_count")
    private Integer userDiscountCount;

    /**
     * 已售数量
     */
    @Column(name = "saled_count")
    private Integer saledCount;

    /**
     * 原价价格
     */
    private Integer price;

    /**
     * 特价价格
     */
    @Column(name = "special_price")
    private Integer specialPrice;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 获取商品id自增
     *
     * @return id - 商品id自增
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置商品id自增
     *
     * @param id 商品id自增
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商品名字
     *
     * @return name - 商品名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名字
     *
     * @param name 商品名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取库存数量
     *
     * @return stock_count - 库存数量
     */
    public Integer getStockCount() {
        return stockCount;
    }

    /**
     * 设置库存数量
     *
     * @param stockCount 库存数量
     */
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
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
     * 获取前几个用户享受优惠
     *
     * @return user_discount_count - 前几个用户享受优惠
     */
    public Integer getUserDiscountCount() {
        return userDiscountCount;
    }

    /**
     * 设置前几个用户享受优惠
     *
     * @param userDiscountCount 前几个用户享受优惠
     */
    public void setUserDiscountCount(Integer userDiscountCount) {
        this.userDiscountCount = userDiscountCount;
    }

    /**
     * 获取已售数量
     *
     * @return saled_count - 已售数量
     */
    public Integer getSaledCount() {
        return saledCount;
    }

    /**
     * 设置已售数量
     *
     * @param saledCount 已售数量
     */
    public void setSaledCount(Integer saledCount) {
        this.saledCount = saledCount;
    }

    /**
     * 获取原价价格
     *
     * @return price - 原价价格
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 设置原价价格
     *
     * @param price 原价价格
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 获取特价价格
     *
     * @return special_price - 特价价格
     */
    public Integer getSpecialPrice() {
        return specialPrice;
    }

    /**
     * 设置特价价格
     *
     * @param specialPrice 特价价格
     */
    public void setSpecialPrice(Integer specialPrice) {
        this.specialPrice = specialPrice;
    }

    /**
     * 获取版本
     *
     * @return version - 版本
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本
     *
     * @param version 版本
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}