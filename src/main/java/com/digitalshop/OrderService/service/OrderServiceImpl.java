package com.digitalshop.OrderService.service;

import com.digitalshop.OrderService.entity.OrderEntity;
import com.digitalshop.OrderService.external.feignclient.ProductService;
import com.digitalshop.OrderService.model.OrderRequest;
import com.digitalshop.OrderService.repository.OrderRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    ProductService productService;
    @Override
    public Long placeOrder(OrderRequest order) {
        log.info("Placing order..");

        //Place Order and store in DB
        //Call ProductMS and update quantity
        //Initiate Payment by calling PaymentMS.
        //If payment->SUCCESS:return success else error.
        productService.reduceQuantity(order.getProductId(),order.getQuantity());

        OrderEntity orderEntity = OrderEntity.builder()
                .productId(order.getProductId())
                .amount(order.getTotalAmount())
                .orderStatus("CREATED")
                .orderDate(Instant.now())
                .quantity(order.getQuantity())
                .build();
        orderEntity = orderRepo.save(orderEntity);
        log.info("Order created with ID .."+orderEntity.getOrderId());
        return orderEntity.getOrderId();
    }


}
