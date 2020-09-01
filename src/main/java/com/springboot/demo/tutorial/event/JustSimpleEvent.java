package com.springboot.demo.tutorial.event;

public class JustSimpleEvent {
	
	private String eventName;
	
	public JustSimpleEvent(String eventName) {
		super();
		this.eventName = eventName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


}
