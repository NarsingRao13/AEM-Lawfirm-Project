package com.lawfirm.core.service;

import org.json.JSONObject;

public interface ContentFragmentsCURDoperations {
	public JSONObject getContentFragment(String contentFragment);
	public JSONObject getContentFragment1(String contentFragment);
	public JSONObject getContentFragmentWithAllVariationsData(String contentFragment);
}
