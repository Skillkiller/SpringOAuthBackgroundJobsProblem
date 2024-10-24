package de.skillkiller.springoauthbackgroundjobs;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }

    @GetMapping("me")
    public String me(Authentication auth) {
        return "auth.getName() = " + auth.getName();
    }
}
