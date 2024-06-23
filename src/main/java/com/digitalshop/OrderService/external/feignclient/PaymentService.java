package com.digitalshop.OrderService.external.feignclient;

import com.digitalshop.OrderService.external.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENTSERVICE/payment" )
public interface PaymentService {

        @PostMapping
        public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);



}
