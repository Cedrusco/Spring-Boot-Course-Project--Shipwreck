package com.projectG2.ProjectMaven1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.projectG2.ProjectMaven1.controller.HomeController;

@SpringBootTest
public   class ProjectMaven1ApplicationTests {

	@Test //@Test annotation is used to tell java this is a test
	void testApp() {
		HomeController hc = new HomeController(); //instantiating the home controller
		String result = hc.home();
		assertEquals(result, "Das Boot, reporting for duty!");
		
	}
	

}
