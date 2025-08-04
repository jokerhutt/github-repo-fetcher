package com.atipera.zadanie.githubrepofetcher.controller;
import com.atipera.zadanie.githubrepofetcher.response.RepositoryResponse;
import com.atipera.zadanie.githubrepofetcher.service.GithubRepoFetcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/github")
public class GithubRepoFetcherController {

    private GithubRepoFetcherService githubRepoFetcherService;

    public GithubRepoFetcherController (GithubRepoFetcherService githubRepoFetcherService) {
        this.githubRepoFetcherService = githubRepoFetcherService;
    }

    //*User 404 error is handled in Global Exception Handler
    @GetMapping(value = "/{username}/repositories")
    public ResponseEntity<List<RepositoryResponse>> getRepositories(@PathVariable String username) {
        List<RepositoryResponse> repositories = githubRepoFetcherService.fetchUsersRepositories(username);
        return ResponseEntity.ok(repositories);
    }

}
