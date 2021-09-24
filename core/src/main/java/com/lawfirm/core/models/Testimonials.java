package com.lawfirm.core.models;

import java.util.ArrayList;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

public class Testimonials extends WCMUsePojo {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(Testimonials.class);
	private String content = "";
	private String testimonialsTitle;
	private String message;
	private String authorName;

	public String getContent() {
		return content;
	}

	public String getTestimonialsTitle() {
		return testimonialsTitle;
	}

	public String getMessage() {
		return message;
	}

	public String getAuthorName() {
		return authorName;
	}

	@Override
	public void activate() throws Exception {
		String cfInput = get("cfInput", String.class);
		String variation = "";

		try {
			variation = get("variation", String.class);
			LOG.info("variation = " + variation);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		cfInput = cfInput + "/jcr:content/data/" + variation;
		try {
			Node cfNode = getResource().getResourceResolver().getResource(cfInput).adaptTo(Node.class);
			if (cfNode != null) {
				content = cfNode.getPath();
				if (cfNode.hasProperty("testimonialsTitle")) {
					Property pro = cfNode.getProperty("testimonialsTitle");
					testimonialsTitle = pro.getString();
				}

				if (cfNode.hasProperty("message")) {
					Property pro = cfNode.getProperty("message");
					message = pro.getString();

				}

				if (cfNode.hasProperty("authorName")) {
					Property pro = cfNode.getProperty("authorName");
					authorName = pro.getString(); 
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			System.out.println("\nend");
		}
	
	}

}
