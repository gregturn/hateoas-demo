package com.greglturnquist.springonehateoasdemo.server;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class HypermediaEmployeeAssembler implements SimpleRepresentationModelAssembler<FlexibleEmployee> {

	@Override
	public void addLinks(EntityModel<FlexibleEmployee> resource) {

		int id = resource.getContent().getId();

		Link selfLink = linkTo(methodOn(HypermediaController.class).one(id)).withSelfRel();
		Affordance update = afford(methodOn(HypermediaController.class).update(null, id));

		resource.add(selfLink.andAffordance(update));

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
