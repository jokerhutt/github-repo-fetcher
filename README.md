# Github Repository Fetcher for Atipera
Basic API that returns all non-forked repository names and owner logins, as well as their respective branches, including each branches name and head commit's sha

## Overview
This Repository contains my solution to Atipera's Github repo/branch fetcher recruitment task. The instructions are:
As an api consumer, I would like to list all his github repositories, which are not forks. Information, which I require in the response, is:
```
Repository Name,
Owner Login,
For each branch it’s name and last commit sha

As an api consumer, given not existing github user, I would like to receive 404 response in such a format:
{
    “status”: ${responseCode}
    “message”: ${whyHasItHappened}
}
```

## Assumptions and notes
As per the requirements of not doing more than necessary, this solution:
- Only accounts for 404 User Errors. Other errors, such as rate limits, no repositories, invalid metadata, etc. are not accounted for.
- Does not use an authentication token. Please be mindful to not send too many requests at once.
- Only lists non-forked public repositories where the owner is the given user
- Only returns the top 30 repositories (due to explicit instruction to NOT include pagination anywhere)

## Requirements
Java 21+ 

## Running the Project
Clone the project
```
git clone https://github.com/jokerhutt/github-repo-fetcher.git
```
Run the Application
```
./mvnw spring-boot:run
```

## Usage
By default, the API is available at http://localhost:8080. If port 8080 is unavailable on your system, adjust it accordingly in the application.properties file.

Once you have the application running, you can fetch the repositories and branches of a given user using the following GET request:
```
GET /api/github/{username}/repositories
```
Example Request (Browser)
```
http://localhost:8080/api/github/torvalds/repositories
```
Example Request (cURL)
```
curl --request GET http://localhost:8080/api/github/torvalds/repositories
```
Example Request (Postman)
```
http://localhost:8080/api/github/torvalds/repositories
```

## Example Output

The following output uses torvalds as the user for the request. 
```
[
    {
        "repositoryName": "1590A",
        "ownerLogin": "torvalds",
        "branches": [
            {
                "name": "legacy",
                "commit": {
                    "sha": "5741b634093743850ba2eec9a2dc4d3361d79711"
                }
            },
            {
                "name": "legacy-gen2",
                "commit": {
                    "sha": "2ea787f964e6045bed43e681a78ce7772aad4c65"
                }
            },
            {
                "name": "main",
                "commit": {
                    "sha": "83adc5770c4cb4cb1f4539d099e02d13d2234610"
                }
            }
        ]
    },
    {
        "repositoryName": "linux",
        "ownerLogin": "torvalds",
        "branches": [
            {
                "name": "master",
                "commit": {
                    "sha": "6bcdbd62bd56e6d7383f9e06d9d148935b3c9b73"
                }
            }
        ]
    },
    {
        "repositoryName": "pesconvert",
        "ownerLogin": "torvalds",
        "branches": [
            {
                "name": "master",
                "commit": {
                    "sha": "d6d7001bc0c608514e46b2a1a2ed341269d2f226"
                }
            }
        ]
    },
    {
        "repositoryName": "test-tlb",
        "ownerLogin": "torvalds",
        "branches": [
            {
                "name": "master",
                "commit": {
                    "sha": "14321e3356829d4c6e7724b7b7fd406a4af31667"
                }
            }
        ]
    },
    {
        "repositoryName": "uemacs",
        "ownerLogin": "torvalds",
        "branches": [
            {
                "name": "master",
                "commit": {
                    "sha": "1cdcf9df88144049750116e36fe20c8c39fa2517"
                }
            }
        ]
    }
]
```

## Running Tests

The following will run an integration test which tests the "happy path" using torvalds as the given github user 

```
./mvnw test
```

## Errors

Given a non existent user, a 404 response will be returned in the following format:
```
{
    “status”: ${responseCode}
    “message”: ${whyHasItHappened}
}
```
