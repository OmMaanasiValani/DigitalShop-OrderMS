package com.digitalshop.OrderService.exception;

import com.digitalshop.OrderService.external.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {



       @ExceptionHandler(CustomException.class)
        public ResponseEntity<ErrorResponse> handleProductException(CustomException exception){
            ErrorResponse msg = ErrorResponse.builder()
                    .errorMessage(exception.getMessage())
                    .errorCode(exception.getErrorCode())
                    .build();

            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);



        }
}
