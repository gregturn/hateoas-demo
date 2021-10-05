package com.greglturnquist.hateoasdemo.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AdvancedEmployeeService {

	private static final List<AdvancedEmployee> EMPLOYEES = new ArrayList<>();

	private AdvancedEmployeeService() {

		create(new AdvancedEmployee("Frodo", "Baggins", "ring bearer"));
		create(new AdvancedEmployee("Bilbo", "Baggins", "burglar"));
	}

	/**
	 * Find all employees.
	 */
	List<AdvancedEmployee> findAll() {
		return EMPLOYEES;
	}

	/**
	 * Find by {@literal id}.
	 */
	AdvancedEmployee findById(int id) {
		return EMPLOYEES.get(id);
	}

	/**
	 * Find by {@literal name}.
	 */
	AdvancedEmployee findByName(String firstName, String lastName) {

		return EMPLOYEES.stream() //
				.filter(employee -> employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) //
				.findFirst() //
				.orElseThrow(() -> EmployeeNotFound.byName(firstName + " " + lastName));
	}

	/**
	 * Find by {@literal role}.
	 */
	AdvancedEmployee findByRole(String role) {

		return EMPLOYEES.stream() //
				.filter(employee -> employee.getRole().equals(role)) //
				.findFirst() //
				.orElseThrow(() -> EmployeeNotFound.byRole(role));
	}

	/**
	 * Create a new {@link BasicEmployee}.
	 */
	AdvancedEmployee create(AdvancedEmployee newEmployee) {

		AdvancedEmployee newlyCreatedEmployee = new AdvancedEmployee(EMPLOYEES.size(), newEmployee.getFirstName(),
				newEmployee.getLastName(), newEmployee.getRole());
		EMPLOYEES.add(newlyCreatedEmployee);
		return newlyCreatedEmployee;
	}

	/**
	 * Replace an existing {@link BasicEmployee}.
	 */
	AdvancedEmployee replace(AdvancedEmployee updatedEmployee, int id) {

		EMPLOYEES.remove(id);
		EMPLOYEES.add(id, updatedEmployee);
		return findById(id);
	}
}
