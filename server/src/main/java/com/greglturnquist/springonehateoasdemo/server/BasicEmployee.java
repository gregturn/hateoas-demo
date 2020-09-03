package com.greglturnquist.springonehateoasdemo.server;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

class BasicEmployee {

	private final int id;
	private String name;
	private String role;

	BasicEmployee(String name, String role) {
		this(-1, name, role);
	}

	@JsonCreator
	BasicEmployee(int id, String name, String role) {

		this.id = id;
		this.name = name;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof BasicEmployee))
			return false;
		BasicEmployee that = (BasicEmployee) o;
		return id == that.id && Objects.equals(name, that.name) && Objects.equals(role, that.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, role);
	}

	@Override
	public String toString() {
		return "BasicEmployee{" + "id=" + id + ", name='" + name + '\'' + ", role='" + role + '\'' + '}';
	}
}
