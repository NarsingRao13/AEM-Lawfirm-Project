package com.lawfirm.core.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
@Component(service = RemoveRendition.class)
public class RemoveRendition {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(RemoveRendition.class);
	private Session session;
	@Reference
	private QueryBuilder builder;
	@Reference
	private User user;
	private ResourceResolver resolver;
	List<Node> childrenList = null;
	public void removeRenditionServlet(String path) {
		resolver = user.getUser();
		session = resolver.adaptTo(Session.class);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("path", path);
		map.put("property", "jcr:primaryType");
		map.put("property.1_value", "nt:folder");
		Query query = builder.createQuery(PredicateGroup.create(map), session);
		SearchResult result = query.getResult();
		List<Hit> hits = result.getHits();
		try {
			for (Hit hit : hits) {
				Resource renditionResource = null;
				LOG.info("Hit Path "+hit.getPath());
				renditionResource = resolver.resolve(hit.getPath());
				Iterator<Resource> reneditionIterator = renditionResource.listChildren();
				while (reneditionIterator.hasNext()) {
					Resource specificResource = reneditionIterator.next();
					Node renditionNode = specificResource.adaptTo(Node.class);
					LOG.info("node--> "+renditionNode.getName()+" --> "+renditionNode.getPath());
					
					if(!renditionNode.getName().equals("original")){  
	                     renditionNode.remove();  	
					}  
				}
			}
			session.save();
			session.logout();
		} catch (RepositoryException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void removeRendition(String path) {
		resolver = user.getUser();
		session = resolver.adaptTo(Session.class);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("path", path);
		map.put("property", "jcr:primaryType");
		map.put("property.1_value", "nt:folder");
		Query query = builder.createQuery(PredicateGroup.create(map), session);
		SearchResult result = query.getResult();
		List<Hit> hits = result.getHits();
		try {
			for (Hit hit : hits) {
				Resource renditionResource = null;
				LOG.info("Hit Path "+hit.getPath());
				renditionResource = resolver.resolve(hit.getPath());
				Iterator<Resource> reneditionIterator = renditionResource.listChildren();
				while (reneditionIterator.hasNext()) {
					Resource specificResource = reneditionIterator.next();
					Node renditionNode = specificResource.adaptTo(Node.class);
					LOG.info("node--> "+renditionNode.getName()+" --> "+renditionNode.getPath());
					if(!renditionNode.getName().equals("original")){  
	                     renditionNode.remove();
	                  }  
				}
			}
			session.save();
			session.logout();
		} catch (RepositoryException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/*public List<Node> getChildNodes(Node node) {
		childrenList = new ArrayList<Node>();
		collectChildList(node);

		try {
			Iterator<Node> it = childrenList.iterator();
			while (it.hasNext()) {
				LOG.info(it.next().getPath());
			}
		} catch (Exception e) {
			LOG.debug(e.toString());
		}

		return childrenList;
	}

	private void collectChildList(Node subNode) {
		try {
			childrenList.add(subNode);
			if (subNode.hasNodes()) {
				NodeIterator ni = subNode.getNodes();
				while (ni.hasNext()) {
					collectChildList(ni.nextNode());
				}
			}
		} catch (RepositoryException e) {
			LOG.debug(e.toString());
		}
	}*/

}
