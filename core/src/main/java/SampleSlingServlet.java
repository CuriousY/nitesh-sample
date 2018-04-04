import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_POST,		
		"sling.servlet.paths=" + "/bin/server/myServlet"})

public class SampleSlingServlet extends SlingSafeMethodsServlet {

	
	private static final Logger log = LoggerFactory.getLogger(SampleSlingServlet.class);
	
	protected void doGet(SlingHttpServletRequest request, 
			SlingHttpServletResponse response) throws IOException {
		log.info("Inside get");
		response.getWriter().print(" I am in doGet Method");
	}
	
	protected void doPost(SlingHttpServletRequest request, 
			SlingHttpServletResponse response) throws IOException {
		log.info("Inside post");
		response.getWriter().print(" I am in doPost Method");
	}
}