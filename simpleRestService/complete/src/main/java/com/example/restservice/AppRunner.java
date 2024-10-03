package com.example.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.restservice.caching.OutputResponseRepository;


/**
 * Used as part of the caching test flow.
 *
 * To disable:
 * Comment out @Cacheable annotation in OutputResponseRepositoryImpl.java
 * Comment out @EnableCaching annotation in RestServiceApplication.java
 * Look for logs in the Console "com.example.restservice.AppRunner: Fetching User Repos->..." 
 * It will run 5 times and there will be a 3 seconds delay in between each calls.
 */

@Component
public class AppRunner implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(AppRunner.class);

  private final OutputResponseRepository outputResponseRepository;

  public AppRunner(OutputResponseRepository outputResponseRepository) {
    this.outputResponseRepository = outputResponseRepository;
  }

  @Override
  public void run(String... args) {
    LOGGER.info(".... Fetching User Repos");
    LOGGER.info("Fetching User Repos -->" + outputResponseRepository.getByUserName("octocat"));
    LOGGER.info("Fetching User Repos -->" + outputResponseRepository.getByUserName("octocat"));
    LOGGER.info("Fetching User Repos -->" + outputResponseRepository.getByUserName("octocat"));
    LOGGER.info("Fetching User Repos -->" + outputResponseRepository.getByUserName("octocat"));
    LOGGER.info("Fetching User Repos -->" + outputResponseRepository.getByUserName("octocat"));
  }
}