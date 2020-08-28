package com.greglturnquist.springonehateoasdemo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class HypermediaEmployeeAssembler implements SimpleRepresentationModelAssembler<FlexibleEmployee> {

	@Override
	public void addLinks(EntityModel<FlexibleEmployee> resource) {

		resource.add( //
				linkTo(methodOn(HypermediaController.class).one(resource.getContent().getId())).withSelfRel() //
						.andAffordance(afford(methodOn(HypermediaController.class).update(null, resource.getContent().getId()))));

		// Add links for workflows here (conditional or unconditional)!

		resource.add(linkTo(methodOn(HypermediaController.class).all()).withRel("employees"));
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<FlexibleEmployee>> resources) {

		resources.add( //
				linkTo(methodOn(HypermediaController.class).all()).withSelfRel() //
						.andAffordance(afford(methodOn(HypermediaController.class).create(null))));

		// Add links for aggregate root workflows here!
	}
}
