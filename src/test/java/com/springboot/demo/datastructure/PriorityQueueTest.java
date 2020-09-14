package com.springboot.demo.datastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

public class PriorityQueueTest {

	@Test
	public void priorityQueueTest() throws InterruptedException {

		Node node1 = new Node("event1");
		Node node2 = new Node("event2");
		Node node3 = new Node("event3");
		Thread.sleep(10);
		Node node4 = new Node("event3");
		Thread.sleep(10);
		Node node5 = new Node("event3");
		Thread.sleep(10);
		
		LocalDateTime localDateTime = LocalDateTime.now();
		localDateTime = localDateTime.minusDays(1);
		
		Node node6  = new Node("event3b", Timestamp.valueOf(localDateTime).getTime());
		Thread.sleep(10);
		Node node7  = new Node("event3a", Timestamp.valueOf(localDateTime).getTime());
		Thread.sleep(10);
		Node node8  = new Node("event3b");
		Thread.sleep(10);
		Node node9  = new Node("event3a");
		Node node10 = new Node("abc");
		Node node11 = new Node("abc", Timestamp.valueOf(localDateTime).getTime());

		PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();

		priorityQueue.offer(node1);
		priorityQueue.offer(node2);
		priorityQueue.offer(node3);
		priorityQueue.offer(node4);
		priorityQueue.offer(node5);
		priorityQueue.offer(node6);
		priorityQueue.offer(node7);
		priorityQueue.offer(node8);
		priorityQueue.offer(node9);
		priorityQueue.offer(node10);
		priorityQueue.offer(node11);

		System.out.println("===================================== Ordered data =====================================");

		while (!priorityQueue.isEmpty()) {
			Node node = priorityQueue.poll();
			System.out
					.println("localDateTime : [" + node.getLocalDateTime().toString() + "]\t" + "localDate : "
							+ node.getLocalDate().toString() + "\t" + "data : " + node.getData());
		}
	}

}
