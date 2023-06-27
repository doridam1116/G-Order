package com.gaubiz.gorder.api.account.controller;

import com.gaubiz.gorder.api.account.model.Account;
import com.gaubiz.gorder.api.account.model.Sub;
import com.gaubiz.gorder.api.account.service.AccountService;
import com.gaubiz.gorder.api.validation.Groups;
import com.gaubiz.gorder.msg.HttpStatusMsg;
import com.gaubiz.gorder.security.auth.PrincipalDetailService;
import com.gaubiz.gorder.security.auth.PrincipalDetails;
import com.gaubiz.gorder.security.jwt.JwtProperties;
import com.gaubiz.gorder.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;


@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final PrincipalDetailService principalDetailService;
    private final JwtProvider jwtProvider;

    public AccountController(AccountService accountService, PrincipalDetailService principalDetailService, JwtProvider jwtProvider) {
        this.accountService = accountService;
        this.principalDetailService = principalDetailService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(
            // Account 객체 유효성 검사
            @Validated(Groups.registerGroup.class) @RequestBody Account account
    ) {
        return accountService.registerAccount(account);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(
            @Validated(Groups.loginGroup.class) @RequestBody Account account
            , HttpServletResponse response
    ) {
        PrincipalDetails principalDetails = principalDetailService.loadUserByUsername(account.getAccountSerial());

        if (principalDetails != null) {
            // 로그인 성공시
            // JWT 토큰 생성 및 반환
            // 저장된 user의 정보 principalDetails을 기반으로 JWT 토큰을 생성한다.
            String accessToken = JwtProvider.createAccessToken(principalDetails);
            // 생성된 토큰으로 쿠키를 생성한다.
            Cookie cookie = new Cookie("accessToken", accessToken);
            cookie.setMaxAge(JwtProperties.EXPIRATION_TIME); // 쿠키 유효 기간 설정 (예: 30분)
            cookie.setPath("/"); // 쿠키의 유효 경로 설정 (루트 경로로 설정)
            // 쿠키를 Http응답에 추가한다.
            response.addCookie(cookie);
            // 응답을 반환한다.
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpStatusMsg.HTTP_UNAUTHORIZED);
        }
    }

    /*
    param : String accountSerial, String tableNo
     */
    @PostMapping("/sub/add")
    public ResponseEntity<?> addSub(
            @Validated(Groups.subAddGroup.class) @RequestBody Sub sub
    ){
        sub.setSubSerial(sub.getAccountSerial()+sub.getTableNo());
        return accountService.addSub(sub);
    }
}
