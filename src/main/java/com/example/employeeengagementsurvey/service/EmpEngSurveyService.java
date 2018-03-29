/****************************************************************************
*
* File: EmpEngSurveyService.java                     
* Author: Pushpa Rajupeta
*
* Description: Service class provides methods to retrieve survey questions, add question and remove a question.
*
* Modifications
*
*    DATE     PROGRAMMER   VER   MODIFICATION
* ----------  ----------- -----  ---------------------------------------------
* 
* 2018.03.25 prajupeta         CREATED
*
****************************************************************************/
package com.example.employeeengagementsurvey.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.employeeengagementsurvey.model.Question;
import com.example.employeeengagementsurvey.model.Survey;

@Component
public class EmpEngSurveyService {

	private static List<Survey> empengSurveyList = new ArrayList<Survey>();

	// static initializer to populate initial data for test purpose
	// TODO
	// temporary solution till db support is provided
	static {
		List<String> options = Arrays.asList("Not at all", "Occasionally", "Sometimes", "Often", "Always");
		// Growth and Development
		List<Question> questionList = new ArrayList<>(Arrays.asList(new Question(1,
				"I have development opportunities at the Operating Company at which I work that help me learn and grow",
				options),
				new Question(2,
						"Being associated with the Company provides me with the opportunity to fulfill my career goals.",
						options)));

		empengSurveyList.add(new Survey("Survey1", "Growth and Dvelopment", "Questions related to employee development",
				questionList));

		// Innovation
		List<Question> questionList2 = new ArrayList<>(Arrays.asList(
				new Question(1, "My Operating Company is innovative and seeks new ideas", options),
				new Question(2, "My Operating Company rewards risk-taking in order to drive innovation", options)));
		empengSurveyList.add(new Survey("Survey2", "Innovation", "Questions related to innovation", questionList2));
	}

	public List<Survey> retrieveAllSurvey() {
		return empengSurveyList;
	}

	/**
	 * 
	 * @param surveyId
	 * @return
	 */
	public Survey retrieveSurvey(String surveyId) {
		for (Survey survey : empengSurveyList) {
			if (survey.getId().equals(surveyId)) {
				return survey;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param surveyId
	 * @return
	 */
	public List<Question> retrieveQuestions(String surveyId) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		return survey.getQuestions();
	}

	/**
	 * 
	 * @param surveyId
	 * @param questionId
	 * @return
	 */
	public Question retrieveQuestion(String surveyId, int questionId) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		for (Question question : survey.getQuestions()) {
			if (question.getId() == questionId) {
				return question;
			}
		}

		return null;
	}

	/**
	 * 
	 * @param surveyId
	 * @param question
	 * @return
	 */
	public Question addQuestion(String surveyId, Question question) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		synchronized (this) {
			List<Question> sList = survey.getQuestions();
			question.setId(sList.size() + 1);
			sList.add(question);
		}

		return question;
	}

	/**
	 * 
	 * @param surveyId
	 * @param question
	 * @return
	 */
	public boolean deleteQuestion(String surveyId, Question question) {
		Survey survey = retrieveSurvey(surveyId);
		return survey.getQuestions().remove(question);
	}
}
