package com.lawfirm.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.paths=" + "/lawfirm/task/a1",
		// "sling.servlet.paths=" + "/pathservlet/task/readJsonData",
		"sling.servlet.extensions=" + ".json" })
public class TestingServlet extends SlingSafeMethodsServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String data = request.getParameter("val");
		response.getWriter().print("Narsing");
		response.getWriter().print(data);
	}

}
