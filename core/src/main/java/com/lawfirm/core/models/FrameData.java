package com.lawfirm.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FrameData {
	@Inject
	private String image;
	@Inject
	private String title;
	@Inject
	private String description;

	public String getImage() {
		return image;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
}
