package com.tml.poc.Wallet.exception;

import java.util.Date; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.tml.poc.Wallet.models.reponse.DataModelResponce;

@ControllerAdvice
public class GlobalExceptionHandler {
//	
//	this.data = data;
//	this.message = message;
//	this.error = error;
//	this.success = success;
//	this.resCode = resCode;
	
	/**
	 * If Data not found 
	 * error code 404
	 * @param ex
	 * @param request
	 * @return
	 */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        DataModelResponce errorDetails = new DataModelResponce(null,
        		ex.getMessage(),
        		ex.getCause(),
        		false,0);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
//    	DataModelResponce errorDetails = new DataModelResponce(new Date(),
//        		ex.getMessage(),
//        		null,
//        		false,0);
//        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//    
    @ExceptionHandler(JsonEOFException.class)
    public ResponseEntity<?> JsonExcpetionHandler(Exception ex, WebRequest request) {
    	DataModelResponce errorDetails = new DataModelResponce(new Date(),
        		ex.getMessage(),
        		null,
        		false,0);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<?> JsonParceExcpetionHandler(Exception ex, WebRequest request) {
    	DataModelResponce errorDetails = new DataModelResponce(new Date(),
        		ex.getMessage(),
        		null,
        		false,0);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
//    
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}