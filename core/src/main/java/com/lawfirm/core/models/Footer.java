package com.lawfirm.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Footer {
	@Inject
	private String latestNews;
	@Inject
	private String address;
	@Inject
	private String phone;
	@Inject
	private String email;
	@Inject
	private String message;

	public String getLatestNews() {
		return latestNews;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getMessage() {
		return message;
	}

}
