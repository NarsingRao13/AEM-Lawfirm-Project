package com.lawfirm.core.models;

import javax.jcr.Node;
import javax.jcr.Property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

public class FooterCFModel extends WCMUsePojo {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(FooterCFModel.class);
	private String content = "";
	private String latestNews;
	private String address;
	private String phoneNumber;
	private String email;
	private String message;
	private String copyrightsText;

	public String getContent() {
		return content;
	}

	public String getLatestNews() {
		return latestNews;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getMessage() {
		return message;
	}

	public String getCopyrightsText() {
		return copyrightsText;
	}

	@Override
	public void activate() throws Exception {
		String cfInput = get("cfInput", String.class);
		String variation = get("variation", String.class);
		LOG.info("variation = " + variation);
		try {
			Node cfNode = getResource().getResourceResolver().getResource(cfInput + "/jcr:content/data/" + variation).adaptTo(Node.class);
			if (cfNode != null) {
				content = cfNode.getPath();
				if (cfNode.hasProperty("latestNews")) {
					Property pro1 = cfNode.getProperty("latestNews");
					latestNews = pro1.getString();
				}

				if (cfNode.hasProperty("address")) {
					Property pro3 = cfNode.getProperty("address");
					address = pro3.getString();

				}

				if (cfNode.hasProperty("phoneNumber")) {
					Property pro3 = cfNode.getProperty("phoneNumber");
					phoneNumber = pro3.getString();

				}

				if (cfNode.hasProperty("email")) {
					Property pro3 = cfNode.getProperty("email");
					email = pro3.getString();

				}

				if (cfNode.hasProperty("message")) {
					Property pro3 = cfNode.getProperty("message");
					message = pro3.getString();

				}

				if (cfNode.hasProperty("copyrightsText")) {
					Property pro3 = cfNode.getProperty("copyrightsText");
					copyrightsText = pro3.getString();

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			System.out.println("\nend");
		}
	}
}

/*
 * http://localhost:4502/content/dam/Lawfirm/cfs/footer-cf.cfm.info.json
 */
