package com.lawfirm.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RolesModel {
	@Inject
	private String role;
	@Inject
	private String isLastOne;
	@Inject
	private List<RoleDetails> roleDetails;

	public String getIsLastOne() {
		return isLastOne;
	}

	public List<RoleDetails> getRoleDetails() {
		return roleDetails;
	}

	public String getRole() {
		return role;
	}

}
