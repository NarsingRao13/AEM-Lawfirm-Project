package com.lawfirm.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "AEM Lawfirm - OSGi Factory Configuration",
description = "OSGi Factory Configuration demo.")
public @interface LawfirmOSGiFactoryConfig {
	@AttributeDefinition(
			name = "Config ID",
			description = "Enter Service Id",
			type = AttributeType.INTEGER)
	int configID();
	
	@AttributeDefinition(
			name = "Service Name",
			description = "Enter Service Name",
			type = AttributeType.STRING)
	public String serviceName() default "Service #";
	
	@AttributeDefinition(
			name = "Service URL",
			description = "Add Service URL",
			type = AttributeType.STRING)
	String serviceURL() default "URL #";
}
