package com.lawfirm.core.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class, 
property = { 
		"sling.servlet.methods=" + HttpConstants.METHOD_PUT,
	"sling.servlet.paths=" + "/sample/ReadCSV", 
	"sling.servlet.selector=" + ".json" 
	})
public class ReadCSV extends  SlingAllMethodsServlet {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(ReadCSV.class);
	@Override
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		
		//readDomainUrls();
		response.getWriter().print("hello");
	}
	
	 public List<String> readDomainUrls(){
		   List<String> domainUrls = new ArrayList<String>();
			String line = "";
			String splitBy = ",";
			try {
			  BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\d1\\Desktop\\data.csv"));
			  while ((line = br.readLine()) != null)
			  {
				String[] domainList = line.split(splitBy);
				String url = domainList[0]+"test.jpg";		
				domainUrls.add(url);
				LOG.info("link "+url);
			  }
			}
			catch(IOException e) {
			  e.printStackTrace();
			}
			return domainUrls;
		}
}
