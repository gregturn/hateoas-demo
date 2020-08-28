package com.greglturnquist.springonehateoasdemo;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HypermediaController {

	private final FlexibleEmployeeService employeeService;
	private final HypermediaEmployeeAssembler assembler;

	public HypermediaController(FlexibleEmployeeService employeeService, HypermediaEmployeeAssembler assembler) {

		this.employeeService = employeeService;
		this.assembler = assembler;
	}

	@GetMapping("/rest/employees")
	CollectionModel<EntityModel<FlexibleEmployee>> all() {
		return assembler.toCollectionModel(employeeService.findAll());
	}

	@PostMapping("/rest/employees")
	EntityModel<FlexibleEmployee> create(@RequestBody FlexibleEmployee newEmployee) {
		return assembler.toModel(employeeService.create(newEmployee));
	}

	@GetMapping("/rest/employees/{id}")
	EntityModel<FlexibleEmployee> one(@PathVariable int id) {
		return assembler.toModel(employeeService.findById(id));
	}

	@PutMapping("/rest/employees/{id}")
	EntityModel<FlexibleEmployee> update(@RequestBody FlexibleEmployee updatedEmployee, @PathVariable int id) {
		return assembler.toModel(employeeService.replace(updatedEmployee, id));
	}

}
