package com.lawfirm.core.service.imp;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.lawfirm.core.config.LawfirmOSGiConfig;
import com.lawfirm.core.service.OGSiConfigModule;

@Component(service = OGSiConfigModule.class)
@Designate(ocd = LawfirmOSGiConfig.class)
public class OSGiConfigModuleImpl implements OGSiConfigModule {
	private int serviceId;
	private String serviceName;
	private String serviceURL;

	@Activate
	protected void activate(LawfirmOSGiConfig lawfirmOSGiConfig) {
		serviceId = lawfirmOSGiConfig.serviceID();
		serviceName = lawfirmOSGiConfig.serviceName();
		serviceURL = lawfirmOSGiConfig.serviceURL();
	}

	@Override
	public int getServiceId() {
		return serviceId;
	}

	@Override
	public String getServiceName() {
		return serviceName;
	}

	@Override
	public String getServiceURL() {
		return serviceURL;
	}

}
