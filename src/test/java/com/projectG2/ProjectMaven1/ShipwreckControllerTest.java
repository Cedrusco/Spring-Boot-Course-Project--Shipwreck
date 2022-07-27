package com.projectG2.ProjectMaven1;

//Testing using Mockito

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.projectG2.ProjectMaven1.controller.ShipwreckController;
import com.projectG2.ProjectMaven1.model.Shipwreck;
import com.projectG2.ProjectMaven1.repository.ShipwreckRepository;

public class ShipwreckControllerTest {
	
	
	// Instead of instantiating the ShipwreckController inside testShipwreckController(), I want to create a mock instance of it.
	  //Mockito provides an annotation that creates this object and injects it into the test.
	@InjectMocks //Mockito is now managing this object for me
	private ShipwreckController sc;
	
	// Next, I need to create a shipwreckRepository mock object so that when I call my controller I don't get that
	  // NullPointerException. I can use another Mockito annotation for that, and I'll declare another attribute that 
	 // looks like this:
	@Mock
	private ShipwreckRepository shipwreckRepository;
	
	// I now have both of my main Spring objects that are managed by the Mockito framework. To get Mockito to set
	  // that mocked repository automatically on the controller, I need to add a setup method that initializes all 
	  // of the mocked objects together when the test runs. That method will look like this:
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	// And I have a method called init that's going to run before every test case is fired, or run. And in my Before
	  // init method I'm going to call the MockitoAnnotations. initMocks, it's going to pass 'this' in. And when it passes 
	  // 'this' in it's going to look at the InjectMocks and the @Mocks attributes, and it's going to see if they need to 
	  // be pushed together. In this case, since the ShipwreckController has a shipwreckRepository on it, the Mockito 
	  // framework will go ahead and set that for me, much like the Spring container would via its dependency injection
	
	@Test
	public void testShipwreckGet() {
		// Now all that's left to do is I need to specify the mock behavior on my repository when I call my test.
		  // Since I'm not actually hitting a database I'm going to mock that call by adding the following lines in my test case:
		Shipwreck sw = new Shipwreck();
		sw.setId(1L);
		when(shipwreckRepository.getOne(1l)).thenReturn(sw);
		
		Shipwreck wreck = sc.get(1L);
		verify(shipwreckRepository).getOne(1l); //The other cool feature Mockito gives you is the ability to verify that your mocked or stubbed method calls are actually used during the testing of the code.  I can place a verify to check that this stubbed getOne method was actually called.  If for some reason the controller called the findOne method on the repository twice, the test would fail and you could figure out why the method was called twice and fix the issue.
		
		assertEquals(1l, wreck.getId().longValue());
	}
	
	// Above, I've created a new Shipwreck object, and set the Id to 1. The when method call allows us to provide the actual 
	  //mocking behavior. This is telling Mockito that when the findOne method is called on the repository, it should return
	  //the stubbed Shipwreck object. This now allows me to test my controller code as an actual unit test, and I don't need
	  //to be wired up to the database, or to Spring to make the test pass. So when I run the test now, my test status should 
	  //show green instead of red. The nice thing about this Spring STS JUnit plugin is I can simply re-run my test and it
	  //will pick up my changes. So now my test is passing because I've mocked the shipwreckRepository. 

	
	
	
//	@Test
//	public void testShipwreckGet() {
//		ShipwreckController sc = new ShipwreckController();
//		Shipwreck wreck = sc.get(1L);
//		assertEquals(1l, wreck.getId().longValue());
//	}
	//  the above test will fail. That's because we instantiated the ShipwreckController and didn't use Spring to inject it,
	  // so all of the injected objects on the ShipwreckController, like the shipwreckRepository, never
	  // got created properly by Spring. So this is the exact problem that mock frameworks were created
	  // for. By using Mockito, I can mock the shipwreckRepository so I can get my test to work.

}


