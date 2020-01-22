package com.phonemarket.service;

import com.phonemarket.entity.Recommend;

import java.util.List;

public interface IRecommenderService {
    List<Recommend> recommendGoodsBasedUser(int userID, int size);
    List<Recommend> recommendGoodsBasedItem(int userID,int size);
}
