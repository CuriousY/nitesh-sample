package org.sample.pojo;

import org.apache.sling.api.resource.ValueMap;

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
		
		heading = "Hello from WCMPOJO, Your title is " + map.get("jcr:titleHeading").toString();
		
	}

}
