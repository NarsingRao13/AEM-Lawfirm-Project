package com.lawfirm.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ContactSlideBarModelTest {
	@InjectMocks
	private ContactSlideBarModel model;
	@Mock
	private ResourceResolver resourceResolver;
	@Mock
	private Node cfNode;
	@Mock
	private Resource resource;
	@Mock
	private Property pro;

	private String heading;
	private String cfInput;
	private String variation;
	private String name;
	private String firm;
	private String address;
	private String phone;
	private String fax;
	private String email;

	@BeforeEach
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testCf() throws Exception  {
		this.testCfInput();
		this.testVariation();
		// when(resourceResolver.getResource("/content/dam/Lawfirm/cfs/contact-cf/jcr:content/data/master")).thenReturn(resource);
		when(resourceResolver.getResource(Mockito.anyString())).thenReturn(resource);
		when(resource.adaptTo(Node.class)).thenReturn(cfNode);
		when(cfNode.hasProperty(Mockito.anyString())).thenReturn(true);
		when(cfNode.getProperty(Mockito.anyString())).thenReturn(pro);
		//when(cfNode.hasProperty(Mockito.anyString())).thenThrow(new RepositoryException()).thenReturn(false);
		//when(cfNode.getProperty(Mockito.anyString())).thenThrow(new RepositoryException(), new PathNotFoundException());
		
		model.getCf();

	}

	/*@Test
	void testCfException() throws Exception {
		
		this.testCfInput();
		this.testVariation();
		
		when(resourceResolver.getResource(Mockito.anyString())).thenReturn(resource);
		when(resource.adaptTo(Node.class)).thenReturn(cfNode);
		model.getCf();
		when(cfNode.hasProperty(Mockito.anyString())).thenReturn(true);
		when(cfNode.hasProperty(Mockito.anyString())).thenThrow(new RepositoryException());
		when(cfNode.getProperty(Mockito.anyString())).thenThrow(new RepositoryException(), new PathNotFoundException());
		///this.testCf();
		model.getCf();

	}*/

	@Test
	void testAddress() throws Exception {
		Field field = model.getClass().getDeclaredField("address");
		field.setAccessible(true);
		field.set(model, address);
		assertEquals(address, model.getAddress());
	}

	@Test
	void testCfInput() throws Exception {
		cfInput = "/content/dam/Lawfirm/cfs/contact-cf";
		Field field = model.getClass().getDeclaredField("cfInput");
		field.setAccessible(true);
		field.set(model, cfInput);
		assertEquals(cfInput, model.getCfInput());
	}

	@Test
	void testEmail() throws Exception {
		Field field = model.getClass().getDeclaredField("email");
		field.setAccessible(true);
		field.set(model, email);
		assertEquals(email, model.getEmail());
	}

	@Test
	void testFax() throws Exception {
		Field field = model.getClass().getDeclaredField("fax");
		field.setAccessible(true);
		field.set(model, fax);
		assertEquals(fax, model.getFax());
	}

	@Test
	void testFirm() throws Exception {
		Field field = model.getClass().getDeclaredField("firm");
		field.setAccessible(true);
		field.set(model, firm);
		assertEquals(firm, model.getFirm());
	}

	@Test
	void testHeading() throws Exception {
		Field field = model.getClass().getDeclaredField("heading");
		field.setAccessible(true);
		field.set(model, heading);
		assertEquals(heading, model.getHeading());
	}

	@Test
	void testName() throws Exception {
		Field field = model.getClass().getDeclaredField("name");
		field.setAccessible(true);
		field.set(model, name);
		assertEquals(name, model.getName());
	}

	@Test
	void testPhone() throws Exception {
		Field field = model.getClass().getDeclaredField("phone");
		field.setAccessible(true);
		field.set(model, phone);
		assertEquals(phone, model.getPhone());
	}

	@Test
	void testVariation() throws Exception {
		variation = "master";
		Field field = model.getClass().getDeclaredField("variation");
		field.setAccessible(true);
		field.set(model, variation);
		assertEquals(variation, model.getVariation());
	}

}
