package com.lawfirm.core.models;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;

import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderDynamicModel {
	@Inject
	@Source("sling-object")
	private ResourceResolver resourceResolver;
	
	public Page parentPage() {
		Resource resource = resourceResolver.getResource("/content/Lawfirm/us/en");
		Page page = resource.adaptTo(Page.class);
		return page;

	}

}
