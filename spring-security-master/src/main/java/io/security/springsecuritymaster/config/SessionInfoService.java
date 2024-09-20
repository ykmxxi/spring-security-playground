package io.security.springsecuritymaster.config;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionInfoService {

    private final SessionRegistry sessionRegistry;

    public void sessionInfo() {
        sessionRegistry.getAllPrincipals()
                .forEach(principal -> sessionRegistry.getAllSessions(principal, true)
                        .forEach(sessionInformation -> {
                            if (sessionInformation.isExpired()) {
                                log.info("사용자: {}, 만료된 세션 ID: {}", principal, sessionInformation.getSessionId());
                            } else {
                                log.info("사용자: {}, 세션 ID: {}, 최종 요청 시간: {}", principal,
                                        sessionInformation.getSessionId(),
                                        sessionInformation.getLastRequest());
                            }
                        })
                );
//        for (Object principal : sessionRegistry.getAllPrincipals()) {
//            List<SessionInformation> activeSessions = sessionRegistry.getAllSessions(principal, true);
//            for (SessionInformation sessionInformation : activeSessions) {
//                if (sessionInformation.isExpired()) {
//                    log.info("사용자: {}, 만료된 세션 ID: {}", principal, sessionInformation.getSessionId());
//                    continue;
//                }
//                log.info("사용자: {}, 세션 ID: {}, 최종 요청 시간: {}",
//                        principal, sessionInformation.getSessionId(), sessionInformation.getLastRequest());
//            }
//        }
    }

}
