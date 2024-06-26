package com.digitalshop.OrderService.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private long orderId;

    private long productId;

    private long quantity;

    private Instant orderDate;

    private String orderStatus;

    private long amount;
}
