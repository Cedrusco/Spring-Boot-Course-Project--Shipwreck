package com.projectG2.ProjectMaven1.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PersistenceConfiguration {
	//@Bean tells spring that the return value of the method dataSource needs to be set up
		//and stored as a Spring bean in the Spring context
	// @ConfigurationProperties annotation tells the data source builder to use the connection and pulling properties located in 
		// the application.properties file where the properties begin with the spring.datasource prefix
		// I can keep all of the existing connection property definitions that were used with my auto-configuration
		// setup, and the Spring DataSource Bean will reuse those.
		// since i am going to add another dataSource to my application, spring will need to know which one
		// is the default DataSource that should be used if not defined. this is easy to flag by specifying @Primary annotation on the DataSource
	//@Primary tells spring this DataSource is the defaults one
	@Bean 
	@ConfigurationProperties(prefix="spring.datasource")
	@Primary 
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix="datasource.flyway") //this method is using another datasource, check application.properties
	public DataSource flywayDataSource() {
		return DataSourceBuilder.create().build();
	}
}
