package com.greglturnquist.springonehateoasdemo.server;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlainControllerV2 {

	private final AdvancedEmployeeService employeeService;

	public PlainControllerV2(AdvancedEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/api/v2/employees")
	List<AdvancedEmployee> all() {
		return this.employeeService.findAll();
	}

	@PostMapping("/api/v2/employees")
	AdvancedEmployee create(@RequestBody AdvancedEmployee newEmployee) {
		return this.employeeService.create(newEmployee);
	}

	@GetMapping("/api/v2/employees/{id}")
	AdvancedEmployee one(@PathVariable int id) {
		return this.employeeService.findById(id);
	}

	@PutMapping("/api/v2/employees/{id}")
	AdvancedEmployee update(@RequestBody AdvancedEmployee updatedEmployee, @PathVariable int id) {
		return this.employeeService.replace(updatedEmployee, id);
	}
}
