# Spring Boot Twitch OAuth2 Background Job problem

This repository is created to clarify a specific question and offers no additional value beyond that. The project
demonstrates a problem regarding OAuth2 authentication with Twitch and the handling of the OAuth2 `AuthorizedClient`
within a Spring Boot application.

## Problem Description

This project implements OAuth2 authentication with Twitch. After the user authenticates via Twitch, the
`AuthorizedClient` is stored. The goal is to use the stored `AuthorizedClient` later in a background job to perform
actions on behalf of the user.

However, the problem occurs when the access token within the `AuthorizedClient` expires. Spring Security seems not to
automatically refresh the token when the background job runs again, which leads to issues with executing actions that
require a valid token.

## How to Use

To run and test this project, follow these steps:

1. Create a private profile and set the following properties with your Twitch credentials:
   ```properties
   spring.security.oauth2.client.registration.twitch.client-id=<your-client-id>
   spring.security.oauth2.client.registration.twitch.client-secret=<your-client-secret>
   ```
2. Start the project.
3. Open a browser and navigate to: [http://localhost:8080/me](http://localhost:8080/me)
4. Check the console logs for the output of the background job execution.
5. Wait for the access token to expire or manually set the token to expired in the H2 database by updating the following
   columns in the relevant table:
    - `ACCESS_TOKEN_ISSUED_AT`
    - `ACCESS_TOKEN_EXPIRES_AT`
    - `REFRESH_TOKEN_ISSUED_AT`
6. Wait for the next background job execution.
7. Observe that the client has not been refreshed and the actions fail due to the expired token.

