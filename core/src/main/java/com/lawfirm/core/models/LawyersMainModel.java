package com.lawfirm.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LawyersMainModel {
	@Inject
	private String mainTitle;
	@Inject
	private List<RolesModel> rolesModel;
	public String getMainTitle() {
		return mainTitle;
	}
	public List<RolesModel> getRolesModel() {
		return rolesModel;
	}

	
}
