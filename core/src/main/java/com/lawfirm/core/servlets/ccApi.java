package com.lawfirm.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import com.lawfirm.core.service.ContentFragmentsCURDoperations;
import com.lawfirm.core.utils.LawfirmUtils;

@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/lawfirm/api",
		"sling.servlet.selector=" + "json" })
@ServiceDescription("Task Servlet")
public class ccApi extends SlingSafeMethodsServlet {

	@Reference
	private ContentFragmentsCURDoperations fragmentsCURDoperations;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		String s[] = request.getRequestURI().split("[.]");
		JSONObject jsonObject = fragmentsCURDoperations.getContentFragmentWithAllVariationsData(s[1]);
		JSONObject dataObject =  fragmentsCURDoperations.getContentFragmentData(jsonObject);
		JSONObject variations = fragmentsCURDoperations.getContentFragmentVariation(jsonObject);
		Iterator<String> keys = variations.keys();
		try {
			while (keys.hasNext()) {
				String key = keys.next();
				JSONObject variation = variations.getJSONObject(key);
				JSONArray listItems = variation.getJSONArray("listItems");
				String[] listItemsArray = new String[listItems.length()];
				for(int j=0;j<listItems.length();j++) {
					listItemsArray[j] = listItems.getString(j);
				}
				listItems = new JSONArray("[]");
				JSONObject listData = new JSONObject();
				for(int i=0;i<listItemsArray.length;i++) {
					String data = listItemsArray[i];
					String[] cfData = data.split("/");
					String cardName = cfData[cfData.length-1];
					JSONObject cardData = fragmentsCURDoperations.getContentFragmentData(fragmentsCURDoperations.getContentFragmentWithAllVariationsData(cardName));
					listData.put(cardName,cardData);
					//listItems.remove(i);
					//out.println(cardData);
				}
				listItems.put(listData);
				variation.put("listItems", listItems);
				variations.put(key, variation);
			}
			dataObject.remove("listItems");
			jsonObject.put(LawfirmUtils.CONTENT_FRAGMENT_DATA, dataObject);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.println(jsonObject);
	}
}
