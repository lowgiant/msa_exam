package com.sparta.msa_exam.auth.controller;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.sparta.msa_exam.auth.common.config.token.service.TokenService;
import com.sparta.msa_exam.auth.domain.user.enums.Role;
import com.sparta.msa_exam.auth.dto.TokenResponse;
import com.sparta.msa_exam.auth.exception.ErrorCode;
import com.sparta.msa_exam.auth.common.MsaApiResponse;
import com.sparta.msa_exam.auth.domain.user.dto.SignUpRequest;
import com.sparta.msa_exam.auth.domain.user.service.UserService;
import com.sparta.msa_exam.auth.dto.SignInRequest;
import com.sparta.msa_exam.auth.domain.user.dto.MsaUser;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;
    private final TokenService tokenService;


    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest req) {
        userService.authProcess(req);
        return ResponseEntity.ok("가입이 성공 했습니다.");
    }


    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {
        try {UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);
            MsaUser principal = (MsaUser) authentication.getPrincipal();
            String username = principal.getUsername();

            TokenResponse tokenResponse = tokenService.upsertToken(username,
                Role.CUSTOMER.getKey());

            return ResponseEntity.ok(MsaApiResponse.ok(tokenResponse));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(MsaApiResponse.fail(ErrorCode.AUTHENTICATION_FAILED, "아이디 패스워드 확인하세요"));
        }
    }


    @PostMapping("/auth/reissue")
    public MsaApiResponse<?> reissue(HttpServletRequest httpServletRequest){
        String refreshToken = httpServletRequest.getHeader("refresh_token");
        String accessToken = httpServletRequest.getHeader("Authorization");

        if (StringUtils.isBlank(refreshToken) || StringUtils.isBlank(accessToken)) {
            return MsaApiResponse.fail(ErrorCode.DEFAULT_ERROR, "토큰이 없습니다.");
        }

        return MsaApiResponse.ok(tokenService.reissueToken(accessToken, refreshToken));
    }




}




