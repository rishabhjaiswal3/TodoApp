package com.data;
public class Client {
	private String name;
	private String email;
	private String password;
	public Client(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public Client(String name, String email) {
		this.name = name;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "client [name=" + name + ", email=" + email + "]";
	}
}

