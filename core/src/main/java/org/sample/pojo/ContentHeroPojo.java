package org.sample.pojo;

import java.util.HashMap;
import java.util.List;

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

	private List<HashMap> featureList;

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

	public List<HashMap> getFeatureList() {
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
			JSONObject obj = null;
			if (test.length > 0) {
				for (int i = 0; i < test.length; i++) {
					obj = new JSONObject(test[i]);
				}
			}
			strFeatureList = obj.getString("ctaLink");

		}

	}

}
