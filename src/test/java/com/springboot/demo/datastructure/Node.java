package com.springboot.demo.datastructure;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Node implements Comparable<Node> {

	private LocalDate localDate;

	private LocalDateTime localDateTime;

	private String groupId;

	private String data;

	
	public Node(String data) {
		super();

		long now  = System.currentTimeMillis();
		this.data = data;

		Date date    = new Date(now);
		Timestamp ts = new Timestamp(now);

		this.localDate     = date.toLocalDate();
		this.localDateTime = ts.toLocalDateTime();
	}

	
	public Node(String data, long timestamp) {
		super();

		long now  = timestamp;
		this.data = data;

		Date date    = new Date(now);
		Timestamp ts = new Timestamp(now);

		this.localDate     = date.toLocalDate();
		this.localDateTime = ts.toLocalDateTime();
	}

	
	@Override
	public int compareTo(Node target) {

		// LocalDate(YYYY/MM/DD)가 같을 경우, data로 순서를 비교한다.
		if (this.localDate.equals(target.localDate)) {
			return this.data.compareTo(target.data);
		} else if (!this.localDate.equals(target.localDate)) {
			// LocalDate(YYYY/MM/DD)가 다를경우, LocalDate로만 순서를 비교한다.
			if (this.localDate.isAfter(target.localDate)) {
				return 1;
			} else if (this.localDate.isBefore(target.localDate)) {
				return -1;
			}
		}

		return 0;

		// Default compareTo method.
		// if (this.localDate.isAfter(target.localDate)) {
		// return 1;
		// } else if (this.localDate.isBefore(target.localDate)) {
		// return -1;
		// } else if (this.localDate.isEqual(target.localDate)) {
		// return 0;
		// }
		// return 0;
	}

	
	public LocalDate getLocalDate() {
		return localDate;
	}

	
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}


	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	
	public String getGroupId() {
		return groupId;
	}

	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	
	public String getData() {
		return data;
	}

	
	public void setData(String data) {
		this.data = data;
	}

	
	@Override
	public String toString() {
		return "CompositeEvent [localDate=" + localDate + ", localDateTime="
				+ localDateTime + ", groupId=" + groupId + ", data=" + data + "]";
	}
}