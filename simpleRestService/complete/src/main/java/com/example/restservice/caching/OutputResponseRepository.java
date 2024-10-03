package com.example.restservice.caching;

import com.example.restservice.model.UserRepos;

/**
 *
 */
public interface OutputResponseRepository {

    /**
     * @param userName
     * @return
     */
    UserRepos getByUserName(String userName);
}