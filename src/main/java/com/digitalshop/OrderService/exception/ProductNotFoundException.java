package com.digitalshop.OrderService.exception;

import lombok.Data;

@Data
public class ProductNotFoundException extends RuntimeException{

    String errorCode;
   public ProductNotFoundException(String message,String errorCode){
       super(message);
       this.errorCode = errorCode;
   }
}
