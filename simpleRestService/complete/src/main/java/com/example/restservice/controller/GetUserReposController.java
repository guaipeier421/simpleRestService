package com.example.restservice.controller;

import com.example.restservice.exception.NoFoundError;
import com.example.restservice.exception.ServiceError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.example.restservice.model.UserRepos;
import com.example.restservice.service.UserRepoService;

/**
 *
 */
@RestController
public class GetUserReposController {
    private static final Logger LOG = LoggerFactory.getLogger(GetUserReposController.class);
    @Autowired
    private UserRepoService userRepoService;

    @Operation(summary = "Retrieve github repositories by user name", description = "Get a list of github repositories by specifying user name.", tags = {
            "Demo"})
    @ApiResponses({@ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = UserRepos.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/repos/{userName}")
    public ResponseEntity<UserRepos> getReposByUserName(@PathVariable("userName") String userName) {
        LOG.debug("Called get Teams for Person {} service", userName);
        try {
            return ResponseEntity.ok(this.userRepoService.getUserRepo(userName));
        } catch (final ServiceError e) {
            if (e instanceof NoFoundError) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.internalServerError().body(null);
            }
        }
    }
}