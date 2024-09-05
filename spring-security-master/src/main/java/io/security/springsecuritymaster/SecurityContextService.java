package io.security.springsecuritymaster;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SecurityContextService {

    public void securityContext() {
        // 애플리케이션 전체에서 SecurityContext 접근 가능
        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy()
                .getContext();
        Authentication authentication = securityContext.getAuthentication();

        log.info("[Service] Authentication = {}", authentication);
    }

}
