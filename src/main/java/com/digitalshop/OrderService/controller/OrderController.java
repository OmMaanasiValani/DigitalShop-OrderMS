package com.digitalshop.OrderService.controller;


import com.digitalshop.OrderService.model.OrderRequest;
import com.digitalshop.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/order")
public class OrderController {

        @Autowired
        OrderService orderService;

        @PostMapping
        public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest order){

            Long orderId = orderService.placeOrder(order);
            return new ResponseEntity<>(orderId, HttpStatus.OK);
        }
    

}
