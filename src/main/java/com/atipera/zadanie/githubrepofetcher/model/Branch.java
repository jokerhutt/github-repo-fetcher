package com.atipera.zadanie.githubrepofetcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Branch (
        String name,
        @JsonProperty("commit") HeadCommit headCommit
) {}
