package com.lawfirm.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "AEM Lawfirm - Modular OSGi Configuration.",
						description = "Modular OSGi Configuration demo.")
public @interface LawfirmOSGiConfig{
	@AttributeDefinition(
			name = "Service ID",
			description = "Enter Service Id",
			type = AttributeType.INTEGER)
	int serviceID();
	
	@AttributeDefinition(
			name = "Service Name",
			description = "Enter Service Name",
			type = AttributeType.STRING)
	public String serviceName() default "AEM Lawfirm Service";
	
	@AttributeDefinition(
			name = "Service URL",
			description = "Add Service URL",
			type = AttributeType.STRING)
	String serviceURL() default "localhost";
}
