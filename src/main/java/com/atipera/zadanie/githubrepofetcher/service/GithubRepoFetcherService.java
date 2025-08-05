package com.atipera.zadanie.githubrepofetcher.service;
import com.atipera.zadanie.githubrepofetcher.model.Branch;
import com.atipera.zadanie.githubrepofetcher.model.GithubRepository;
import com.atipera.zadanie.githubrepofetcher.response.RepositoryResponse;
import com.atipera.zadanie.githubrepofetcher.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubRepoFetcherService {

    private final RestTemplate restTemplate;

    @Value("${github.api.url}")
    private String githubApiUrl;

    @Autowired
    public GithubRepoFetcherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RepositoryResponse> fetchUsersRepositories (String username) {

        String url = UriComponentsBuilder.fromUriString(githubApiUrl)
                .path("/users/{username}/repos")
                .queryParam("type", "owner")
                .buildAndExpand(username)
                .toUriString();

        GithubRepository[] repositories;

            try {
                repositories = restTemplate.getForObject(url, GithubRepository[].class);
            }
            catch (HttpClientErrorException.NotFound e) {
                throw new UserNotFoundException("Username of " + username + " was not found");
            }
            return Arrays.stream(repositories)
                    .filter(repository -> !repository.isForked())
                    .map(repo -> {
                        System.out.println("Repo name: " + repo.name());
                        List<Branch> branches = getRepositoryBranches(username, repo.name());
                        return new RepositoryResponse(repo.name(), repo.owner().login(), branches);
                    })
                    .collect(Collectors.toList());
    }

    private List<Branch> getRepositoryBranches (String username, String repositoryName) {

        String url = UriComponentsBuilder.fromUriString(githubApiUrl)
                .path("/repos/{username}/{repositoryName}/branches")
                .queryParam("type", "all")
                .buildAndExpand(username, repositoryName)
                .toUriString();

        Branch[] branches = restTemplate.getForObject(url, Branch[].class);

        return Arrays.stream(branches)

                .map(branch -> new Branch(branch.name(), branch.headCommit()))
                .peek(System.out::println)
                .collect(Collectors.toList());
    }


}
