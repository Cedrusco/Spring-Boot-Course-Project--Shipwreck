package com.projectG2.ProjectMaven1;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectG2.ProjectMaven1.model.Shipwreck;
import com.projectG2.ProjectMaven1.repository.ShipwreckRepository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

//for greaterThanOrEqual
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//Integration testing
//This test will be responsible for testing the ShipwreckRepository Spring Data object. 
// it should run successfully.
// pen up the Console tab and you should notice that it looks like our application started when we ran our test, and that's because
  //the app did start. Essentially each integration test will boot up your app, and this is one reason why integration tests can take 
  //a while if you have a really large app or you have a lot of tests. 


//@Runwith(SpringJUnit4ClassRunner.class) annotation says that this integration test should be run with the SpringJUnit4ClassRunner. 
  // This Spring-based test class runner is part of the Spring test tools that the test starter transitively included in the project.
//@SpringApplicationConfiguration(ProjectMaven1Application.class) annotation is a Spring Boot annotation and you supply your main 
  //class as the annotation parameter, which in our case is ProjectMaven1Application.class.

 // This tells Spring Boot how to configure and start your application. This essentially is like calling your static 
  //void main method when you start your app, except that now we're embedding that inside of the context of a unit test. 

@Runwith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ProjectMaven1Application.class)
public class ShipwreckRepositoryIntegrationTest {
	
	
	// Now when the test starts up the Spring context will load, and Spring will inject the full shipwreckRepository
	  //into the test, just like it would if it were running in a standard application.
	@Autowired
	private ShipwreckRepository shipwreckRepository;
	
	@Test
	public void testFindAll() {
		List<Shipwreck> wrecks = shipwreckRepository.findAll();
		assertThat(wrecks.size(), is(greaterThanOrEqual(0)));
	}

}
