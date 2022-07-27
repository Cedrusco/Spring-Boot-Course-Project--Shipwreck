package com.projectG2.ProjectMaven1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectG2.ProjectMaven1.model.Shipwreck;
import com.projectG2.ProjectMaven1.repository.ShipwreckRepository;

// in this file, angular is communicating with spring boot mvc via our spring boot app.

@RestController //first annotation
@RequestMapping("api/v1/") //second annotation. "api/v1/" specifies the base URL that all end points will contain for this class. All end points in the angular app will start with api/v1/. so setting "api/v1/" in the class annotation will set that piece of the endpoint for us
public class ShipwreckController {
	
	// inject the newly-created ShipwreckRepository at the top of the ShipwreckController class
	  //by auto-wiring it in and defining the interface
	@Autowired
	private ShipwreckRepository shipwreckRepository;
	
    //now we add a list endpoint
    //RequestMapping says that it will accept a GET request to the API v1/shipwrecks endpoint, the method-level RequestMapping
    // annotation appends its value to the class value annotation.
    //first endpoint is GET
    @RequestMapping(value="shipwrecks", method= RequestMethod.GET)
    public List<Shipwreck> list(){
        //return ShipwreckStub.list();
    	return shipwreckRepository.findAll();
     }

     // the other endpoints are POST, GET, PUT, DELETE
    //all those Methods are pure spring MVC code. the part spring boot is doing for us, we cannot see it in hte code:
    // spring boot is handling the integration of spring mvc and sets up the Jackson JSON library so that when we set the shipwreck info across the http connection
    // spring boot and spring MVC are automatically marshling the json info into and out of the shipwreck java object

    /**
     * adds a shipwreck
     * @param shipwreck
     * @return
     */
     @RequestMapping(value="shipwrecks", method=RequestMethod.GET)
     public Shipwreck create(@RequestBody Shipwreck shipwreck){
         //return ShipwreckStub.create(shipwreck);
    	 return shipwreckRepository.saveAndFlush(shipwreck);
    	 //we are taking the shipwreck object passed in from the browser and save it and return the saved copy
     }

    /**
     * view a shipwreck
     * @param id
     * @return
     */
	@RequestMapping(value="shipwrecks/{id}", method= RequestMethod.GET)
    public Shipwreck get(@PathVariable Long id){
        //return ShipwreckStub.get(id);
    	//The GET method will now use the shipwreckRepository defined by id, and return that Shipwreck.
    	     //return shipwreckRepository.findOne(id); 
    	//findOne isn't working with Long or int, so:
    	return shipwreckRepository.getOne(id);
    }

    /**
     * updates a shipwreck
     * @param id
     * @param shipwreck
     * @return
     */
    @RequestMapping(value="shipwrecks/{id}", method= RequestMethod.PUT)
    public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck){
        //return ShipwreckStub.update(id, shipwreck);
    	//The update method is a little more involved since we need to pull the existing shipwreck
    	  //and copy the updated attributes into it and then resave the object.
		Shipwreck existingShipwreck = shipwreckRepository.getOne(id);
    	BeanUtils.copyProperties(shipwreck, existingShipwreck);
    	return shipwreckRepository.saveAndFlush(existingShipwreck);
    }

    /**
     *  deletes a shipwreck
     * @param id
     * @return
     */
    @RequestMapping(value="shipwrecks/{id}", method= RequestMethod.DELETE)
    public Shipwreck delete(@PathVariable Long id){
        //return ShipwreckStub.delete(id);
    	// look up the object, delete it, and return the existing one. We could make this simpler
    	  //by actually just passing in the id and returning void, we could delete the object without 
    	  //actually having to look it up and load it, but since our controller is already providing 
    	  //the shipwreck that was deleted on return, this is the logic that I've chosen to use to implement
    	  //this method. 
    	@SuppressWarnings("deprecation")
		Shipwreck existingShipwreck = shipwreckRepository.getOne(id);
    	shipwreckRepository.delete(existingShipwreck);
    	return existingShipwreck;
    }
    
}
