package com.lawfirm.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.script.Bindings;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.adobe.cq.sightly.WCMBindings;

import io.wcm.testing.mock.aem.junit5.AemContext;

class FooterCFModelTest {
	@InjectMocks
	private FooterCFModel model;

	@Mock
	Node cfNode;
	@Mock
	ResourceResolver resolver;
	@Mock
	Resource resource;
	@Mock
	Resource resource1;
	@Mock
	private Bindings bindings;
	// or
	// private Bindings bindings = mock(Bindings.class);
	AemContext aemContext = new AemContext();
	@Mock
	Property property;
	private String content;
	private String latestNews;
	private String address;
	private String phoneNumber;
	private String email;
	private String message;
	private String copyrightsText;

	@BeforeEach
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testActivate() throws Exception {
		//String cfInput="/content/dam/Lawfirm/cfs/footer-cf",variation="master";
		when(bindings.get("cfInput")).thenReturn("/content/dam/Lawfirm/cfs/footer-cf");
		when(bindings.get("variation")).thenReturn("master");
		when(bindings.get(WCMBindings.RESOURCE_PAGE))
				.thenReturn("/conf/Lawfirm/settings/wcm/templates/basetemplate/structure/jcr:content/root/footer_cf");
		when(resource.getResourceResolver()).thenReturn(resolver);
		when(resolver.getResource("/content/dam/Lawfirm/cfs/footer-cf" + "/jcr:content/data/" + "master"))
				.thenReturn(resource1);
		when(resource1.adaptTo(Node.class)).thenReturn(cfNode);
		when(cfNode.hasProperty(Mockito.anyString())).thenReturn(true);
		when(cfNode.getProperty(Mockito.anyString())).thenReturn(property);
		model.init(bindings);
	}
	
	/*@Test
	void testActivate_NullPointerException() throws RepositoryException {
		
		when(cfNode.hasProperty(Mockito.anyString())).thenThrow(new PathNotFoundException());
		model.init(bindings);
	}*/

	@Test
	void testContent() throws Exception {
		Field field = model.getClass().getDeclaredField("content");
		field.setAccessible(true);
		field.set(model, content);
		assertEquals(content, model.getContent());
	}

	@Test
	void testAddress() throws Exception {
		Field field = model.getClass().getDeclaredField("address");
		field.setAccessible(true);
		field.set(model, address);
		assertEquals(address, model.getAddress());
	}

	@Test
	void testPhoneNumber() throws Exception {
		Field field = model.getClass().getDeclaredField("phoneNumber");
		field.setAccessible(true);
		field.set(model, phoneNumber);
		assertEquals(phoneNumber, model.getPhoneNumber());
	}

	@Test
	void testMessage() throws Exception {
		Field field = model.getClass().getDeclaredField("message");
		field.setAccessible(true);
		field.set(model, message);
		assertEquals(message, model.getMessage());
	}

	@Test
	void testLatestNews() throws Exception {
		Field field = model.getClass().getDeclaredField("latestNews");
		field.setAccessible(true);
		field.set(model, latestNews);
		assertEquals(latestNews, model.getLatestNews());
	}

	@Test
	void testEmail() throws Exception {
		Field field = model.getClass().getDeclaredField("email");
		field.setAccessible(true);
		field.set(model, email);
		assertEquals(email, model.getEmail());
	}

	@Test
	void testCopyrightsText() throws Exception {
		Field field = model.getClass().getDeclaredField("copyrightsText");
		field.setAccessible(true);
		field.set(model, copyrightsText);
		assertEquals(copyrightsText, model.getCopyrightsText());
	}
}
