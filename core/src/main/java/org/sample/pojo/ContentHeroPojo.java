package org.sample.pojo;

import java.util.HashMap;
import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ValueMap;
import org.demo.service.IGreeter;

import com.adobe.cq.sightly.WCMUsePojo;

public class ContentHeroPojo extends WCMUsePojo {

	private String heroHeading;

	private String heroLink;

	private String heroDescription;

	private String heroImagePath;

	private List<HashMap> featureList;

	public String getHeroLink() {
		return heroLink;
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
		}

	}

}
