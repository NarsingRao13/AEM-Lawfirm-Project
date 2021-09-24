package com.lawfirm.core.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.osgi.annotation.bundle.Requirement.Cardinality;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;

import com.lawfirm.core.config.LawfirmOSGiFactoryConfig;
import com.lawfirm.core.service.OSGiFactoryConfig;

@Component(service = OSGiFactoryConfig.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = LawfirmOSGiFactoryConfig.class, factory = true)
public class OSGiFactoryConfigImpl implements OSGiFactoryConfig {
	// private static final LOG =
	// LoggerFactory.getLogger(OSGiFactoryConfigImpl.class);
	private int configID;
	private String serviceName;
	private String serviceURL;
	private List<OSGiFactoryConfig> configList;
	
	
	@Activate
	protected void activate(LawfirmOSGiFactoryConfig lawfirmOSGiFactoryConfig) {
		configID = lawfirmOSGiFactoryConfig.configID();
		serviceName = lawfirmOSGiFactoryConfig.serviceName();
		serviceURL = lawfirmOSGiFactoryConfig.serviceURL();
	}
	
	@Reference(service = OSGiFactoryConfig.class, cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public void bindOSGiFactoryConfig(final OSGiFactoryConfig config){
		if(configList == null){
			configList = new ArrayList<OSGiFactoryConfig>();
		}
		configList.add(config);
	}
	
	public void unbindOSGiFactoryConfig(final OSGiFactoryConfig config){
		configList.remove(config);
	}

	@Override
	public int getConfigID() {
		return configID;
	}

	@Override
	public String getServiceName() {
		return serviceName;
	}

	@Override
	public String getServiceURL() {
		return serviceURL;
	}
	
	@Override
	public List<OSGiFactoryConfig> getConfigList() {
		return configList;
	}

	
}
