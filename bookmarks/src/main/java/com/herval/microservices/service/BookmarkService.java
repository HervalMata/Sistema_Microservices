package com.herval.microservices.service;

import com.herval.microservices.model.Bookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

/*
 * Criado Por Herval Mata em 29/08/2018
 */
@Service
@Transactional
public class BookmarkService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UUID addBookmark(Bookmark bookmark){
        UUID uuid = UUID.randomUUID();
        jdbcTemplate.update("INSERT INTO bookmark (url, uuid, version, description)" +
                " VALUES (?, ?, ?, ?)", bookmark.getUrl(), uuid, bookmark.getDescription());
        System.out.println("http://localhost:8080/bookmark/" + uuid);
        return uuid;
    }

    public Bookmark find(UUID id) {
        return jdbcTemplate.queryForObject("SELECT * FROM bookmark WHERE uuid=?", new BookmarkRowMapper(), id);
    }

    public Bookmark update(Bookmark bookmark) {
        find(bookmark.getUuid());
        int update = jdbcTemplate.update("UPDATE bookmark SET url = ?, description =?, " +
                " updatedOn = current_timestamp(), version = version + 1 " +
                " WHERE uuid = ? AND version = ?" ,
                bookmark.getUrl(), bookmark.getDescription(), bookmark.getUuid(), bookmark.getVersion());
        if (update != 1) throw new OptimisticLockingFailureException("Stale update detected for " + bookmark.getUuid());
        return find(bookmark.getUuid());
    }

    public void delete(UUID id) {
        if (jdbcTemplate.update("DELETE FROM bookmark WHERE uuid = ?", id) != 1)
            throw new NotModifiedDataAccessException("Bookmark already gone");
    }

    public Iterable<Bookmark> findAll() {
        return jdbcTemplate.query("SELECT FROM bookmark",
                new BookmarkRowMapper());
    }

    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    private class NotModifiedDataAccessException extends DataAccessException {
        public NotModifiedDataAccessException(String msg) {
            super(msg);
        }
    }
}
