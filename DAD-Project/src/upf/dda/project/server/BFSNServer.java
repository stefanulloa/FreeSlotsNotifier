package upf.dda.project.server;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.sun.net.httpserver.HttpServer;

import twitter4j.TwitterException;
import upf.dda.project.data.ScheduledTestJob;
import upf.dda.project.data.ScheduledTestJobTelegram;

public class BFSNServer {
	

	public static void main(String[] args) throws IOException, TwitterException, SchedulerException {

		
		// Start a JDK HttpServer with the Jersey application configured in the
		// ResourceConfig and
		// deployed in the given URI:
		try {
			URI serverUri = UriBuilder.fromUri("http://localhost/").port(15000).build();

			ResourceConfig configClients = new ResourceConfig(APIClients.class, APIStations.class);
			HttpServer serverClients = JdkHttpServerFactory.createHttpServer(serverUri, configClients);

			System.out.println("Servers started...");

			// Specify the job' s details..
			JobDetail job = JobBuilder.newJob(ScheduledTestJob.class).withIdentity("testJob").build();
			// Specify the running period of the job
			Trigger trigger = TriggerBuilder.newTrigger()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60).repeatForever())
					.build();
			// Schedule the job
			
			
			
			// job for telegram reply upon receiving a msg. 
			JobDetail job2 = JobBuilder.newJob(ScheduledTestJobTelegram.class).withIdentity("testJob2").build();
			// Specify the running period of the job
			Trigger trigger2 = TriggerBuilder.newTrigger()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
					.build();
		
			
			//send tweet and debug in out put 
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();

			sched.start();
			//add jobs to scheduleJob
			sched.scheduleJob(job, trigger);
			sched.scheduleJob(job2, trigger2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
