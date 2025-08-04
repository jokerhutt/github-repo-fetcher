package com.atipera.zadanie.githubrepofetcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubRepoFetcherService {

    private final RestTemplate restTemplate;

    @Autowired
    public GithubRepoFetcherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void fetchUsersRepositories (String username) {

        String path = "/users/" +  username + "/repos?type=all";

        try {



        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException(e.getMessage());
        }




    }




}
