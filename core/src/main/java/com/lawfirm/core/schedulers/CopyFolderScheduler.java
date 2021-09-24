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

import com.lawfirm.core.service.CopyFolder;
@Component(service = Runnable.class, immediate = true)
@Designate(ocd = CopyFolderScheduler.Config.class)
public class CopyFolderScheduler implements Runnable{
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(CopyFolderScheduler.class);
	private int schedulerId;
	@Reference
	private Scheduler scheduler; 
	
	@Reference
	private CopyFolder copyFolder;
	
	 @ObjectClassDefinition(name="Copy Folder Scheduler",
             description = "Scheduler to copy content of one folder to other")
	public static @interface Config {
		@AttributeDefinition(
				name = "Scheduler Name",
				description = "Name of scheduler",
				type = AttributeType.STRING
				)
		public String schedulerName() default "Copy Folder Content to Other";
		
		@AttributeDefinition(
				name = "Cron Expression",
				description = "Cron Expression used by scheduler",
				type = AttributeType.STRING
				)
		public String cronExpression() default "0 0/10 * 1/1 * ? *"; //run every 20 seconds
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
		LOG.info("---Copy Folder Scheduler Added---");
		System.out.println("---Copy Folder Scheduler Added---");
		
		ScheduleOptions scheduleOptionsNow = scheduler.NOW();
		scheduler.schedule(this, scheduleOptionsNow);
	}
	

	private void removerScheduler() {
		scheduler.unschedule(String.valueOf(schedulerId));
		System.out.println("---Copy Folder Scheduler Removed---");
	}

	
	@Override
	public void run() {
		if(copyFolder.copy()){
			LOG.info("---Copy Folder Scheduler Run Method Executing---");
			System.out.println("---Copy Folder Scheduler Run Method Executing---");
		} else {
			LOG.info("---Copy Folder Scheduler Run Method Execution Failed---");
			System.out.println("---Copy Folder Scheduler Run Method Execution Failed---");
		}
		
	}
}
