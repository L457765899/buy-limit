package com.lin.buy.limit.db.entity;

import javax.persistence.*;

public class Buy {
    /**
     * 购买信息id
     */
    @Id
    private Integer id;

    /**
     * 关联用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 关联商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 用户已购买数量
     */
    private Integer count;

    /**
     * 获取购买信息id
     *
     * @return id - 购买信息id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置购买信息id
     *
     * @param id 购买信息id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取关联用户id
     *
     * @return user_id - 关联用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置关联用户id
     *
     * @param userId 关联用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取关联商品id
     *
     * @return goods_id - 关联商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置关联商品id
     *
     * @param goodsId 关联商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取用户已购买数量
     *
     * @return count - 用户已购买数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置用户已购买数量
     *
     * @param count 用户已购买数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}