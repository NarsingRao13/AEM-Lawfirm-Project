package com.lawfirm.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RoleDetails {
	@Inject
	private String image;
	@Inject
	private String candidateName;
	@Inject
	private String description;

	public String getImage() {
		return image;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public String getDescription() {
		return description;
	}

}




