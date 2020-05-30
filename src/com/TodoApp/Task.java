package com.TodoApp;

public class Task {
	private String task;
	private int id;
	
	public Task(String task, int id) {
		this.task = task;
		this.id = id;
	}
	public Task(String task) {
		this.task = task;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Task [task=" + task + "]";
	}
	
}
