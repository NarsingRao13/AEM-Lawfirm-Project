package com.lawfirm.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;
import com.lawfirm.core.service.MailService;
import com.lawfirm.core.service.imp.MailServiceImp;

@Component(
		service = Servlet.class, 
		property = { 
				"sling.servlet.methods=" + HttpConstants.METHOD_GET,
				//"sling.servlet.paths=" + "/lawfirm/task/sendMail", 
				"sling.servlet.resourceTypes="+ "Lawfirm/components/structure/base-page",
				 "sling.servlet.extensions=" + "sendMail"
				})
@ServiceDescription("Triggers an Email")
public class SendEmail extends SlingAllMethodsServlet{
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(SendEmail.class);
	@Reference
	private MailService mailService;
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		Resource resource = request.getResource();
		String data = (String) resource.getValueMap().get(JcrConstants.JCR_TITLE);
		String fistName = "<p><b>First Name</b> = "+request.getParameter("fistName")+"</p>";
		String lastName = "<p><b>Last Name</b> = "+request.getParameter("lastName")+"</p>";
		String email = "<p><b>E-Mail</b> = "+request.getParameter("email")+"</p>";
		String message = "<p><b>Message</b> = "+request.getParameter("message")+"</p>";
		LOG.info("fistName "+fistName);
		LOG.info("lastName "+lastName);
		LOG.info("email "+email);
		LOG.info("message "+message);
		data = fistName+lastName+email+message;
		
		
		mailService.sendMail(data,"Testing Mail");
		response.getWriter().print("data "+data);
	}
	
}
