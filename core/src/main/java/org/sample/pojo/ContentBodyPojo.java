package org.sample.pojo;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ValueMap;
import org.demo.service.IGreeter;

import com.adobe.cq.sightly.WCMUsePojo;

public class ContentBodyPojo extends WCMUsePojo {
	
	private String heading;
	

	public String getHeading() {
		return heading;
	}


	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		
		ValueMap map = getProperties();
		SlingHttpServletRequest request = getRequest();
		
		IGreeter greeter = getSlingScriptHelper().getService(IGreeter.class);
		
		heading = "Hello from WCMPOJO, Your title is " + map.get("jcr:titleHeading").toString() + greeter.sayHello();
		
		
		
				
		
	}

}
