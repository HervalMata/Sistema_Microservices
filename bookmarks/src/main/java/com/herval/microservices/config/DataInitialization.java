package com.herval.microservices.config;

import com.herval.microservices.model.Bookmark;
import com.herval.microservices.service.BookmarkService;
import com.herval.microservices.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/*
 * Criado Por Herval Mata em 29/08/2018
 */
@Component
public class DataInitialization implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(DataInitialization.class);

    @Autowired
    UserService userService;

    @Autowired
    BookmarkService bookmarkService;

    private static volatile boolean initialized = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (initialized) return;
        initialized = true;

        bookmarkService.addBookmark(new Bookmark("Packt publishing", "http://packtpub.com"));
        bookmarkService.addBookmark(new Bookmark("orchit GmbH", "http://orchit.de"));

        userService.addUser(new User("admin", "password",
                Arrays.asList(
                        new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_USER")
                )));
        userService.addUser(new User("admin", "password",
                Arrays.asList(
                        new SimpleGrantedAuthority("ROLE_ADMIN")
                )));
    }
}
