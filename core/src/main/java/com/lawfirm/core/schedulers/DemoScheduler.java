package com.lawfirm.core.schedulers;

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


@Component(service = Runnable.class, immediate = true)
@Designate(ocd = DemoScheduler.Config.class)
public class DemoScheduler implements Runnable{
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(DemoScheduler.class);
	
	private int schedulerId;
	
	@Reference
	private Scheduler scheduler; 
	
	 @ObjectClassDefinition(name="A demo scheduled task",
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
		addScheduler(config);
	}
	
	@Deactivate
	protected void deactivate(Config config) {
		removerScheduler();
	}
	

	private void addScheduler(Config config) {
		ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
		scheduleOptions.name(String.valueOf(schedulerId));
		scheduleOptions.canRunConcurrently(false);
		scheduler.schedule(this	, scheduleOptions);
		LOG.info("---Scheduler Added---");
		System.out.println("---Scheduler Added---");
		
		ScheduleOptions scheduleOptionsNow = scheduler.NOW();
		//ScheduleOptions scheduleOptionsNow = scheduler.NOW(6,10);
		scheduler.schedule(this, scheduleOptionsNow);
	}
	

	private void removerScheduler() {
		scheduler.unschedule(String.valueOf(schedulerId));
		System.out.println("---Scheduler Removed---");
	}

	
	@Override
	public void run() {
		LOG.info("---Scheduler Run Method Executing---");
		System.out.println("---Scheduler Run Method Executing---");
	}

}
