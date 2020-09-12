package com.springboot.demo.event;

/**
 * This event has no source.
 * EventListener can listen this event. but, ApplicationListener<ApplicationEvent>() can't listen.
 * 
 * @see SpringbootDemoApplication, QueueingEvent
 * @author surely-kafka
 *
 */
public class EventQueueCommand {
	
	private String command;

	public EventQueueCommand(String command) {
		super();
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
}
