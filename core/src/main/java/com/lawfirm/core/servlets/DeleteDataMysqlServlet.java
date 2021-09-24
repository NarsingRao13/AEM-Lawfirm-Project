package com.lawfirm.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import com.lawfirm.core.service.CURDOperationOnMysql;
import com.lawfirm.core.utils.Employee;

@Component(
		service = Servlet.class, 
		property = { 
				"sling.servlet.methods=" + HttpConstants.METHOD_PUT,
				"sling.servlet.paths=" + "/lawfirm/task/delete", 
				"sling.servlet.selector=" + ".json" 
				})
@ServiceDescription("Path based(Task) Servlet for to perform CURD operations(Delete) on mysql database")
public class DeleteDataMysqlServlet extends SlingAllMethodsServlet{
	@Reference
	private CURDOperationOnMysql operationOnMysql;
	
	@Override
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		if(operationOnMysql.deleteData(id))
			out.println("Deleted Successfully");
		else 
			out.println("Failed to delete");
	}
}
