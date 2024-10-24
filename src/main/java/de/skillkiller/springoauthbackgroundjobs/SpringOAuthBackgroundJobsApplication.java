package de.skillkiller.springoauthbackgroundjobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringOAuthBackgroundJobsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringOAuthBackgroundJobsApplication.class, args);
    }

}
