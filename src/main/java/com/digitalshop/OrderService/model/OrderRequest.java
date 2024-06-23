package com.digitalshop.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {


    private long productId;
    private long totalAmount;
    private long quantity;
    private Paymentmode paymentMode;
}
