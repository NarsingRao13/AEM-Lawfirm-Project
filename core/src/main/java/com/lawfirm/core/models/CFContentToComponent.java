package com.lawfirm.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Property;
import javax.jcr.Value;
import com.adobe.cq.sightly.WCMUsePojo;

/*
 * Author Karukola Narsing Rao
 * This class helps in setting content of CF(Content Fragment) to Component
 */
public class CFContentToComponent extends WCMUsePojo {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(CFContentToComponent.class);
	private String content = "";
	private String title;
	private Double number;
	private List<String> multiValue;

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getMultiValue() {
		return multiValue;
	}

	public Double getNumber() {
		return number;
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
				if (cfNode.hasProperty("title")) {
					Property pro1 = cfNode.getProperty("title");
					title = pro1.getString();
				}

				if (cfNode.hasProperty("number")) {
					Property pro3 = cfNode.getProperty("number");
					number = pro3.getDouble();

				}

				if (cfNode.hasProperty("multiFieldTitle")) {
					Property pro2 = cfNode.getProperty("multiFieldTitle");
					Value[] value = pro2.getValues();
					multiValue = new ArrayList<String>();
					for (Value v : value) {
						LOG.info("Multi Field Value : " + v.getString());
						multiValue.add(v.getString());
					}
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
 * http://localhost:4502/content/dam/Lawfirm/cfs/testing-cf.cfm.info.json
 */