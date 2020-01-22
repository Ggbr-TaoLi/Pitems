package com.phonemarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.phonemarket.entity.Goods;
import com.phonemarket.entity.Recommend;
import com.phonemarket.entity.Users;
import com.phonemarket.service.IGoodsService;
import com.phonemarket.service.IGuessService;
import com.phonemarket.service.IRecommenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


//注解开发
@Controller
@RequestMapping("/recommender")
public class RecommenderController {
    final private int RECOMMENDER_NUM = 8;    //推荐结果个数
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IRecommenderService rdService;
    @Autowired
    private IGuessService guessService;
    @RequestMapping("recommendGoodsBasedUser")
    @ResponseBody
    public JSONArray recommendGoodsBasedUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        List<Recommend> GoodsRBU = rdService.recommendGoodsBasedUser(user.getUserId(), RECOMMENDER_NUM);
//        List<Recommend> GoodsRBU = rdService.recommendGoodsBasedUser(4, RECOMMENDER_NUM);
        JSONArray arr = new JSONArray();
        for (Recommend rc : GoodsRBU) {
            Integer goodsId = rc.getItem().intValue();
//            if(user!=null) {
//                Guess guess = guessService.findGuessByUserId(user.getUserId(), goodsId);
//                Integer rs = guess.getFavorite();
//                if (rs < 0) { //过滤掉用户搜藏过的商品
                    JSONObject obj = new JSONObject();
                    float goodsValues = rc.getValue();
                    Goods g = goodsService.findById(goodsId);
                    obj.put("recommendId", goodsId);
                    obj.put("recommendName", g.getGoodsName());
                    obj.put("recommendImg", g.getGoodsImg());
                    obj.put("recommendDesc", g.getGoodsDesc());
                    obj.put("recommendPrice", g.getGoodsPrice());
                    obj.put("goodsValues", goodsValues);//偏好值
                    arr.add(obj);
//                }
//            }
            }
            return arr;
        }

    @RequestMapping("recommendGoodsBasedItem")
    @ResponseBody
    public JSONArray recommendGoodsBasedItem(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        List<Recommend> GoodsRBI=rdService.recommendGoodsBasedItem(user.getUserId(), RECOMMENDER_NUM);
//        List<Recommend> GoodsRBI=rdService.recommendGoodsBasedItem(4, RECOMMENDER_NUM);
        JSONArray arr=new JSONArray();
            for (Recommend rc : GoodsRBI) {
                Integer goodsId = rc.getItem().intValue();
//                if(user!=null) {
//                    Guess guess = guessService.findGuessByUserId(user.getUserId(), goodsId);
//                    Integer rs = guess.getFavorite();
//                    if (rs < 0) { //过滤掉用户搜藏过的商品
                        JSONObject obj = new JSONObject();
                        float goodsValues = rc.getValue();
                        Goods g = goodsService.findById(goodsId);
                        obj.put("recommendId", goodsId);
                        obj.put("recommendName", g.getGoodsName());
                        obj.put("recommendImg", g.getGoodsImg());
                        obj.put("recommendDesc", g.getGoodsDesc());
                        obj.put("recommendPrice", g.getGoodsPrice());
                        obj.put("goodsValues", goodsValues);//偏好值
                        arr.add(obj);
//                    }
//                }
        }
        return arr;
    }

}
