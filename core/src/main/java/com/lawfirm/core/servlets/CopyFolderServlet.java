package com.lawfirm.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawfirm.core.service.CopyFolder;

@Component(
		service = Servlet.class, 
		property = { 
				"sling.servlet.methods=" + HttpConstants.METHOD_GET,
				"sling.servlet.paths=" + "/lawfirm/task/copyfolder", 
				"sling.servlet.selector=" + ".json" 
				})
@ServiceDescription("Path based(Task) Servlet for copy folder content to other")
public class CopyFolderServlet extends SlingSafeMethodsServlet{
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(CopyFolderServlet.class);
	@Reference
	CopyFolder copyFolder;
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().print( copyFolder.copy());
	}
}
