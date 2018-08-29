package com.herval.microservices.service;

import com.herval.microservices.model.Bookmark;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


/*
 * Criado Por Herval Mata em 29/08/2018
 */
public class BookmarkRowMapper implements RowMapper<Bookmark> {
    @Override
    public Bookmark mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Bookmark(
                rs.getObject("uuid", UUID.class),
                rs.getString("description"),
                rs.getString("url"),
                rs.getInt("version"),
                rs.getTimestamp("createdOn").toLocalDateTime(),
                rs.getTimestamp("updatedOn").toLocalDateTime()
        );
    }
}
