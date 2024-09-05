package io.security.springsecuritymaster;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class IndexController {

    private final SecurityContextService securityContextService;

    public IndexController(final SecurityContextService securityContextService) {
        this.securityContextService = securityContextService;
    }

    @GetMapping("/")
    public String index() {
        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy()
                .getContext();
        Authentication authentication = securityContext.getAuthentication();

        log.info("[Controller] Authentication = {}", authentication);
        securityContextService.securityContext();

        return "index";
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccess(@CurrentSecurityContext SecurityContext context) {
        return "logoutSuccess";
    }

}
