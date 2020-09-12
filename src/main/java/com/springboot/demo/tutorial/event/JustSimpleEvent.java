package com.springboot.demo.tutorial.event;

/**
 * This event has source. so ApplicationListener<ApplicationEvent>() can listen.
 * 
 * @see SpringbootDemoApplication, EventQueueCommand
 * @author surely-kafka
 *
 */
public class JustSimpleEvent {
	
	private Object source;

	private String eventName;
	
	public JustSimpleEvent(Object source) {
		this(source, null);
	}
	
	public JustSimpleEvent(Object source, String eventName) {
		this.source    = source;
		this.eventName = eventName;
	}

	public Object getSource() {
		return source;
	}
	
	//public void setSource(Object source) {
	//	this.source = source;
	//}
	
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


}
