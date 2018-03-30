package com.example.employeeengagementsurvey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.employeeengagementsurvey.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeEngagementSurveyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeEngagementSurveyApplicationTests {

	@LocalServerPort
	int port;
	
	HttpHeaders httpHeader = new HttpHeaders();
	
	//RESt client for integration test
    TestRestTemplate restTemplate = new TestRestTemplate();

	@Before
	public void setheaders() {
		List<MediaType> media = new ArrayList<MediaType>();
		media.add(MediaType.APPLICATION_JSON);
		httpHeader.setAccept(media);
	}

	@Test
	public void testGetEmpEngSurveyQuestion() {
		HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeader);
		// GET request URL
		String url = "http://localhost:" + port + "/surveys/Survey1/questions/1";
		// Execute
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

		// compare actual with expected

		String expected = "{\"id\":1,\"description\":\"I have development opportunities at the Operating Company"
				+ " at which I work that help me learn and grow\",\"options\":[\"Not at all\",\"Occasionally\","
				+ "\"Sometimes\",\"Often\",\"Always\"]}";
		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testPostEmpEngSurveyQuestion() {
		// Post request
		String url = "http://localhost:" + port + "/surveys/Survey1/questions/";

		Question question = new Question(2,
				"Being associated with the Company provides me with the opportunity to fulfill my career goals.",
				Arrays.asList("Not at all", "Occasionally", "Sometimes", "Often", "Always"));
		// Create request with the question to add/post
		HttpEntity<Question> httpEntity = new HttpEntity<Question>(question, httpHeader);
		// execute
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		
		// validate status
		HttpStatus actualSttaus = response.getStatusCode();
		assertEquals(HttpStatus.CREATED, actualSttaus);

		// validate content location
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/surveys/Survey1/questions/"));

	}
}
