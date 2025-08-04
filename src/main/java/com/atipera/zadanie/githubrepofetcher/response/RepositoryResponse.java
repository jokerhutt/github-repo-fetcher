package com.atipera.zadanie.githubrepofetcher.response;

import com.atipera.zadanie.githubrepofetcher.model.Branch;

import java.util.List;

public record RepositoryResponse (
        String repositoryName,
        String ownerLogin,
        List<Branch> branches
) {}


