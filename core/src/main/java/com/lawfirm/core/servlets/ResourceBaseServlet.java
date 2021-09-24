package com.lawfirm.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

import com.day.cq.commons.jcr.JcrConstants;

@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.resourceTypes=" + "Lawfirm/components/structure/base-page",
		"sling.servlet.selectors=" + "test" })
@ServiceDescription("This servlet use to get data using JCR Queries")
public class ResourceBaseServlet extends SlingSafeMethodsServlet{
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		Resource resource = request.getResource();
		response.setContentType("text/plain");
		response.getWriter().print("Title = "+resource.getValueMap().get(JcrConstants.JCR_TITLE));
	}
}
