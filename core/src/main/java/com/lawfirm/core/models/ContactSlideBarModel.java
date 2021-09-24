package com.lawfirm.core.models;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ContactSlideBarModel {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(ContactSlideBarModel.class);
	@Inject
	@Source("sling-object")
	private ResourceResolver resourceResolver;
	@Inject
	private String heading;
	@Inject
	private String cfInput;
	@Inject
	private String variation;
	private String name;
	private String firm;
	private String address;
	private String phone;
	private String fax;
	private String email;

	public String getName() {
		return name;
	}

	public String getFirm() {
		return firm;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getFax() {
		return fax;
	}

	public String getEmail() {
		return email;
	}

	public String getHeading() {
		return heading;
	}

	public String getCfInput() {
		return cfInput;
	}

	public String getVariation() {
		return variation;
	}

	public void getCf()  {
		//cfInput =
		LOG.info("path = "+cfInput );
		LOG.info("variation = "+variation );
		System.out.println("\n path = " + cfInput + "/jcr:content/data/" + variation);
		try {
			Node cfNode = resourceResolver.getResource( cfInput + "/jcr:content/data/" + variation).adaptTo(Node.class);
			//String p = cfNode.getPath();
			if (cfNode != null) {
				if (cfNode.hasProperty("name")) {
					Property pro = cfNode.getProperty("name");
					name = pro.getString();
				}

				if (cfNode.hasProperty("firm")) {
					Property pro = cfNode.getProperty("firm");
					firm = pro.getString();
				}

				if (cfNode.hasProperty("address")) {
					Property pro = cfNode.getProperty("address");
					address = pro.getString();
				}

				if (cfNode.hasProperty("phone")) {
					Property pro = cfNode.getProperty("phone");
					phone = pro.getString();

				}

				if (cfNode.hasProperty("fax")) {
					Property pro = cfNode.getProperty("fax");
					fax = pro.getString();
				}

				if (cfNode.hasProperty("email")) {
					Property pro = cfNode.getProperty("email");
					email = pro.getString();
				}

			}
		} catch (NullPointerException | RepositoryException e) {
			e.printStackTrace();
			//LOG.error(e.getMessage());
			//System.out.println("\nHey!!!");
		}
	}

}
