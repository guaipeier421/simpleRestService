package com.example.restservice.model;

import com.fasterxml.jackson.annotation.*;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"login", "avatar_url", "html_url", "name", "location", "email",
})
public class User {

    @JsonProperty("login")
    private String login;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private String location;
    @JsonProperty("email")
    private String email;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonCreator
    public User(
            @JsonProperty("login") final String login,
            @JsonProperty("avatar_url") final String avatarUrl,
            @JsonProperty("html_url") final String htmlUrl,
            @JsonProperty("name") final String name,
            @JsonProperty("location") final String location,
            @JsonProperty("email") final String email,
            @JsonProperty("created_at") final String createdAt) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.htmlUrl = htmlUrl;
        this.name = name;
        this.location = location;
        this.email = email;
        this.createdAt = createdAt;
    }

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }


    @JsonProperty("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }


    @JsonProperty("html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }


    @JsonProperty("name")
    public String getName() {
        return name;
    }


    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }
}