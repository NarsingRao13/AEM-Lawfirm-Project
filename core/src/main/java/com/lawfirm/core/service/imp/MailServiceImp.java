package com.lawfirm.core.service.imp;

import javax.jcr.RepositoryException;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import com.lawfirm.core.service.MailService;
import com.lawfirm.core.service.User;

@Component(service = MailService.class)
public class MailServiceImp implements MailService {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(MailServiceImp.class);
	@Reference
	private MessageGatewayService messageGatewayService;
	private ResourceResolver resolver;
	@Reference
	private User user;
	@Override
	public void sendMail(String data, String subject) {
		MessageGateway<HtmlEmail> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
		resolver = user.getUser();
		UserManager userManager = resolver.adaptTo(UserManager.class);
		Authorizable authorizable;
		try {
			LOG.info("Email start");
			authorizable = userManager.getAuthorizable("admin");
			String userEmail = PropertiesUtil.toString(authorizable.getProperty("profile/email"), "");
			LOG.info("userEmail = "+userEmail);
			if(userEmail == null)
				return;
			HtmlEmail email = new HtmlEmail();
			email.addTo(userEmail);
			email.setSSL(true);
			email.setSubject(subject);
			email.setHtmlMsg("<html><head></head><body><center><b>Here is the message </b>"+data+"</center></body></html>");
			messageGateway.send(email);
			LOG.info("Email has sent");
		} catch (RepositoryException | EmailException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		
		
	}

}
