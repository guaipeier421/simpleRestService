package com.example.restservice.service;

import java.text.MessageFormat;
import java.util.List;

import com.example.restservice.exception.NoFoundError;
import com.example.restservice.exception.ServiceError;
import com.example.restservice.exception.UnexpectedError;
import com.example.restservice.model.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.restservice.model.User;

/**
 *  Business logic to handle interaction with GitHub rest API.
 * 
 *  With more time actual Github API calls could be separated into data layer 
 *  with interface definitions in domain
 */

@Service
public class GithubApiService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(GithubApiService.class);

    private final RestTemplate restTemplate;

    public GithubApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public User getUserFromGithubApi(String username) throws ServiceError {
        LOG.info("Retrieving user information from GitHub API for user {}", username);
        String apiUrl = "https://api.github.com/users/" + username;

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                LOG.info("SUCCESS Retrieving user from GitHub API for user {}", username);

                return OBJECT_MAPPER.readValue(response.getBody(), User.class);
            } catch (final JsonProcessingException e) {
                throw new UnexpectedError(MessageFormat.format("Cannot parse response {0}", e.getMessage()), e);
            }
        } else if (response.getStatusCode().is4xxClientError()) {
            throw new NoFoundError(MessageFormat.format("not user found by name: {0}", username));
        } else {
            assert response.getStatusCode().is5xxServerError() : response.getStatusCode();
            throw new UnexpectedError(MessageFormat.format("getUserFromGithubApi API call failed: {0}", response.getBody()));
        }
    }

    public List<UserRepo> getUserReposFromGithubApi(String userName) throws ServiceError {
        LOG.info("Retrieving repositories from GitHub API for user {}", userName);
        String apiUrl = "https://api.github.com/users/" + userName + "/repos";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                LOG.info("SUCCESS Retrieving repositories from GitHub API for user {}", userName);
                return OBJECT_MAPPER.readValue(response.getBody(), new TypeReference<List<UserRepo>>() {
                });
            } catch (final JsonProcessingException e) {
                throw new UnexpectedError(MessageFormat.format("Cannot parse response {0}", e.getMessage()), e);
            }
        } else {
            throw new UnexpectedError(MessageFormat.format("getUserReposFromGithubApi API call failed: {0}, {1}", response.getStatusCode(), response.getBody()));
        }
    }
}