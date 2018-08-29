package com.herval.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookmarksApplication {

    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(BookmarksApplication.class);
    	if (new StandardServletEnviromment().getActiveProfiles().length==0) {
    		app.setAdditionalProperties("dev", System.getProperty("user.name"));
    	}
    	
    	ConfigurableApplicationContext context = app.run(args);
    	
    	System.out.println();
    	System.out.println("########################");
    	System.out.println("#### Initializado: #####");
    	System.out.println("########################");
    	System.out.println(" go to: http://localhost:8080");
    }
}
