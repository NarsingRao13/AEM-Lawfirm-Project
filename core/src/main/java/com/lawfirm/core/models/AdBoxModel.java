package com.lawfirm.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AdBoxModel {
	@Inject
	private String adImage;
	@Inject
	private String title1;
	@Inject
	private String title2;
	@Inject
	private String caption1;
	@Inject
	private String caption2;

	public String getAdImage() {
		return adImage;
	}

	public String getTitle1() {
		return title1;
	}

	public String getTitle2() {
		return title2;
	}

	public String getCaption1() {
		return caption1;
	}

	public String getCaption2() {
		return caption2;
	}

}
