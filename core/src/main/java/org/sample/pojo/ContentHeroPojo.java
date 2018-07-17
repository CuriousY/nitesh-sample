package org.sample.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.demo.service.IGreeter;

import com.adobe.cq.sightly.WCMUsePojo;

public class ContentHeroPojo extends WCMUsePojo {

	private String heroHeading;

	private String heroLink;

	private String heroDescription;

	private String heroImagePath;

	private String strFeatureList;
	
	private Map<String, String> featureMap;

	private List<Map<String, String>> featureList;

	public Map<String, String> getFeatureMap() {
		return featureMap;
	}

	public String getHeroLink() {
		return heroLink;
	}

	public String getStrFeatureList() {
		return strFeatureList;
	}

	public void setHeroLink(String heroLink) {
		this.heroLink = heroLink;
	}

	public String getHeroHeading() {
		return heroHeading;
	}

	public String getHeroDescription() {
		return heroDescription;
	}

	public String getHeroImagePath() {
		return heroImagePath;
	}

	public List<Map<String, String>> getFeatureList() {
		return featureList;
	}

	@Override
	public void activate() throws Exception {
		ValueMap map = getProperties();
		if (map != null) {
			heroHeading = map.get("title", String.class);
			heroLink = map.get("ctaLink", String.class);
			heroDescription = map.get("description", String.class);
			heroImagePath = map.get("imagePath", String.class);
			String[] test = map.get("featureItemList", String[].class);
			featureList = new ArrayList<Map<String, String>>();
			JSONObject obj = null;
			featureMap = new HashMap<String, String>();
			if (test.length > 0) {
				for (int i = 0; i < test.length; i++) {
					obj = new JSONObject(test[i]);
					if (obj != null) {
						featureMap.put("title", obj.optString("title"));
						featureMap.put("ctaLink", obj.optString("ctaLink"));
					}
					featureList.add(new HashMap(featureMap));
				}
			}
		}
	}
}
