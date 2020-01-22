package com.phonemarket.mapper;

import com.phonemarket.entity.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderDetailMapper {
	Integer addOrderDetail(OrderDetail orderDetail);
	OrderDetail findOrderDetailById(Integer detailId);
	Integer updateOrderDetail(OrderDetail orderDetail);
	List<OrderDetail> findOrderDetailByOrderId(String orderId);
	List<Map<String,Object>> findPopularProduct(Integer limit);
}
