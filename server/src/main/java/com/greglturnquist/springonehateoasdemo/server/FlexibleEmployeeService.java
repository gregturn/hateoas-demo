package com.greglturnquist.springonehateoasdemo.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FlexibleEmployeeService {

	private static final List<FlexibleEmployee> EMPLOYEES = new ArrayList<>();

	private FlexibleEmployeeService() {

		create(new FlexibleEmployee("Frodo", "Baggins", "ring bearer"));
		create(new FlexibleEmployee("Bilbo", "Baggins", "burglar"));
	}

	/**
	 * Find all employees.
	 */
	List<FlexibleEmployee> findAll() {
		return EMPLOYEES;
	}

	/**
	 * Find by {@literal id}.
	 */
	FlexibleEmployee findById(int id) {
		return EMPLOYEES.get(id);
	}

	/**
	 * Find by {@literal name}.
	 */
	FlexibleEmployee findByName(String firstName, String lastName) {

		return EMPLOYEES.stream() //
				.filter(employee -> employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) //
				.findFirst() //
				.orElseThrow(() -> EmployeeNotFound.byName(firstName + " " + lastName));
	}

	/**
	 * Find by {@literal role}.
	 */
	FlexibleEmployee findByRole(String role) {

		return EMPLOYEES.stream() //
				.filter(employee -> employee.getRole().equals(role)) //
				.findFirst() //
				.orElseThrow(() -> EmployeeNotFound.byRole(role));
	}

	/**
	 * Create a new {@link BasicEmployee}.
	 */
	FlexibleEmployee create(FlexibleEmployee newEmployee) {

		FlexibleEmployee newlyCreatedEmployee = new FlexibleEmployee(EMPLOYEES.size(), newEmployee.getFirstName(),
				newEmployee.getLastName(), newEmployee.getRole());
		EMPLOYEES.add(newlyCreatedEmployee);
		return newlyCreatedEmployee;
	}

	/**
	 * Replace an existing {@link BasicEmployee}.
	 */
	FlexibleEmployee replace(FlexibleEmployee updatedEmployee, int id) {

		EMPLOYEES.remove(id);
		EMPLOYEES.add(id, updatedEmployee);
		return findById(id);
	}
}
