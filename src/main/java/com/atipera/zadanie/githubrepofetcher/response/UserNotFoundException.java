package com.atipera.zadanie.githubrepofetcher.response;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}