package de.skillkiller.springoauthbackgroundjobs;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class Job {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OAuth2AuthorizedClientService clientService;
    private final JdbcTemplate jdbcTemplate;

    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        logger.info("Start scheduleFixedRateTask job");
        Long count = jdbcTemplate.queryForObject("SELECT count(PRINCIPAL_NAME) FROM OAUTH2_AUTHORIZED_CLIENT LIMIT 1", Long.class);
        if (count == null || count == 0) {
            logger.info("No client found. Skipping!");
            return;
        }
        String clientId = jdbcTemplate.queryForObject("SELECT PRINCIPAL_NAME FROM OAUTH2_AUTHORIZED_CLIENT LIMIT 1", String.class);
        logger.info("Select Client ID: {}", clientId);

        OAuth2AuthorizedClient authorizedClient = clientService.loadAuthorizedClient("twitch", clientId);

        logger.info("authorizedClient.getAccessToken().getTokenValue() = {}", authorizedClient.getAccessToken().getTokenValue());
        logger.info("authorizedClient.getAccessToken().getExpiresAt() = {}", authorizedClient.getAccessToken().getExpiresAt());
        logger.info("Is expired: {}", authorizedClient.getAccessToken().getExpiresAt().isBefore(Instant.now()));
    }

}
