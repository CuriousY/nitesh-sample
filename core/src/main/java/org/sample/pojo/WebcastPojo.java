package org.sample.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.demo.service.IGreeter;

import com.adobe.cq.sightly.WCMUsePojo;

public class WebcastPojo extends WCMUsePojo {

	private String sectionTitle;
	
	private String viewAllText;
		
	private String viewAllCta;	

	private Map<String, String> webcastMap;

	private List<Map<String, String>> webcastList;	
	
	public String getViewAllText() {
		return viewAllText;
	}

	public String getViewAllCta() {
		return viewAllCta;
	}

	public Map<String, String> getWebcastMap() {
		return webcastMap;
	}

	public List<Map<String, String>> getWebcastList() {
		return webcastList;
	}

	public String getSectionTitle() {
		return sectionTitle;
	}
	
	@Override
	public void activate() throws Exception {
		ValueMap map = getProperties();
		if (map != null) {			
			sectionTitle = map.get("sectionTitle", String.class);
			viewAllText = map.get("viewAllText", String.class);
			viewAllCta = map.get("viewAllCta", String.class);
			String[] test = map.get("webcastList", String[].class);
			webcastList = new ArrayList<Map<String, String>>();
			JSONObject obj = null;
			if (test.length > 0) {
				webcastMap = new HashMap<String, String>();
				for (int i = 0; i < test.length; i++) {
					obj = new JSONObject(test[i]);
					if (obj != null) {
						webcastMap.put("title", obj.optString("title"));
						webcastMap.put("time", obj.optString("time"));
						webcastMap.put("ctaLink", obj.optString("ctaLink"));
						webcastMap.put("description", obj.optString("description"));
						webcastMap.put("registerText", obj.optString("registerText"));
					}
					webcastList.add(new HashMap(webcastMap));
				}
			}
		}
	}
}
