package com.springboot.demo.event;

import java.util.LinkedList;
import java.util.Queue;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventQueue {
	
	private static Logger log = LoggerFactory.getLogger(EventQueue.class);
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	private Queue<QueueingEvent> q;
	
	private final int QUEUE_SIZE = 5;
	
	public enum COMMAND{
		  PRINT("PRINT")
		, REMOVE_ALL("REMOVE_ALL");
		
		final private String command;
		
		private COMMAND(String command) {
			this.command = command;
		}
		
		public String getCommand() {
			return command;
		}
		
	}
	
	@PostConstruct
	public void init() {
		log.info("[fwk]EventQueue bean init....");
		q = new LinkedList<>();
	}
	
	@EventListener
	public void eventOffer(QueueingEvent event) {
		this.q.offer(event);
	}


	public boolean isFull() {
		if(this.q.size() > this.QUEUE_SIZE) {
			return true;
		}else {
			return false;
		}
	}

	
	public void ifItsFullDo(String something) {
		if(this.isFull()) {
			this.eventPublisher.publishEvent(new EventQueueCommand(something));
		}
	}
	
	
	@EventListener
	public void doCommand(EventQueueCommand event) {
		
		log.info("IS THIS PRINT COMMAND? ");
		
		Queue<QueueingEvent> tmpQ = new LinkedList<>();
		
		if(COMMAND.PRINT.getCommand().equals(event.getCommand())) {
			
			while(q.iterator().hasNext()) {
				String data = this.q.poll().getData();
				log.info("[fwk]Queue : {}", data);
				tmpQ.offer(new QueueingEvent(this, data));
			}
			
			q.addAll(tmpQ);
			tmpQ.clear();
			
		}else if(COMMAND.REMOVE_ALL.getCommand().equals(event.getCommand())) {
			this.q.clear();
			log.info("[fwk]EventQueue has been removed.");
		}
	}
	
}
