package com.herval.microservices.model;

import com.herval.microservices.rest.BookmarkController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/*
 * Criado Por Herval Mata em 29/08/2018
 */
public class BookmarkResourceAssembler implements ResourceAssembler<Bookmark, Resource<Bookmark>> {
    @Override
    public Resource<Bookmark> toResource(Bookmark entity) {
        return new Resource<>(entity, linkTo(methodOn(BookmarkController.class).getBookmark(entity.getUuid())).withSelfRel());
    }
}
