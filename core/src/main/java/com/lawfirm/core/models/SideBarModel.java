package com.lawfirm.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SideBarModel {
	@Inject
	private String heading;
	@Inject
	private List<SidebarContent> sidebarContent;

	public String getHeading() {
		return heading;
	}

	public List<SidebarContent> getSidebarContent() {
		return sidebarContent;
	}

}
