package com.atipera.zadanie.githubrepofetcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Branch (
        String name,
        @JsonProperty("commit") HeadCommit headCommit
) {}

