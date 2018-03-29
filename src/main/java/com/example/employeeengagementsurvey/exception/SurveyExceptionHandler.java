/****************************************************************************
 *
 * File: SurveyExceptionHandler.java                     
 * Author: Pushpa Rajupeta
 *
 * Description: Customized error handling to provide customized response.
 * If survey is not found then 404 with survey/ question id will be presented.
 * Internal server error for any other error case.
 *
 * Modifications
 *
 *    DATE     PROGRAMMER   VER   MODIFICATION
 * ----------  ----------- -----  ---------------------------------------------
 * 
 * 2018.03.28 prajupeta         CREATED
 *
 ****************************************************************************/
package com.example.employeeengagementsurvey.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class SurveyExceptionHandler  extends ResponseEntityExceptionHandler {
	/**
	 * 
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(SurveyNotFoundException.class)
	public final ResponseEntity<Object> handleSurveyNotFoundException(SurveyNotFoundException exception, WebRequest request){
		ExceptionResponse eRes = new ExceptionResponse(new Date(), exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(eRes,HttpStatus.NOT_FOUND);
	}
	/**
	 * 
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleSurveyNotFoundException(Exception exception, WebRequest request){
		ExceptionResponse eRes = new ExceptionResponse(new Date(), exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(eRes,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	

}
