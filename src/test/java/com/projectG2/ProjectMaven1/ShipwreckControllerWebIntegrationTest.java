package com.projectG2.ProjectMaven1;

import org.springframework.boot.test.SpringApplicationConfiguration;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


//Web Integration Testing, the test should pass
// These tests are expensive to run, so they may be something that you want to set up only on a continuous build server,
  //and run them every time someone on your team checks something in or adds something to your code repository.

@Runwith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ProjectMaven1Application.class)
@WebIntegrationTest
public class ShipwreckControllerWebIntegrationTest {
	@Test
	public void testListAll() throws IOException {
		
		//  The first thing that I'm doing is I'm creating a restTemplate. RestTemplates are how we can programmatically call APIs, and in this case, I want to programmatically call the Shipwreck API. That API call is asking for all of the shipwrecks that the system knows about, and it holds the response. I did hardcode the server URL here, but this is something that you could, and probably should, move to the application properties, or configure your tests so that it points to your test environment, rather than your dev environment.
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/v1/shipwrecks", String.class);
		
		// Next, I run an assertion to make sure that we get a 200 OK back. If I don't get the 200 OK back, my test will fail immediately.
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		
		
		// I then convert the response into an actual JSON object
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode responsejson = objectMapper.readTree(response.getBody());
		
		
		// and perform some assertions on that to make sure that the return JSON is in a state that makes sense. Since I don't have any shipwrecks in my database currently, I'm going to check to make sure I get an empty array returned as my JSON payload.
		assertThat(responsejson.isMissingNode(), is(false));
		assertThat(responsejson.toString(), equalTo("[]")); //run the app, go to localhost:8080/h2 to check if the database has no shipwrecks, otherwise the test will fail
		
	

	}	
	
}
