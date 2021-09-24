package com.lawfirm.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawfirm.core.service.ContentFragmentsCURDoperations;
import com.lawfirm.core.service.DatabaseService;

@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_PUT,
		"sling.servlet.paths=" + "/lawfirm/task/getCF", "sling.servlet.selector=" + ".json" })
@ServiceDescription("Path based(Task) Servlet to get Content Fragment Data")
public class GetContentFragment extends SlingAllMethodsServlet {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(GetContentFragment.class);
	@Reference
	private ContentFragmentsCURDoperations contentFragmentsCURDoperations;
	
	@Override
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//http://localhost:4502/lawfirm/task/getCF?cf=demo-cf1
		String cf = request.getParameter("cf");
		JSONObject data = contentFragmentsCURDoperations.getContentFragment(cf);
		if (data != null) {
			out.print(data);
		} else {
			response.sendError(SlingHttpServletResponse.SC_BAD_REQUEST, "Unable to find " + cf + " content fragment.");
		}
		
	}

}
