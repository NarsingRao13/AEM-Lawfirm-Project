package com.lawfirm.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.osgi.services.HttpClientBuilderFactory;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawfirm.core.service.ContentFragmentsCURDoperations;
import com.lawfirm.core.utils.LawfirmUtils;

@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.paths=" + "/lawfirm/task",
		"sling.servlet.selector=" + "json" })
@ServiceDescription("Task Servlet")
public class Task extends SlingAllMethodsServlet {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(Task.class);
	@Reference
	private ContentFragmentsCURDoperations fragmentsCURDoperations;
	@Reference
	private HttpClientBuilderFactory httpClientBuilderFactory;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		String s[] = request.getRequestURI().split("[.]");
		JSONObject jsonObject = fragmentsCURDoperations.getContentFragmentWithAllVariationsData(s[1]);
		out.println(jsonObject);
		out.println(getAPIData(jsonObject));
	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		String s[] = request.getRequestURI().split("[.]");
		JSONObject jsonObject = fragmentsCURDoperations.getContentFragment1(s[1]);
		out.println(jsonObject);
		// out.println(hitAPI(jsonObject));
		// hitAPI(jsonObject);
	}

	public JSONArray getAPIData(JSONObject jsonObject) {
		JSONArray response = new JSONArray();
		JSONObject variations = new JSONObject();
		try {
			variations = jsonObject.getJSONObject(LawfirmUtils.CONTENT_FRAGMENT_VARIATIONS);
			System.out.println(variations.length());
			Iterator<String> iterator  = variations.keys();
			while(iterator.hasNext()) {
				JSONObject desktop = variations.getJSONObject(iterator.next());
				String url = desktop.getString("apiUrl");
				response.put(hitAPI(url));
			}
			
		} catch (JSONException | IOException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

	public JSONObject hitAPI(String url) throws IOException, JSONException {
		String line = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); 
		System.out.println("Request Type: " + httpget.getMethod());
		HttpResponse httpresponse = httpclient.execute(httpget);
		Scanner sc = new Scanner(httpresponse.getEntity().getContent());
		System.out.println(httpresponse.getStatusLine());
		while (sc.hasNext()) {
			line = line + sc.nextLine();
		}
		return new JSONObject().put(url, line);
	}
}
