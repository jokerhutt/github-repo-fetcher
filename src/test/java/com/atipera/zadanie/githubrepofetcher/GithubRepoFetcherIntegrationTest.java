package com.atipera.zadanie.githubrepofetcher;
import com.atipera.zadanie.githubrepofetcher.response.RepositoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubRepoFetcherIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldFetchRepositoriesAndBranchesForValidUser() {

        String username = "torvalds";

        ResponseEntity<RepositoryResponse[]> response = restTemplate.getForEntity("/api/github/" + username + "/repositories", RepositoryResponse[].class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotEmpty();

        for (RepositoryResponse repo : response.getBody()) {
            assertThat(repo.repositoryName()).isNotEmpty();
            assertThat(repo.ownerLogin()).isEqualTo(username);
            repo.branches().forEach(branch -> {
                assertThat(branch.name()).isNotEmpty();
                assertThat(branch.headCommit().sha()).isNotEmpty();
            });
        }

    }
}