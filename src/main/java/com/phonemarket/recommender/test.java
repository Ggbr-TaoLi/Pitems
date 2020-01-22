package com.phonemarket.recommender;

import com.phonemarket.entity.Recommend;

import java.util.List;
//仅用于算法测试
public class test {
    public static void main(String[] args) throws Exception{
        DigitalMallRecommender dr = new DigitalMallRecommender();
        List<Recommend> GoodsRBU=dr.userBasedRecommender(4,4);
        DigitalMallRecommender ir = new DigitalMallRecommender();
        List<Recommend> GoodsRBI=ir.itemBasedRecommender(4,4);
        System.out.println(GoodsRBU.size());
//        for (int i = 0;i <GoodsRBU.size();i++) {
//            System.out.println(GoodsRBU.get(0).getItem());
//        }
//        for (int i = 0;i <GoodsRBI.size();i++) {
//            System.out.println(GoodsRBI.get(0).getItem());
//        }
        for (Recommend rc : GoodsRBI) {
            System.out.println(rc.getItem());
            System.out.println(rc.getValue());
        }
    }
}
