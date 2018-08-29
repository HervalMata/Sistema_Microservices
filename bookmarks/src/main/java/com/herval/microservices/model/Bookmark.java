package com.herval.microservices.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

/*
 * Criado Por Herval Mata em 29/08/2018
 */
@Data
public class Bookmark {

    @Length(max = 255)
    @NotEmpty
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updateOn;
    @Length(max = 255)
    @NotEmpty
    @URL
    private String url;
    private UUID uuid;
    private int version;

    public Bookmark() {
    }

    public Bookmark(@Length(max = 255) @NotEmpty String description, @Length(max = 255) @NotEmpty @URL String url, UUID uuid) {
        this.description = description;
        this.url = url;
        this.uuid = uuid;
    }

    @JsonCreator
    public Bookmark(
            @JsonProperty("description") @Length(max = 255) @NotEmpty String description, 
            @JsonProperty("ceatedOn") LocalDateTime createdOn, 
            @JsonProperty("updateOn") LocalDateTime updateOn, 
            @JsonProperty("url") @Length(max = 255) @NotEmpty @URL String url,
            @JsonProperty("uuid") UUID uuid, int version) {
        this.description = description;
        this.createdOn = createdOn;
        this.updateOn = updateOn;
        this.url = url;
        this.uuid = uuid;
        this.version = version;
    }

    public Bookmark(UUID uuid, String description, String url, int version, LocalDateTime createdOn, LocalDateTime updateOn) {
    }

    public Bookmark(String p, String s) {
    }

    public Bookmark withUuid(UUID uuid){
        return new Bookmark(uuid, description, url, version, createdOn, updateOn);
    }

    public Bookmark withUrl(String newUrl){
        return new Bookmark(uuid, description, url, version, createdOn, updateOn);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public int getVersion() {
        return version;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
