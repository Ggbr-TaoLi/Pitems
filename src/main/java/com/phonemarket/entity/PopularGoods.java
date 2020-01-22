package com.phonemarket.entity;

import java.io.Serializable;

public class PopularGoods implements Serializable {
    private Integer hotId;//热门商品id
    private Integer hotNums;//销售数量
    private String rank;//销量排名
//    private  String hotName;//商品名字
//    private String goodsImg;//商品图片

    public Integer getHotId() { return hotId; }

    public void setHotId(Integer hotId) { this.hotId = hotId; }

    public Integer getHotNums() {
        return hotNums;
    }

    public void setHotNums(Integer hotNums) {
        this.hotNums = hotNums;
    }

    public String getRank() { return rank; }

    public void setRank(String rank) { this.rank = rank; }


    public PopularGoods(){
        super();
    }


}
