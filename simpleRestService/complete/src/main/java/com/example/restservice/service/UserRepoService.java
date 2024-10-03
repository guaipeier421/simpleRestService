package com.example.restservice.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.restservice.exception.ServiceError;
import com.example.restservice.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Business logic to transform Github response into relevant user data for our service. 
 * 
 */
@Service
public class UserRepoService {

    private static final Logger LOG = LoggerFactory.getLogger(UserRepoService.class);

    @Autowired
    private GithubApiService githubApiService;

    public UserRepos getUserRepo(String userName) throws ServiceError {
        User user = this.githubApiService.getUserFromGithubApi(userName);
        List<UserRepo> userRepos = this.githubApiService.getUserReposFromGithubApi(userName);
        return combineUserWithRepo(user, userRepos);
    }

    private String formatHelper(String dt) {
        // Parse the date string
        Instant instant = Instant.parse(dt);

        // Convert to LocalDateTime (you can specify the timezone if needed)
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));

        // Format to the desired output
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);

        return formattedDateTime;
    }

    private UserRepos combineUserWithRepo(final User user, final List<UserRepo> userRepos) {
        LOG.info("Getting output Response for user {}", user.getLogin());
        String dt = user.getCreatedAt();
        String formattedDateTime = formatHelper(dt);
        List<Repo> repos = new ArrayList<>();
        for (final UserRepo userRepo : userRepos) {
            Repo repo = new Repo(userRepo.getName(), userRepo.getHtmlUrl());
            repos.add(repo);
        }
        return new UserRepos(user.getLogin(), user.getName(), user.getAvatarUrl(), user.getLocation(), user.getEmail(), user.getHtmlUrl(), formattedDateTime, repos);
    }
}