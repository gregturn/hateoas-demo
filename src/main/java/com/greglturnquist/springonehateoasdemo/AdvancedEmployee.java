package com.greglturnquist.springonehateoasdemo;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

class AdvancedEmployee {

	private final int id;
	private String firstName;
	private String lastName;
	private String role;

	AdvancedEmployee(String firstName, String lastName, String role) {
		this(-1, firstName, lastName, role);
	}

	@JsonCreator
	AdvancedEmployee(int id, String firstName, String lastName, String role) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
