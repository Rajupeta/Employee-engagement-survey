# Employee-engagement-survey

This project provides following rest services. 

Resource                  Method            URI
 * Get all survey questions  GET           /surveys
 * Get survey questions      GET           /surveys/{surveyId}/questions
 * Get survey questions      GET           /surveys/{surveyId}/questions/{questionId}
 * Add survey questions      POST          /surveys/{surveyId}/questions
 * Delete survey question    DELETE       /surveys/{surveyId}/questions/{questionId}
 
 Provided intial survey questions( added in a List, no db support yet).
 http://localhost:8080/surveys/
 http://localhost:8080/surveys/Survey1/questions/
 
 To run , clone the project and import into eclipse (File -> Import -> Existing Maven Project -> pom file location)
 
 
