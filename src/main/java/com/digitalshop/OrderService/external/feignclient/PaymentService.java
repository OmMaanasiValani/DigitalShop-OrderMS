package com.digitalshop.OrderService.external.feignclient;

import com.digitalshop.OrderService.exception.CustomException;
import com.digitalshop.OrderService.external.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@CircuitBreaker(name="external", fallbackMethod = "fallback")
@FeignClient(name = "PAYMENTSERVICE/payment" )
public interface PaymentService {

        @PostMapping
        public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);


        default ResponseEntity<Long> fallback(Exception e) {
                throw new CustomException("Payment Service is not available",
                        "UNAVAILABLE",
                        500);
        }
}
