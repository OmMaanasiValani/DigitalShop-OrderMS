package com.digitalshop.OrderService.service;


import com.digitalshop.OrderService.model.OrderRequest;

public interface OrderService {
    Long placeOrder(OrderRequest order);

   // ProductResponse getProduct(long id);
}
