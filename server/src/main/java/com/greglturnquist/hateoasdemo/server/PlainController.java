package com.greglturnquist.hateoasdemo.server;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlainController {

	private final BasicEmployeeService employeeService;

	public PlainController(BasicEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/api/employees")
	List<BasicEmployee> all() {
		return this.employeeService.findAll();
	}

	@PostMapping("/api/employees")
	BasicEmployee create(@RequestBody BasicEmployee newEmployee) {
		return this.employeeService.create(newEmployee);
	}

	@GetMapping("/api/employees/{id}")
	BasicEmployee one(@PathVariable int id) {
		return this.employeeService.findById(id);
	}

	@PutMapping("/api/employees/{id}")
	BasicEmployee update(@RequestBody BasicEmployee updatedEmployee, @PathVariable int id) {
		return this.employeeService.replace(updatedEmployee, id);
	}
}
