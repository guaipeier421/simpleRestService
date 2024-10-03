package com.example.restservice.caching;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.restservice.model.UserRepos;

/**
 * Fake caching service to prove out slow service/retry handling.
 * 
 * To disable:
 * Comment out @Cacheable annotation in OutputResponseRepositoryImpl.java
 * Comment out @EnableCaching annotation in RestServiceApplication.java
 * Look for logs in the Console "com.example.restservice.AppRunner: Fetching User Repos->..." 
 * It will run 5 times and there will be a 3 seconds delay in between each calls.
 */
@Component
public class OutputResponseRepositoryImpl implements OutputResponseRepository {

    /**
     *
     * @param userName
     * @return
     */
    @Override
    @Cacheable("repositories")
    public UserRepos getByUserName(String userName) {
        simulateSlowService();
        return new UserRepos();
    }

    /**
     * This function simulate a 3 seconds delay to test caching
     * 
     * This is for the purposes of demonstration in this project, in a real product
     * this would be something handled in integration or load testing if at all
     */
    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}