package com.greglturnquist.springonehateoasdemo;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class FlexibleEmployee {

	private final int id;
	private String firstName;
	private String lastName;
	private String role;

	FlexibleEmployee(String firstName, String lastName, String role) {
		this(-1, firstName, lastName, role);
	}

	@JsonCreator
	FlexibleEmployee(int id, String firstName, String lastName, String role) {

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

	// Getters and setters to provide backwards compatibility

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public void setName(String name) {

		String[] parts = name.split(" ");
		this.firstName = parts[0];
		this.lastName = parts[1];
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof FlexibleEmployee))
			return false;
		FlexibleEmployee that = (FlexibleEmployee) o;
		return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName)
				&& Objects.equals(role, that.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, role);
	}

	@Override
	public String toString() {

		return "FlexibleEmployee{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", role='" + role
				+ '\'' + '}';
	}
}
