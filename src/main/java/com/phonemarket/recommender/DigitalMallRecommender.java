package com.phonemarket.recommender;

//实现基于用户相似度的推荐引擎和基于内容相似度的推荐引擎。

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.phonemarket.entity.Recommend;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DigitalMallRecommender {

    final private int NEIGHBORHOOD_NUM = 8;//设置为八个近邻

        private DataModel getDataModel(){
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setUser("root");
            dataSource.setPassword("LT618618");
            dataSource.setDatabaseName("phonemarket2");

//          JDBCDataModel model = new MySQLJDBCDataModel(dataSource, "taste_preferences", "user_id", "item_id", "preference", null);
            JDBCDataModel jdbcDataModel = new MySQLJDBCDataModel(dataSource,"evaluate","eva_User","eva_Goods","eva_Level", null);
            DataModel dataModel = jdbcDataModel;
            return dataModel;
    }
    private List<Recommend> getRecommendedItemIDs(List<RecommendedItem> recommendations){
        List<Recommend> recommendItems = new ArrayList<>();
//        Map<String,Object> map = new HashMap<>();
//        ArrayList<long[][]> recommendItems = new ArrayList<long[][]>();
        for(int i = 0 ; i < recommendations.size() ; i++) {
            RecommendedItem recommendedItem=recommendations.get(i);
            Recommend rc = new Recommend();
            rc.setItem(recommendedItem.getItemID());
            rc.setValue(recommendedItem.getValue());
            recommendItems.add(rc);
//            Iterator<Recommend> it = recommendItems.iterator();
        }
        return  recommendItems;
    }
//基于用户的协同过滤推荐
    public List<Recommend> userBasedRecommender(long userID,int size) throws TasteException {
            DataModel dataModel = getDataModel();
        UserSimilarity similarity  = new EuclideanDistanceSimilarity(dataModel);
        NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, similarity, dataModel);
        Recommender recommender = new CachingRecommender(new GenericUserBasedRecommender(dataModel , neighbor, similarity));
        List<RecommendedItem> recommendations = recommender.recommend(userID, size);
//                for (RecommendedItem recommendation : recommendations) {
//            System.out.println(recommendation);
//        }
        return getRecommendedItemIDs(recommendations);
    }
//基于物品的协同过滤推荐
    public List<Recommend> itemBasedRecommender(long userID,int size) throws TasteException {
        DataModel dataModel = getDataModel();
            ItemSimilarity itemSimilarity = new UncenteredCosineSimilarity(dataModel);
            Recommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
            List<RecommendedItem> recommendations = recommender.recommend(userID, size);
//        for (RecommendedItem recommendation : recommendations) {
//            System.out.println(recommendation);
//        }
        return getRecommendedItemIDs(recommendations);
    }

}
