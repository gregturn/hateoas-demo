package com.greglturnquist.springonehateoasdemo.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlexibleController {

	private final FlexibleEmployeeService employeeService;

	public FlexibleController(FlexibleEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/flexible/employees")
	List<FlexibleEmployee> all() {
		return this.employeeService.findAll();
	}

	@PostMapping("/flexible/employees")
	FlexibleEmployee create(@RequestBody FlexibleEmployee newEmployee) {
		return this.employeeService.create(newEmployee);
	}

	@GetMapping("/flexible/employees/{id}")
	FlexibleEmployee one(@PathVariable int id) {
		return this.employeeService.findById(id);
	}

	@PutMapping("/flexible/employees/{id}")
	FlexibleEmployee update(@RequestBody FlexibleEmployee updatedEmployee, @PathVariable int id) {
		return this.employeeService.replace(updatedEmployee, id);
	}

}
