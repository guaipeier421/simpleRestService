package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"name", "html_url"})
public class UserRepo {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("html_url")
    private final String htmlUrl;

    @JsonCreator
    public UserRepo(
            @JsonProperty("name") final String name,
            @JsonProperty("html_url")
            final String htmlUrl) {
        this.name = name;
        this.htmlUrl = htmlUrl;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }
}
