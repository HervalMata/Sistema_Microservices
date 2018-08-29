package com.herval.microservices.rest;

import com.herval.microservices.model.Bookmark;
import com.herval.microservices.model.BookmarkResourceAssembler;
import com.herval.microservices.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.UUID;


/*
 * Criado Por Herval Mata em 29/08/2018
 */
@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping(value = "bookmark", produces = {"application/hal+json;charset=UTF-8", MediaType.APPLICATION_JSON_UTF8_VALUE})
public class BookmarkController {

    @Autowired
    BookmarkResourceAssembler assembler;

    @Autowired
    BookmarkService bookmarkService;

    @GetMapping("{id}")
    public Resource<Bookmark> getBookmark(@PathVariable UUID id){
        return assembler.toResource(bookmarkService.find(id));
    }

    @PostMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Resource<Bookmark> updateBookmark(@PathVariable UUID id, @RequestBody @Valid Bookmark bookmark){
        return assembler.toResource(bookmarkService.update(bookmark.withUuid(id)));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBookmark(@PathVariable UUID id){
        try {
            bookmarkService.delete(id);
            return ResponseEntity.status(HttpStatus.GONE).build();
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }
}
