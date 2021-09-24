package com.lawfirm.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import com.lawfirm.core.service.ContentFragmentsCURDoperations;

@Component(
		service = Servlet.class, 
		property = { 
				"sling.servlet.methods=" + HttpConstants.METHOD_PUT,
				"sling.servlet.paths=" + "/lawfirm/task/a2", 
				"sling.servlet.selector=" + "txt" 
				})
@ServiceDescription("Path based(Task) Servlet")
public class PathBaseServlet extends SlingAllMethodsServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().print("hyyy");
		
		
	}

	
	
	
}
