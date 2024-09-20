package io.security.springsecuritymaster.mvc;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/*
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final HttpSessionSecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @PostMapping("/login")
    public Authentication login(
            @RequestBody final LoginRequest loginRequest,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) {
        // 사용자 이름과 비밀번호를 담은 인증 객체를 생성
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.username(), loginRequest.password());
        // 인증을 시도하고 최종 인증 결과를 반환
        Authentication authentication = authenticationManager.authenticate(token);

        // 인증 정보 유지를 위해 SecurityContext 생성 및 저장
        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy().createEmptyContext();
        securityContext.setAuthentication(authentication); // 인증 결과를 컨텍스트에 저장
        // 컨텍스트를 ThreadLocal 에 저장
        SecurityContextHolder.getContextHolderStrategy().setContext(securityContext);

        // 컨텍스트를 세션에 저장해서 인증 상태를 영속
        securityContextRepository.saveContext(securityContext, request, response);
        return authentication;
    }

}
*/
