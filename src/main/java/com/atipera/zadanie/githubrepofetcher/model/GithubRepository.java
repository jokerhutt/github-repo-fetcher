package com.atipera.zadanie.githubrepofetcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GithubRepository (
        String name,
        RepositoryOwner owner,
        @JsonProperty("fork") boolean isForked,
        List<Branch> branches

) {}