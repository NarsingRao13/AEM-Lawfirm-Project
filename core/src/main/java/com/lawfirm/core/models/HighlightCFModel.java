package com.lawfirm.core.models;

import javax.jcr.Node;
import javax.jcr.Property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

/*
 * Author Karukola Narsing Rao
 * This class helps in setting content of CF(Content Fragment) to Component
 */
public class HighlightCFModel extends WCMUsePojo {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(HighlightCFModel.class);
	private String content = "";
	private String testimonialsText;
	private String testimonialsAuthor;
	private String heading;
	private String text1;
	private String text2;
	private String atext1;
	private String atext2;

	public String getContent() {
		return content;
	}

	public String getTestimonialsText() {
		return testimonialsText;
	}

	public String getTestimonialsAuthor() {
		return testimonialsAuthor;
	}

	public String getHeading() {
		return heading;
	}

	public String getText1() {
		return text1;
	}

	public String getText2() {
		return text2;
	}

	public String getAtext1() {
		return atext1;
	}

	public String getAtext2() {
		return atext2;
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
				if (cfNode.hasProperty("testimonialsText")) {
					Property pro1 = cfNode.getProperty("testimonialsText");
					testimonialsText = pro1.getString();
				}

				if (cfNode.hasProperty("testimonialsAuthor")) {
					Property pro3 = cfNode.getProperty("testimonialsAuthor");
					testimonialsAuthor = pro3.getString();

				}
				

				if (cfNode.hasProperty("heading")) {
					Property pro3 = cfNode.getProperty("heading");
					heading = pro3.getString();

				}

				if (cfNode.hasProperty("text1")) {
					Property pro3 = cfNode.getProperty("text1");
					text1 = pro3.getString();

				}

				if (cfNode.hasProperty("text2")) {
					Property pro3 = cfNode.getProperty("text2");
					text2 = pro3.getString();

				}

				if (cfNode.hasProperty("atext1")) {
					Property pro3 = cfNode.getProperty("atext1");
					atext1 = pro3.getString();

				}

				if (cfNode.hasProperty("atext2")) {
					Property pro3 = cfNode.getProperty("atext2");
					atext2 = pro3.getString();

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
 * http://localhost:4502/content/dam/Lawfirm/cfs/highlight-cf.cfm.info.json
 */