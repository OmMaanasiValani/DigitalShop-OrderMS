package com.digitalshop.OrderService.service;

import com.digitalshop.OrderService.entity.OrderEntity;
import com.digitalshop.OrderService.exception.CustomException;
import com.digitalshop.OrderService.external.feignclient.PaymentService;
import com.digitalshop.OrderService.external.feignclient.ProductService;
import com.digitalshop.OrderService.external.request.PaymentRequest;
import com.digitalshop.OrderService.model.OrderRequest;
import com.digitalshop.OrderService.model.OrderResponse;
import com.digitalshop.OrderService.repository.OrderRepo;
import lombok.extern.log4j.Log4j2;
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

    @Autowired
    PaymentService paymentService;

    @Override
    public Long placeOrder(OrderRequest order) {
        log.info("Placing order..");

        //Place Order and store in DB
        //Call ProductMS and update quantity
        //Initiate Payment by calling PaymentMS.
        //If payment->SUCCESS:return success else error.
        productService.reduceQuantity(order.getProductId(), order.getQuantity());

        OrderEntity orderEntity = OrderEntity.builder()
                .productId(order.getProductId())
                .amount(order.getTotalAmount())
                .orderStatus("CREATED")
                .orderDate(Instant.now())
                .quantity(order.getQuantity())
                .build();
        orderEntity = orderRepo.save(orderEntity);
        log.info("Calling Payment Service to complete the payment");
        PaymentRequest paymentRequest
                = PaymentRequest.builder()
                .orderId(order.getProductId())
                .paymentMode(order.getPaymentMode())
                .amount(order.getTotalAmount())
                .build();

        String orderStatus = null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done Successfully. Changing the Oder status to PLACED");
            orderStatus = "PLACED";
        } catch (Exception e) {
            log.error("Error occurred in payment. Changing order status to PAYMENT_FAILED");
            orderStatus = "PAYMENT_FAILED";
        }

        orderEntity.setOrderStatus(orderStatus);
        orderRepo.save(orderEntity);

        log.info("Order created with ID .." + orderEntity.getOrderId());
        return orderEntity.getOrderId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Get order details for Order Id : {}", orderId);

        OrderEntity order
                = orderRepo.findById(orderId)
                .orElseThrow(() -> new CustomException("Order not found for the order Id:" + orderId,
                        "NOT_FOUND",
                        404));
        OrderResponse orderResponse
                = OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .orderDate(order.getOrderDate()).build();

        return orderResponse;
    }
}
