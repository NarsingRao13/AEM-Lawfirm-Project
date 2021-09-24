package com.lawfirm.core.service.imp;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.lawfirm.core.service.User;
import com.lawfirm.core.utils.LawfirmUtils;
@Component(service = User.class)
public class UserImp implements User {
	@Reference
	private ResourceResolverFactory resourceResolverFactory;
	
	@Override 
	public ResourceResolver getUser() {
		ResourceResolver resolver = null;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, LawfirmUtils.SUB_SERVICE_USER);
		try {
			resolver = this.resourceResolverFactory.getServiceResourceResolver(param);
		} catch (Exception e) {
			System.out.println(e);
		}
		return resolver;
	}

}
