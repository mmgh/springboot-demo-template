package com.springboot.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

//remove pom dependecy or autoconfiguration
@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		RedisAutoConfiguration.class,
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		SecurityFilterAutoConfiguration.class,
		XADataSourceAutoConfiguration.class,
		QuartzAutoConfiguration.class,
		HazelcastAutoConfiguration.class,
		SessionAutoConfiguration.class,
		JtaAutoConfiguration.class,
		ErrorMvcAutoConfiguration.class
})
@EnableAsync
//public class SpringbootDemoApplication extends SpringBootServletInitializer{
public class SpringbootDemoApplication extends SpringBootServletInitializer implements CommandLineRunner, ApplicationRunner{

	private static Logger logger = LoggerFactory.getLogger(SpringbootDemoApplication.class);
	
	public static void main(String[] args) {
		logger.info("[fwk]SpringbootDemoApplication.......");
		//SpringApplication.run(SpringbootDemoApplication.class, args);
		
		//examples Spring Application Builder Listeners.
		new SpringApplicationBuilder(SpringbootDemoApplication.class)
			.listeners(new ApplicationListener<ApplicationEvent>() {
				//ApplicationEvent Listener.(ObserverPattern)
				@Override
				public void onApplicationEvent(ApplicationEvent event) {
					logger.info("[fwk-Listener-ApplicationEvent] {}", event.getClass().getCanonicalName());
				}
			})
			.listeners(new ApplicationListener<ApplicationStartedEvent>() {
				//ApplicationStartedEvent Listener
				@Override
				public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
					logger.info("[fwk-Listener-ApplicationStartedEvent] applicationStarted..");
				}
			})
			.run(args);
	}
	
	
	@Bean
	public String applicationInfo() {
		return "springboot-demo-application";
	}
	
	@Autowired
	public String applicationInfo;
	

	@Override
	public void run(ApplicationArguments args) throws Exception{
		logger.info("[fwk]ApplicationRunner implemented");
		logger.info("applicationInfo bean : {}", applicationInfo);
	}
	
	@Override
	public void run(String...args) throws Exception{
		logger.info("[fwk]CommandLineRunner implemented");
		//TODO
	}
	
	
	/**
	 * IF you don't use @ConfigurationProperties, remove this annotation
	 * @see DemoProperties.java 
	 */
	//@Bean
	//public DemoProperties demoProperties() {
	//	return new DemoProperties();
	//}
	
	
	
	
}
