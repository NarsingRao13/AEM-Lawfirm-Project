package com.lawfirm.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
import com.lawfirm.core.service.DatabaseService;

@Component(service = Servlet.class, 
	property = { 
		"sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.paths=" + "/lawfirm/task/getDataFromDB", 
		"sling.servlet.selector=" + ".json" 
	})
@ServiceDescription("Path based(Task) Servlet to get data from database")
public class GetDataFromDatabase extends SlingAllMethodsServlet{
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(GetDataFromDatabase.class);
	@Reference
	private CURDOperationOnMysql operationOnMysql;
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String tableName = request.getParameter("tableName");
		out.println(operationOnMysql.getData(tableName));
	}
	
	
	
}
