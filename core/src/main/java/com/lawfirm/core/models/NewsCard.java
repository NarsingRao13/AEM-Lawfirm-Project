package com.lawfirm.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsCard {
	@Inject
	private String image;
	@Inject
	private String date;
	@Inject
	private String author;
	@Inject
	private String headline;
	@Inject
	private String content;

	public String getImage() {
		return image;
	}

	public String getDate() {
		return date;
	}

	public String getAuthor() {
		return author;
	}

	public String getHeadline() {
		return headline;
	}

	public String getContent() {
		return content;
	}

}
