package com.lawfirm.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.component.annotations.Reference;

import com.lawfirm.core.models.OSGIConfigModelDemo;
import com.lawfirm.core.service.OSGiConfig;

@Model(adaptables = SlingHttpServletRequest.class, 
		adapters = OSGIConfigModelDemo.class, 
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGIConfigDemoImpModel implements OSGIConfigModelDemo {
	@OSGiService
	private OSGiConfig oSGIConfig;

	@Override
	public String getServiceName() {
		return oSGIConfig.getServiceName();
	}

	@Override
	public int getServiceCount() {
		return oSGIConfig.getServiceCount();
	}

	@Override
	public boolean isLiveData() {
		return oSGIConfig.isLiveData();
	}

	@Override
	public String[] getCountries() {
		return oSGIConfig.getCountries();
	}

	@Override
	public String getRunModes() {
		return oSGIConfig.getRunModes();
	}

}
