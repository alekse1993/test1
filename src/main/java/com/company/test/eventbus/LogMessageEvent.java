package com.company.test.eventbus;

import org.springframework.context.ApplicationEvent;

public class LogMessageEvent extends ApplicationEvent {
	private String message;

	public LogMessageEvent(Object source, String message) {
		super(source);
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
