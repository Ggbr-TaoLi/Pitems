package com.phonemarket.service.impl;

import com.phonemarket.entity.Recommend;
import com.phonemarket.recommender.DigitalMallRecommender;
import com.phonemarket.service.IRecommenderService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IRecommenderServiceImpl implements IRecommenderService {
//    @Autowired
//    private RecomenderMapper rdMapper;
//    private IGoodsService goodsService;
    @Autowired
    private DigitalMallRecommender dgRecommender;

//    @Override
//    public List<Goods> queryGoodsByUser(int userID) {
//        return rdMapper.queryGoodsBySql(userID);
//    }

    @Override
    public List<Recommend> recommendGoodsBasedUser(int userID, int size) {
        List<Recommend> recommendedItems=null;
        try {
            recommendedItems=dgRecommender.userBasedRecommender(new Integer(userID).longValue(),size);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        return recommendedItems;

    }

    @Override
    public List<Recommend> recommendGoodsBasedItem(int userID, int size) {
        List<Recommend> recommendedItems=null;
        try {
            recommendedItems=dgRecommender.itemBasedRecommender(new Integer(userID).longValue(),size);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        return recommendedItems;

    }
}
