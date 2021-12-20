package com.lawfirm.core.service.imp;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.json.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.adobe.cq.dam.cfm.ContentVariation;
import com.adobe.cq.dam.cfm.DataType;
import com.adobe.cq.dam.cfm.FragmentData;
import com.adobe.cq.dam.cfm.VariationDef;
import com.lawfirm.core.service.ContentFragmentsCURDoperations;
import com.lawfirm.core.service.User;
import com.lawfirm.core.utils.LawfirmUtils;

@Component(service = ContentFragmentsCURDoperations.class)
public class ContentFragmentsCURDoperationsImpl implements ContentFragmentsCURDoperations {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(ContentFragmentsCURDoperationsImpl.class);
	@Reference
	private User user;
	private ResourceResolver resolver;
	JSONObject jsonObject = new JSONObject();
	JSONObject elements = new JSONObject();

	@Override
	public JSONObject getContentFragment(String contentFragment) {
		resolver = user.getUser();
		Resource resource = resolver.getResource(LawfirmUtils.CF_FOLDER_PATH + contentFragment);
		if (resource != null) {
			ContentFragment fragment = resource.adaptTo(ContentFragment.class);
			Iterator<ContentElement> iterator = fragment.getElements();
			try {
				while (iterator.hasNext()) {
					ContentElement element = iterator.next();
					FragmentData data = element.getValue();
					String dataType = data.getDataType().getTypeString();
					switch (dataType) {
					case "string": {
						elements.put(element.getName(), ((data.getValue() != null) ? data.getValue().toString() : ""));
						break;
					}
					case "double": {
						elements.put(element.getName(), ((data.getValue() != null) ? data.getValue().toString() : ""));
						break;
					}
					case "boolean": {
						elements.put(element.getName(), ((data.getValue() != null) ? data.getValue().toString() : ""));
						break;
					}
					case "calendar": {
						if (data.getValue() != null) {
							Calendar calendar = (Calendar) data.getValue();
							String date = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-"
									+ calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.HOUR) + ":"
									+ calendar.get(Calendar.MINUTE);
							elements.put(element.getName(), date);
						}
						break;
					}
					}
				}
				jsonObject.put("", elements);
			} catch (JSONException e) {
				LOG.error(e.getMessage());
			}
		} else {
			LOG.error(contentFragment + " not found");
			jsonObject = null;
		}
		return jsonObject;
	}

	@Override
	public JSONObject getContentFragmentWithAllVariationsData(String contentFragment) {
		resolver = user.getUser();
		JSONObject response = new JSONObject();
		JSONObject elements = new JSONObject();
		JSONObject variations = new JSONObject();
		Resource cfResource = resolver.getResource(LawfirmUtils.CF_FOLDER_PATH + contentFragment);
		if (cfResource != null) {
			try {
				ContentFragment fragment = cfResource.adaptTo(ContentFragment.class);
				Iterator<VariationDef> variationsList = fragment.listAllVariations();
				while (variationsList.hasNext()) {
					variations.put(variationsList.next().getName(), new JSONObject());
				}

				Iterator<ContentElement> contentElements = fragment.getElements();
				while (contentElements.hasNext()) {
					ContentElement element = contentElements.next();
					elements.put(element.getName(),
							(element.getValue().getValue() != null) ? (element.getValue().getValue().toString()) : "");
					Iterator<ContentVariation> variatonsIterator = element.getVariations();
					while (variatonsIterator.hasNext()) {
						ContentVariation variation = variatonsIterator.next();
						JSONObject innerData = (JSONObject) variations.get(variation.getName());
						if (variation.getValue() != null) {
							FragmentData fragmentData = variation.getValue();
							if (fragmentData.getValue() != null) {
								DataType dataType = fragmentData.getDataType();
								if (!dataType.isMultiValue()) {
									innerData.put(element.getName(), fragmentData.getValue());
								} else {
									String[] stringArray = (String[]) fragmentData.getValue();
									/*
									 * int i = 0; if (stringArray != null) { for (String data : stringArray) { if
									 * (data.contains(TCPConstants.FRAGMENT_PARENT_RESOURCE) &&
									 * data.startsWith(TCPConstants.FRAGMENT_PARENT_RESOURCE) &&
									 * !isContentFragment(data)) stringArray[i] = tcpConfigService.getUrl() + data;
									 * i++; } }
									 */
									List<String> contentValueList = Arrays
											.asList(stringArray != null ? stringArray : new String[0]);
									innerData.put(element.getName(), contentValueList);
								}

							}
						}
						variations.put(variation.getName(), innerData);
					}
				}
				response.put(LawfirmUtils.CONTENT_FRAGMENT_DATA, elements);
				response.put(LawfirmUtils.CONTENT_FRAGMENT_VARIATIONS, variations);
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return response;
	}

	@Override
	public JSONObject getContentFragment1(String contentFragmentName) {
		JSONObject response = new JSONObject();
		JSONObject elements = new JSONObject();
		JSONObject variations = new JSONObject();
		LOG.info(contentFragmentName);
		resolver = user.getUser();
		try {
			Resource fragmentResource = resolver.getResource(LawfirmUtils.CF_FOLDER_PATH + contentFragmentName);
			LOG.info(fragmentResource.getPath() + " , " + fragmentResource.getName());
			if (fragmentResource != null) {
				ContentFragment fragment = fragmentResource.adaptTo(ContentFragment.class);
				response.put(LawfirmUtils.CONTENT_FRAGMENT_NAME, fragment.getName());
				response.put(LawfirmUtils.CONTENT_FRAGMENT_TITLE, fragment.getTitle());
				response.put(LawfirmUtils.CONTENT_FRAGMENT_DESC, fragment.getDescription());

				Iterator<ContentElement> contentElements = fragment.getElements();
				while (contentElements.hasNext()) {
					ContentElement contentElement = contentElements.next();
					JSONObject element = new JSONObject();
					element.put(LawfirmUtils.CONTENT_FRAGMENT_TITLE, contentElement.getTitle());

					element.put(LawfirmUtils.CONTENT_FRAGMENT_TYPE, contentElement.getContentType());

					FragmentData fragmentData = contentElement.getValue();
					element.put(LawfirmUtils.CONTENT_FRAGMENT_VALUE,
							(fragmentData.getValue() != null) ? (fragmentData.getValue().toString()) : "");

					DataType dataType = fragmentData.getDataType();
					element.put(LawfirmUtils.CONTENT_FRAGMENT_DATATYPE, dataType.getTypeString());

					Iterator<ContentVariation> contentVariations = contentElement.getVariations();
					while (contentVariations.hasNext()) {
						ContentVariation contentVariation = contentVariations.next();
						JSONObject variation = new JSONObject();
						variation.put(LawfirmUtils.CONTENT_FRAGMENT_TITLE, contentVariation.getTitle());
						variation.put(LawfirmUtils.CONTENT_FRAGMENT_TYPE, contentVariation.getContentType());
						variation.put(LawfirmUtils.CONTENT_FRAGMENT_DESC, contentVariation.getDescription());

						FragmentData variationData = contentVariation.getValue();
						variation.put(LawfirmUtils.CONTENT_FRAGMENT_VALUE,
								(variationData.getValue() != null) ? (variationData.getValue().toString()) : "");

						DataType variationDataType = variationData.getDataType();
						variation.put(LawfirmUtils.CONTENT_FRAGMENT_DATATYPE, variationDataType.getTypeString());

						variations.put(contentVariation.getName(), variation);

					}
					element.put(LawfirmUtils.CONTENT_FRAGMENT_VARIATIONS, variations);
					elements.put(contentElement.getName(), element);
				}

				String[] tags = (String[]) fragment.getMetaData().get(LawfirmUtils.CONTENT_FRAGMENT_TAGS_KEY);
				response.put(LawfirmUtils.CONTENT_FRAGMENT_ELEMENTS, elements);
				response.put(LawfirmUtils.CONTENT_FRAGMENT_TAGS, tags);

			}

		} catch (Exception e) {
			LOG.error("Error while getting Content Fragment {}", e);
		}
		return response;
	}

	@Override
	public JSONObject getContentFragmentData(JSONObject object) {
		JSONObject data = null;
		try {
			data = object.getJSONObject(LawfirmUtils.CONTENT_FRAGMENT_DATA);
		} catch (JSONException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public JSONObject getContentFragmentVariation(JSONObject object) {
		JSONObject variations = null;
		try {
			variations = object.getJSONObject(LawfirmUtils.CONTENT_FRAGMENT_VARIATIONS);
		} catch (JSONException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		return variations;
	}

}
