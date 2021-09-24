package com.lawfirm.core.workflow;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.lawfirm.core.service.MailService;
import com.lawfirm.core.servlets.SendEmail;

@Component(
		service = WorkflowProcess.class,
		immediate = true,
		property = {
			Constants.SERVICE_DESCRIPTION + "=Sends email when a page is created",
			"process.label=" + "Send Email On Create", 
		}
)
public class TiggerEmailOnCreate implements WorkflowProcess{
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(TiggerEmailOnCreate.class);
	@Reference
	private MailService mailService;
	@Override
	public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
		LOG.info("Page Created");
		WorkflowData workflowData = item.getWorkflowData();
		String path = workflowData.getPayload().toString();
		String type = workflowData.getPayloadType();
		LOG.info("path "+path+"\n type "+type);
		String data = "A page created at "+path;
		mailService.sendMail(data, "<b>Page Created!!<b>");
		LOG.info("Workflow-Success");
		
	}

}
