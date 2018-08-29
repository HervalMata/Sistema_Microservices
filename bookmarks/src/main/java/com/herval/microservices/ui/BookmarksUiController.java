package com.herval.microservices.ui;

import com.herval.microservices.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Criado Por Herval Mata em 29/08/2018
 */
@Controller
@RequestMapping("/")
public class BookmarksUiController {

    @Autowired
    BookmarkService bookmarkService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String listOfBookmarks(Model model){
        model.addAttribute("bookmarks", bookmarkService.findAll());
        return "bookmarks/list";
    }
}
