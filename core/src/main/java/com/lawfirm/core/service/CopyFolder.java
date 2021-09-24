package com.lawfirm.core.service;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Workspace;

import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = CopyFolder.class)
public class CopyFolder {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(CopyFolder.class);
	@Reference
	private User user;
	private ResourceResolver resolver;
	Session session;
	Workspace workspace;

	public boolean copy(){
		resolver = user.getUser();
		session = resolver.adaptTo(Session.class);
		workspace = session.getWorkspace(); 
		Node srcnode = resolver.getResource("/content/dam/Lawfirm/cfs").adaptTo(Node.class);
		Node destnode = resolver.getResource("/content/dam/Lawfirm/random1/").adaptTo(Node.class);
		try {
			if (srcnode.hasNodes()) {
			    NodeIterator worknodes = srcnode.getNodes();
			    while (worknodes.hasNext()) {
			    	Node childnde = worknodes.nextNode();
			        // formation of destination path and its relative path variables
			       
				    String[] parts = childnde.getPath().split("/");
				    String destrelativepath = parts[parts.length-1]; 
					String destpath = destnode.getPath() + "/" + parts[parts.length-1];
					    if (validatedestpath(destnode, destrelativepath)) {
					        workspace.copy(childnde.getPath(), destpath);
					    }
					LOG.info("destrelativepath--> "+destrelativepath);
			    }
			    session.logout();
			}
			return true;
		} catch (RepositoryException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
		
    }
	
	Boolean validatedestpath(Node destnode, String destrelativepath) {
		try {
			if (destnode.hasNode(destrelativepath)) {
				LOG.info("--> "+destrelativepath+" has already exist");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}