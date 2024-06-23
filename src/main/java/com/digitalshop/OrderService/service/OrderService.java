package com.digitalshop.OrderService.service;


import com.digitalshop.OrderService.model.OrderRequest;
import com.digitalshop.OrderService.model.OrderResponse;

public interface OrderService {
    Long placeOrder(OrderRequest order);

    OrderResponse getOrderDetails(long orderId);

}
