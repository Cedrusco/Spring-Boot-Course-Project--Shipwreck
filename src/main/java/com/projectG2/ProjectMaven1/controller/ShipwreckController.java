package com.projectG2.ProjectMaven1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectG2.ProjectMaven1.model.Shipwreck;

@RestController //first annotation
@RequestMapping("api/v1/") //second annotation. "api/v1/" specifies the base URL that all end points will contain for this class. All end points in the angular app will start with api/v1/. so setting "api/v1/" in the class annotation will set that piece of the endpoint for us
public class ShipwreckController {
    //now we add a list endpoint
    //RequestMapping says that it will accept a GET request to the API v1/shipwrecks endpoint, the method-level RequestMapping
    // annotation appends its value to the class value annotation.
     @RequestMapping(value="shipwrecks", method= RequestMethod.GET)
    public List<Shipwreck> list(){
        return ShipwreckStub.list();
     }
}
