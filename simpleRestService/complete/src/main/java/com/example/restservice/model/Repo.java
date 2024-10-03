package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 */
@JsonPropertyOrder({ "name", "url" })
public class Repo {
	@JsonProperty("name")
	private final String name;
	@JsonProperty("url")
	private final  String url;
	public Repo(
			@JsonProperty("name")
			final String name,
			@JsonProperty("name")
			final String url) {
		this.name = name;
		this.url = url;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

}