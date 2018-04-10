package org.demo.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.felix.scr.annotations.References;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(service=Servlet.class,property={Constants.SERVICE_DESCRIPTION + "=QueryBuilderDemo servlet",
										   "sling.servlet.methods="+HttpConstants.METHOD_GET,
										   "sling.servlet.paths="+"/bin/server/queryBuilderDemo"})

public class QueryBuilderDemoServlet extends SlingSafeMethodsServlet {
	
	@Reference
	QueryBuilder querybuilder;
	private static final long serialVersionUID = 1L;
	String fullTextSearch="";

	
	private static final Logger log = LoggerFactory.getLogger(QueryBuilderDemoServlet.class);
	
	//Serves GET Request
	public void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws IOException{		
		
		Session session = request.getResourceResolver().adaptTo(Session.class);
		String resultString = "";
		
//		log.info("SEssion is : " + session + " query byd " + querybuilder);
		Map<String,String> queryMap = new HashMap<String,String>();	
		queryMap.put("path","/content");
		queryMap.put("1_property","sling:resourceType");
		queryMap.put("1_property.value","foundation/components/text");
		queryMap.put("1_property.operation","like");		
		queryMap.put("orderby","path");	
		
		Query query = querybuilder.createQuery(PredicateGroup.create(queryMap),session);
		
		query.setStart(0);
		query.setHitsPerPage(10);
		
		try{
					
		SearchResult result = query.getResult();
		
		Long totalMatches = result.getTotalMatches();
		
		if(result != null){
			
			for(Hit hit : result.getHits()){
				String path = hit.getPath();
				resultString = resultString + path + "\n";
			}
		}
		}
		catch(Exception e){
			log.info("Error in search "+ e.getStackTrace());
		}
		
		response.getWriter().write("Reponse from querybuilder demo : results are " + resultString);
		
		
	}

}
