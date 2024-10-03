package com.example.restservice.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 */
@JsonPropertyOrder({"user_name", "display_name", "avatar", "geo_location", "email", "url", "created_at", "repos"})
public class UserRepos {
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("geo_location")
    private String geoLocation;
    @JsonProperty("email")
    private String email;
    @JsonProperty("url")
    private String url;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("repos")
    private List<Repo> repos;

    @JsonCreator
    public UserRepos() {
    }

    @JsonCreator
    public UserRepos(final String userName, final String displayName, final String avatar, final String geoLocation, final String email, final String url, final String createdAt, final List<Repo> repos) {
        this.userName = userName;
        this.displayName = displayName;
        this.avatar = avatar;
        this.geoLocation = geoLocation;
        this.email = email;
        this.url = url;
        this.createdAt = createdAt;
        this.repos = repos;
    }

    @JsonProperty("user_name")
    public String getUserName() {
        return userName;
    }


    @JsonProperty("display_name")
    public String getDisplayName() {
        return displayName;
    }


    @JsonProperty("avatar")
    public String getAvatar() {
        return avatar;
    }


    @JsonProperty("geo_location")
    public String getGeoLocation() {
        return geoLocation;
    }


    @JsonProperty("email")
    public String getEmail() {
        return email;
    }


    @JsonProperty("url")
    public String getUrl() {
        return url;
    }


    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }


    @JsonProperty("repos")
    public List<Repo> getRepos() {
        return repos;
    }
}