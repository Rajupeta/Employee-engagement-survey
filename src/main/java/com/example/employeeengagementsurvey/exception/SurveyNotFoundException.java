/****************************************************************************
 *
 * File: EmpEngSurveyService.java                     
 * Author: Pushpa Rajupeta
 *
 * Description: Runtime exception to indicate survey or question is not found.
 *
 * Modifications
 *
 *    DATE     PROGRAMMER   VER   MODIFICATION
 * ----------  ----------- -----  ---------------------------------------------
 * 
 * 2018.03.25 prajupeta         CREATED
 *
 ****************************************************************************/
package com.example.employeeengagementsurvey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SurveyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8092313477554713277L;

	public SurveyNotFoundException(String message) {
		super(message+" Not Found");
	}

}
