package com.lawfirm.core.models.impl;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.lawfirm.core.models.OSGiFactoryConfigDemo;
import com.lawfirm.core.service.OSGiFactoryConfig;

@Model(adaptables = SlingHttpServletRequest.class,
		adapters = OSGiFactoryConfigDemo.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGiFactoryConfigDemoImpl implements OSGiFactoryConfigDemo{
	@OSGiService
	OSGiFactoryConfig osGiFactoryConfig;

	@Override
	public List<OSGiFactoryConfig> getConfigList() {
		return osGiFactoryConfig.getConfigList();
	}
	
}
