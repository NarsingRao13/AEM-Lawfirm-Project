package com.lawfirm.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SideBar {
	@Inject
	private String title;
	@Inject
	private String link;

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

}
