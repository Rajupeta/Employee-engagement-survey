/****************************************************************************
*
* File: Question.java                     
* Author: Pushpa Rajupeta
*
* Description: Question model class.
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

public class Question {

	private int id;
	
	@Size(min = 2, message = "description should have atleast 2 characters")
	private String description;
	
	@Size(min = 2, message = "options should have atleast 2 values")
	private List<String> options;

	//default constructor needed by com.fasterxml.jackson.databind.JsonMappingException
	public Question() {
	}

	public Question(int id, String description, List<String> options) {
		super();
		this.id = id;
		this.description = description;
		this.options = options;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return String.format("Question [id=%s, description=%s, options=%s]", id, description, options);
	}
}
