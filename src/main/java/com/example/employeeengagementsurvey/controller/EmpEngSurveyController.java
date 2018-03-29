/****************************************************************************
 *
 * File: EmpEngSurveyController.java                     
 * Author: Pushpa Rajupeta
 *
 * Description: RESt controller class provides resources to create, retrieve delete a survey.
 * Resource                  Method            URI
 * Get all survey questions  GET           /surveys
 * Get survey questions      GET           /surveys/{surveyId}/questions
 * Get survey questions      GET           /surveys/{surveyId}/questions/{questionId}
 * Add survey questions      POST          /surveys/{surveyId}/questions
 * Delete survey question    DELETE       /surveys/{surveyId}/questions/{questionId}
 * 
 * TODO resources Add survey, DeleteAll
 *
 * Modifications
 *
 *    DATE     PROGRAMMER   VER   MODIFICATION
 * ----------  ----------- -----  ---------------------------------------------
 * 
 * 2018.03.25 prajupeta         CREATED
 *
 ****************************************************************************/
package com.example.employeeengagementsurvey.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.employeeengagementsurvey.exception.SurveyNotFoundException;
import com.example.employeeengagementsurvey.model.Question;
import com.example.employeeengagementsurvey.model.Survey;
import com.example.employeeengagementsurvey.service.EmpEngSurveyService;

@RestController
public class EmpEngSurveyController {

	@Autowired
	private EmpEngSurveyService empEngSurveyService;

	/**
	 * Gets all survey questions
	 * 
	 * @return
	 */
	@RequestMapping(path = "/surveys", method = RequestMethod.GET)
	public List<Survey> getAllSurveyQuestions() {

		List<Survey> surveyList = empEngSurveyService.retrieveAllSurvey();

		if (surveyList == null)
			throw new SurveyNotFoundException("Survey ");

		return surveyList;
	}

	/**
	 * Gets the survey questions of given surveyId.
	 * 
	 * @param surveyId
	 * @return List<Question>
	 */
	@RequestMapping(path = "/surveys/{surveyId}/questions", method = RequestMethod.GET)
	public List<Question> getSurveyQuestions(@PathVariable String surveyId) {

		List<Question> surveyQustionsList = empEngSurveyService.retrieveQuestions(surveyId);

		if (surveyQustionsList == null)
			throw new SurveyNotFoundException("Survey id -> " + surveyId);

		return surveyQustionsList;
	}

	/**
	 * Gets the survey question with given surveyId, question id
	 * 
	 * @param surveyId
	 *            String
	 * @param questionId
	 *            String
	 * @return Question
	 */
	@RequestMapping(path = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.GET)
	public Question getSurveyQuestion(@PathVariable String surveyId, @PathVariable int questionId) {

		Question question = empEngSurveyService.retrieveQuestion(surveyId, questionId);

		if (question == null)
			throw new SurveyNotFoundException("Survey id -> " + surveyId + ": Question id -> " + questionId);

		return question;
	}

	/**
	 * Adds survey question, POST request
	 * 
	 * @param surveyId
	 * @param Question
	 *            new survey question to add
	 * @return ResponseEntity CREATED status & Return the created URI
	 */
	@RequestMapping(path = "/surveys/{surveyId}/questions", method = RequestMethod.POST)
	public ResponseEntity<Void> addQuestionToSurvey(@PathVariable String surveyId,
			@Valid @RequestBody Question newQuestion) {

		Question question = empEngSurveyService.addQuestion(surveyId, newQuestion);

		if (question == null) {
			return ResponseEntity.noContent().build();
		}

		// Success - URI of the new resource in Response Header
		// Status - created
		// URI -> /surveys/{surveyId}/questions/{questionId}
		// question.getQuestionId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(question.getId())
				.toUri();

		// Status
		return ResponseEntity.created(location).build();
	}

	/**
	 * Deletes given question of given survey id.
	 * 
	 * @param surveyId
	 * @param questionId
	 */
	@RequestMapping(path = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.DELETE)
	public void deleteQuestionOfSurvey(@PathVariable String surveyId, @PathVariable int questionId) {

		Question question = empEngSurveyService.retrieveQuestion(surveyId, questionId);

		if (question == null)
			throw new SurveyNotFoundException("Survey id -> " + surveyId + ": Question id -> " + questionId);

	}
}
