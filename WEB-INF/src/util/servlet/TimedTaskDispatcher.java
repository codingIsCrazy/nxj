package util.servlet;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class TimedTaskDispatcher {
	private static TimedTaskDispatcher ttd = new TimedTaskDispatcher();
	private Scheduler scheduler = null;
	
	private TimedTaskDispatcher(){
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		try {
			scheduler = schedulerFactory.getScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public static TimedTaskDispatcher getInstance(){
		return ttd;
	}
	
	public boolean addTimedTask(String jobName, String jobGroup,Class jobClass,String cexpString){
		JobDetail jobDetail = new JobDetail(jobName, jobClass);
		try {
			CronTrigger cronTrigger = new CronTrigger(jobName, jobGroup, cexpString);
			cronTrigger.setMisfireInstruction(Trigger.INSTRUCTION_NOOP);
            this.scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}

	public boolean addTimedTask(String jobName, String jobGroup,Class jobClass, long time){
		JobDetail jobDetail = new JobDetail(jobName, jobGroup, jobClass);
		SimpleTrigger trigger = new SimpleTrigger(jobName, jobGroup);
		try {
			trigger.setRepeatInterval(time * 1000);
			trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
            this.scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}

	public void updateTimedTask(String jobName, String jobGroup,Class jobClass, String cexpString){
		try {
			this.scheduler.pauseJob(jobName, jobGroup);
			this.scheduler.removeTriggerListener(jobName+"_cronTrigger");
			this.scheduler.deleteJob(jobName, jobGroup);
			JobDetail jobDetail = new JobDetail(jobName, jobGroup, jobClass);
			CronTrigger cronTrigger = new CronTrigger(jobName, jobGroup);
			CronExpression cexp = new CronExpression(cexpString);
            cronTrigger.setCronExpression(cexp);
            this.scheduler.scheduleJob(jobDetail, cronTrigger);
            this.scheduler.resumeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void updateTimedTask(String jobName, String jobGroup,Class jobClass, long time){
		try {
			this.scheduler.pauseJob(jobName, jobGroup);
			this.scheduler.removeTriggerListener(jobName+"_cronTrigger");
			this.scheduler.deleteJob(jobName, jobGroup);
			JobDetail jobDetail = new JobDetail(jobName, jobGroup, jobClass);
			SimpleTrigger trigger = new SimpleTrigger(jobName, jobGroup);
			trigger.setRepeatInterval(time * 1000);
			trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
            this.scheduler.scheduleJob(jobDetail, trigger);
            this.scheduler.resumeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void start(){
		try {
			if(this.scheduler == null){
				return ;
			}
			if(!this.scheduler.isStarted()){
				this.scheduler.start();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void shutdown(){
		try{
			if(this.scheduler == null){
				return ;
			}
			if(this.scheduler.isStarted()){
				this.scheduler.shutdown(true);
			}
		}catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public Scheduler getScheduler() {
		return scheduler;
	}
}
