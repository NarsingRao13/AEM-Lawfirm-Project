package com.lawfirm.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.lawfirm.core.models.OSGIConfigModelDemo;
import com.lawfirm.core.models.OSGiConfigDemo;
import com.lawfirm.core.service.OGSiConfigModule;

@Model(adaptables = SlingHttpServletRequest.class, 
		adapters = OSGiConfigDemo.class, 
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGiConfigDemoImpl implements OSGiConfigDemo {

	@OSGiService
	OGSiConfigModule oSGiConfigModule;

	@Override
	public int getServiceId() {
		return oSGiConfigModule.getServiceId();
	}

	@Override
	public String getServiceName() {
		return oSGiConfigModule.getServiceName();
	}

	@Override
	public String getServiceURL() {
		return oSGiConfigModule.getServiceURL();
	}

}
