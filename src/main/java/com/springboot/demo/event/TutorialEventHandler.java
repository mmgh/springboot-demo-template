package com.springboot.demo.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.springboot.demo.tutorial.event.JustSimpleEvent;

@Component
public class TutorialEventHandler {
	
	private static Logger log = LoggerFactory.getLogger(TutorialEventHandler.class);
	
	@EventListener
	public void onMyEvent(JustSimpleEvent event) {
		log.error("event handling, data : {}", event.getEventName());
	}
}
