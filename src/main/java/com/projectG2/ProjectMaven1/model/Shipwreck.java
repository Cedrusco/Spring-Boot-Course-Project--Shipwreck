package com.projectG2.ProjectMaven1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//So even though we were using this as a DTO object in the past, or essentially just a transfer object, 
  //I'm going to go ahead and convert this into an actual JPA entity, so the first thing I need to do is 
  //simply add that annotation at the top, which is called @Entity.
// next, I need to tell JPA what attribute of this class is the primary key, and how the primary
  //key gets generated. So above the Long id I'm going to go ahead and just add the @Id and 
  //the @GeneratedValue annotation. The generated value is saying that the database is going to go
  //ahead and take care of incrementing our primary key value for us, so we don't need to pass in a 
  //sequence value, or some other way of generating the primary key. We'll just rely on the database engine
  //for that.
// There's a lot of other JPA entity information that we could add, but since we created our Shipwreck 
  //table to kind of match these attributes, I don't really need to add the @ column, or any of the other 
  //data associated with that because the table matches our Java object pretty clearly. 
@Entity
public class Shipwreck {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String name;
	String description;
	String condition;
	Integer depth;
	Double latitude;
	Double longitude;
	Integer yearDiscovered;

	public Shipwreck() { }

	public Shipwreck(Long id, String name, String description, String condition, Integer depth, Double latitude, Double longitude, Integer yearDiscovered) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.condition = condition;
		this.depth = depth;
		this.latitude = latitude;
		this.longitude = longitude;
		this.yearDiscovered = yearDiscovered;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getYearDiscovered() {
		return yearDiscovered;
	}

	public void setYearDiscovered(Integer yearDiscovered) {
		this.yearDiscovered = yearDiscovered;
	}
}
