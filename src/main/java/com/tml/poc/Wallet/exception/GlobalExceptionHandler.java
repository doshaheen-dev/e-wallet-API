package com.tml.poc.Wallet.exception;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.tml.poc.Wallet.models.reponse.DataModelResponce;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

@ControllerAdvice
public class GlobalExceptionHandler {
//	
//	this.data = data;
//	this.message = message;
//	this.error = error;
//	this.success = success;
//	this.resCode = resCode;

	/**
	 * If Data not found error code 404
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), ex.getCause(), false, 0);
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
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<?> JsonParceExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<?> jwtSignatureExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> userNotFoundExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ServletException.class)
	public ResponseEntity<?> servletExceptionExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<?> iOExceptionExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<?> expiredJwtExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<?> malformedJwtExceptionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(JwtException.class)
	public ResponseEntity<?> jwtExceptionExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<?> invalidinputExceptionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.OK);
	}

//  @ExceptionHandler(Exception.class)
//  public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
//      errorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
//      return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//  }

}