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

public class TrendingComponentPojo extends WCMUsePojo {

	private String sectionTitle;

	private Map<String, String> trendingMap;
	
	
	private List<Map<String, String>> smallTrendingList;

	private List<Map<String, String>> largeTrendingList;
	
	public List<Map<String, String>> getSmallTrendingList() {
		return smallTrendingList;
	}

	public List<Map<String, String>> getLargeTrendingList() {
		return largeTrendingList;
	}

	public String getSectionTitle() {
		return sectionTitle;
	}

	public Map<String, String> getTrendingMap() {
		return trendingMap;
	}
	
	@Override
	public void activate() throws Exception {
		ValueMap map = getProperties();
		if (map != null) {			
			sectionTitle = map.get("sectionTitle", String.class);
			String[] test = map.get("trendingSmallItemList", String[].class);
			String[] test1 = map.get("trendingLargeItemList", String[].class);
			smallTrendingList = new ArrayList<Map<String, String>>();
			largeTrendingList = new ArrayList<Map<String, String>>();
			JSONObject obj = null;
			if (test.length > 0) {
				trendingMap = new HashMap<String, String>();
				for (int i = 0; i < test.length; i++) {
					obj = new JSONObject(test[i]);
					if (obj != null) {
						trendingMap.put("title", obj.optString("title"));
						trendingMap.put("articleType", obj.optString("articleType"));
						trendingMap.put("ctaLink", obj.optString("ctaLink"));
						trendingMap.put("imagePath", obj.optString("imagePath"));
					}
					smallTrendingList.add(new HashMap(trendingMap));
				}
			}
			
			if (test1.length > 0) {
				trendingMap = new HashMap<String, String>();
				for (int i = 0; i < test1.length; i++) {
					obj = new JSONObject(test1[i]);
					if (obj != null) {
						trendingMap.put("title", obj.optString("title"));
						trendingMap.put("ctaLink", obj.optString("ctaLink"));
						trendingMap.put("imagePath", obj.optString("imagePath"));
						trendingMap.put("articleType", obj.optString("articleType"));
					}
					largeTrendingList.add(new HashMap(trendingMap));
				}
			}
		}
	}
}
