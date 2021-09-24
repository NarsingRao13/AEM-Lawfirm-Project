package com.lawfirm.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.*;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.lawfirm.core.service.RemoveRendition;

@Component(service = Servlet.class, 
	property = { 
			"sling.servlet.methods=" + HttpConstants.METHOD_PUT,
		"sling.servlet.paths=" + "/lawfirm/task/removeRendition", 
		"sling.servlet.selector=" + ".json" 
		})
public class RemoveRenditionServlet extends SlingSafeMethodsServlet {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(RemoveRenditionServlet.class);
	
	@Reference
	private RemoveRendition removeRendition;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().println("Start");
		String path = request.getParameter("path");
		removeRendition.removeRenditionServlet(path);
		response.getWriter().println("End");
	}
}
