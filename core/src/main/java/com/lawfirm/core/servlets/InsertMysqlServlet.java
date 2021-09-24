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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawfirm.core.service.CURDOperationOnMysql;
import com.lawfirm.core.utils.Employee;

@Component(
		service = Servlet.class, 
		property = { 
				"sling.servlet.methods=" + HttpConstants.METHOD_PUT,
				"sling.servlet.paths=" + "/lawfirm/task/insert", 
				"sling.servlet.selector=" + ".json" 
				})
@ServiceDescription("Path based(Task) Servlet for to perform CURD operations(Insert) on mysql database")
public class InsertMysqlServlet extends SlingAllMethodsServlet {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(InsertMysqlServlet.class);
	
	@Reference
	private CURDOperationOnMysql operationOnMysql;
	
	@Override
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		double salary = Double.parseDouble(request.getParameter("salary"));
		String empDept = request.getParameter("empDept");
		if(operationOnMysql.insertData(new Employee(id, name, salary, empDept)))
			out.println("Inserted");
		else 
			out.println("Failed to insert");
	}

}
