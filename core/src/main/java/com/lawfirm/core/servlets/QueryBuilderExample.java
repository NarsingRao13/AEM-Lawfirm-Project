package com.lawfirm.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.paths=" + "/lawfirm/task/QueryBuilderExample",
		// "sling.servlet.resourceTypes="+
		// "Lawfirm/components/structure/base-page",
		"sling.servlet.extensions=" + "sendMail" })
@ServiceDescription("QueryBuilder Example")
public class QueryBuilderExample extends SlingAllMethodsServlet {
	private final Logger LOG = LoggerFactory.getLogger(QueryBuilderExample.class);
	@Reference
	private QueryBuilder builder;
	private Session session;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("Starts");
		String param = request.getParameter("param");
		ResourceResolver resourceResolver = request.getResourceResolver();
		session = resourceResolver.adaptTo(Session.class);
		Map<String, String> predicate = new HashMap<>();
		
		  predicate.put("path", "/content/"); predicate.put("type",
		  "dam:Asset"); predicate.put("group.p.or", "true");
		  predicate.put("group.1_fulltext", param);
		  predicate.put("group.1_fulltext.relPath", "jcr:content");
		 
		/*
		 * predicate.put("type", "cq:Page"); predicate.put("1_property",
		 * "content/cq:template"); predicate.put("1_property.value",
		 * "/conf/Moderna/settings/wcm/templates/basetemplate");
		 */

//		predicate.put("type", "cq:Page");
//		predicate.put("1_property", "jcr:content/cq:template");
//		predicate.put("1_property.value", "/conf/Lawfirm/settings/wcm/templates/content-page-template");
//		predicate.put("2_property", "jcr:content/jcr:title");
//		predicate.put("2_property.value", "us");

		Query query = builder.createQuery(PredicateGroup.create(predicate), session);
		query.setStart(0);
		query.setHitsPerPage(20);
		SearchResult searchResult2 = query.getResult();
		try {
			Query query2 = builder.loadQuery("/mypath/getfiles1", session);
			for (Hit hit : searchResult2.getHits()) {
				String path = hit.getPath();
				response.getWriter().println(path);
			}
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		SearchResult searchResult = query.getResult();
		List<Hit> hitt = searchResult.getHits();
		LOG.info("Length " + hitt.size());
		try {
			for (Hit hit : searchResult.getHits()) {

				String path = hit.getPath();
				response.getWriter().println(path);
			}
			//builder.storeQuery(query, "/mypath/getfiles1", false, session);
			session.save();
			session.refresh(true);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		LOG.info("End");

	}

}
