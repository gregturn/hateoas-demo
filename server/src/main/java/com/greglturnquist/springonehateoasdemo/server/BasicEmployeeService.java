package com.greglturnquist.springonehateoasdemo.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BasicEmployeeService {

	private static final List<BasicEmployee> EMPLOYEES = new ArrayList<>();

	private BasicEmployeeService() {

		create(new BasicEmployee("Frodo", "ring bearer"));
		create(new BasicEmployee("Bilbo", "burglar"));
	}

	/**
	 * Find all employees.
	 */
	List<BasicEmployee> findAll() {
		return EMPLOYEES;
	}

	/**
	 * Find by {@literal id}.
	 */
	BasicEmployee findById(int id) {
		return EMPLOYEES.get(id);
	}

	/**
	 * Find by {@literal name}.
	 */
	BasicEmployee findByName(String name) {

		return EMPLOYEES.stream() //
				.filter(employee -> employee.getName().equals(name)) //
				.findFirst() //
				.orElseThrow(() -> EmployeeNotFound.byName(name));
	}

	/**
	 * Find by {@literal role}.
	 */
	BasicEmployee findByRole(String role) {

		return EMPLOYEES.stream() //
				.filter(employee -> employee.getRole().equals(role)) //
				.findFirst() //
				.orElseThrow(() -> EmployeeNotFound.byRole(role));
	}

	/**
	 * Create a new {@link BasicEmployee}.
	 */
	BasicEmployee create(BasicEmployee newEmployee) {

		BasicEmployee newlyCreatedEmployee = new BasicEmployee(EMPLOYEES.size(), newEmployee.getName(),
				newEmployee.getRole());
		EMPLOYEES.add(newlyCreatedEmployee);
		return newlyCreatedEmployee;
	}

	/**
	 * Replace an existing {@link BasicEmployee}.
	 */
	BasicEmployee replace(BasicEmployee updatedEmployee, int id) {

		EMPLOYEES.remove(id);
		EMPLOYEES.add(id, updatedEmployee);
		return findById(id);
	}
}
