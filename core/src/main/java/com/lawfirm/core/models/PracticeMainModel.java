package com.lawfirm.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PracticeMainModel {
	@Inject
	private List<PracticeFrames> practiceFrames;
	@Inject
	private String description;

	public List<PracticeFrames> getPracticeFrames() {
		return practiceFrames;
	}

	public String getDescription() {
		return description;
	}

}
