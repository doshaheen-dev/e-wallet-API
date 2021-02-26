package com.tml.poc.Wallet.exception;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

/**
 * Global Exception Handler for Custom Exception handling
 * If Exception FOund then it will goes to Responce directly
 */
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

	/**
	 * Handled Exception into JSON
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(JsonEOFException.class)
	public ResponseEntity<?> JsonExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Exception while Parcing into JSON or From JSON
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<?> JsonParceExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * JWT Signature Exception handling
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<?> jwtSignatureExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * userNotFound Exception handling
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> userNotFoundExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Sevlet Call Exception
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ServletException.class)
	public ResponseEntity<?> servletExceptionExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Input Output Exception handling
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(IOException.class)
	public ResponseEntity<?> iOExceptionExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * JWT Expiration Exception
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<?> expiredJwtExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * In-Appropriate JWT Exception
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<?> malformedJwtExceptionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * JWT Exception Handled If it not Consumed in above exceptions
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<?> jwtExceptionExcpetionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Handled Invalid Input Exception
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<?> invalidinputExceptionHandler(Exception ex, WebRequest request) {
		DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Model Method Exception
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler( { MethodArgumentNotValidException.class } )
	public final ResponseEntity handleException( Exception e, WebRequest request )
	{
		if( e instanceof MethodArgumentNotValidException )
		{
			MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
			String parameterName = exception.getParameter().getParameterName();

//			for(int i=0;i<exception.get){
//
//			}
			//            return buildError(new DataException(GeneralConstants.EXCEPTION, "Invalid content length: field +"e))
			DataModelResponce errorDetails = new DataModelResponce(null, parameterName, null, false, 0);

			return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Global Exception if aException are not Consumed from above one
	 * @param ex
	 * @param request
	 * @return
	 */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
	  DataModelResponce errorDetails = new DataModelResponce(null, ex.getMessage(), null, false, 0);
      return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}