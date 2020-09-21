package com.springboot.demo.config;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;


/**
 * WebApplicationContext beans 
 * @author surely-kafka
 *
 */
@Configuration
public class DemoWebConfig implements WebApplicationInitializer{

	private static Logger logger = LoggerFactory.getLogger(DemoWebConfig.class);
	
	@PostConstruct
	public void init() {
		logger.info("[fwk]DemoWebConfig init....");
	}

	
	/**
	 * Filter and Servlet Async Support setting.
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		DispatcherServlet servlet = new DispatcherServlet();
		ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", servlet);
		registration.setAsyncSupported(true);
	}
	
	
	
	
}
