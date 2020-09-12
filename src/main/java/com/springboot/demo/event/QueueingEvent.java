package com.springboot.demo.event;

/**
 * This event has source. so ApplicationListener<ApplicationEvent>() can listen.
 * 
 * @see SpringbootDemoApplication, EventQueueCommand
 * @author surely-kafka
 *
 */
public class QueueingEvent {

	private Object source;

	private String data;

	public QueueingEvent(Object source) {
		this(source, null);
	}

	public QueueingEvent(Object source, String data) {
		this.source = source;
		this.data = data;
	}

	public Object getSource() {
		return source;
	}

//	public void setSource(Object source) {
//		this.source = source;
//	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}

