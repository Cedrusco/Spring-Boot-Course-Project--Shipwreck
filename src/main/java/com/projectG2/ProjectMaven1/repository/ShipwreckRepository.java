package com.projectG2.ProjectMaven1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectG2.ProjectMaven1.model.Shipwreck;

// So now that our entity is set, we need to go ahead and create a Shipwreck repository.
  //This is now up into our Spring Data JPA tier

// Since I want this to be a Spring Data repository, I'm going to go ahead and have it extend 
  //the JPA repository, which takes in the shipwreck entity, and the primary key type of Long.
public interface ShipwreckRepository extends JpaRepository<Shipwreck, Long>{

}

// Now that the persistence tier is built out of our application, all we need to do is replace the
  //ShipwreckStub in our controller class with the Spring Data ShipwreckRepository, and the app will
  //then complete from the browser all the way down to the database. 