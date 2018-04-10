package org.sample.model;

import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.demo.service.IGreeter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables=Resource.class)
public class ContentBodyModel {
	
	@Inject
	IGreeter greeter;
	
	private static final Logger log = LoggerFactory.getLogger(ContentBodyModel.class);
	
	@Inject @Named("jcr:titleHeading") @Default(values="No title fetched")
	private String heading;

	public String getHeading() {
		return heading;
	}
	
	@PostConstruct
	protected void init(){		
		String greet =  greeter != null ? greeter.sayHello() : "default";
		
		log.info("initmodel");
		heading = " Hello from Slingmodel, Your title is " + heading + " " + greet;
		
	}

}
