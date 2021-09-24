package com.lawfirm.core.workflow;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import com.lawfirm.core.service.RemoveRendition;

@Component(service = WorkflowProcess.class, immediate = true, property = {
		Constants.SERVICE_DESCRIPTION + "=Removes Rendition for saved data", "process.label=" + "Remove Rendition", })
public class RemoveRenditionWorkflow implements WorkflowProcess{
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(RemoveRenditionWorkflow.class);

	@Reference
	private RemoveRendition removeRendition; 
	@Override
	public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
		WorkflowData workflowData = item.getWorkflowData();
		String path = workflowData.getPayload().toString();
		LOG.info("Saved Asset = "+path);
		removeRendition.removeRendition(path); 
	}
}
