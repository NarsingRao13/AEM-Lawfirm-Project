package com.lawfirm.core.schedulers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.sling.commons.scheduler.Job;
import org.apache.sling.commons.scheduler.JobContext;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = Job.class, immediate = true)
@Designate(ocd = DemoSchedulerJobs.Config.class)
public class DemoSchedulerJobs implements Job{
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(DemoSchedulerJobs.class);
	
	private int schedulerId;
	
	@Reference
	private Scheduler scheduler; 
	
	 @ObjectClassDefinition(name="A demo scheduled jobs task",
             description = "Simple demo for cron-job like task with properties")
	public static @interface Config {
		@AttributeDefinition(
				name = "Scheduler Name",
				description = "Name of scheduler",
				type = AttributeType.STRING
				)
		public String schedulerName() default "Custom Sling Scheduler Configuration";
		
		@AttributeDefinition(
				name = "Cron Expression",
				description = "Cron Expression used by scheduler",
				type = AttributeType.STRING
				)
		public String cronExpression() default "0/20 * * * * ?"; //run every 20 seconds
	}
	
	@Activate
	protected void activate(Config config) {
		schedulerId = config.schedulerName().hashCode();
		addSchedulerJob(config);
	}
	
	@Deactivate
	protected void deactivate(Config config) {
		removerSchedulerJob();
	}
	

	private void addSchedulerJob(Config config) {
		ScheduleOptions in = scheduler.EXPR("0 34 18 1/1 * ? *");
		Map<String, Serializable> inMap = new HashMap<>();
		inMap.put("country", "IN");
		inMap.put("url", "www.in.com");
		in.config(inMap);
		scheduler.schedule(this, in);
		
		ScheduleOptions de = scheduler.EXPR("0 34 18 1/1 * ? *");
		Map<String, Serializable> deMap = new HashMap<>();
		deMap.put("country", "DE");
		deMap.put("url", "www.de.com");
		de.config(deMap);
		scheduler.schedule(this, de);
		
		ScheduleOptions us = scheduler.EXPR("0 34 18 1/1 * ? *");
		Map<String, Serializable> usMap = new HashMap<>();
		usMap.put("country", "US");
		usMap.put("url", "www.us.com");
		us.config(usMap);
		scheduler.schedule(this, us);
	}
	

	private void removerSchedulerJob() {
		scheduler.unschedule(String.valueOf(schedulerId));
		System.out.println("---Scheduler Job Removed---");
	}


	@Override
	public void execute(JobContext context) {
		LOG.info("---Job Executing---");
		LOG.info("---country--- {}",context.getConfiguration().get("country"));
		LOG.info("---url---{}",context.getConfiguration().get("url"));
		System.out.println("---Job Executing---");
	}

}
