/****************************************************************************
*
* File: Survey.java                     
* Author: Pushpa Rajupeta
*
* Description: Survey model class.
*
* Modifications
*
*    DATE     PROGRAMMER   VER   MODIFICATION
* ----------  ----------- -----  ---------------------------------------------
* 
* 2018.03.25 prajupeta         CREATED
*
****************************************************************************/
package com.example.employeeengagementsurvey.model;

import java.util.List;

import javax.validation.constraints.Size;

public class Survey {
	
	@Size(min = 2, message = " id should be atleast 2 characters")
	private String id;
	@Size(min = 5, message = " title should be atleast 5 characters")
	private String title;
	private String description;
	private List<Question> questions;

	public Survey(String id, String title, String description, List<Question> questions) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.questions = questions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
