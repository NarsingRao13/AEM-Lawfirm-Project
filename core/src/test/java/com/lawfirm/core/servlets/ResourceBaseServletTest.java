package com.lawfirm.core.servlets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
public class ResourceBaseServletTest {
	AemContext aemContext = new AemContext();
	ResourceBaseServlet rs = new ResourceBaseServlet();
	
	@BeforeEach
	public void setup(){
		
	}
	
	@Test
	public void doGetTest() throws ServletException, IOException{
		//aemContext.build().resource("/content/Lawfirm/random-page","jcr:title", "Random Page");
		//aemContext.currentResource("/content/Lawfirm/random-page");
		aemContext.build().resource("/content/test", "jcr:title", "resource title").commit();
        aemContext.currentResource("/content/test");

        MockSlingHttpServletRequest request = aemContext.request();
        MockSlingHttpServletResponse response = aemContext.response();

        rs.doGet(request, response);

        //assertEquals("Title = Random Page", response.getOutputAsString());
        assertEquals("Title = resource title", response.getOutputAsString());
	}
}
