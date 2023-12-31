package com.gorder.api.account.controller;

import com.gorder.api.account.model.Account;
import com.gorder.api.account.model.Sub;
import com.gorder.api.account.service.AccountService;
import com.gorder.api.validation.Groups;
import com.gorder.security.auth.PrincipalDetailService;
import com.gorder.security.auth.PrincipalDetails;
import com.gorder.security.jwt.JwtProperties;
import com.gorder.security.jwt.JwtProvider;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;

import static com.gorder.config.PropertyConfig.getMessageSource;

@Api(tags = {"계정 API"})
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final PrincipalDetailService principalDetailService;

    public AccountController(AccountService accountService, PrincipalDetailService principalDetailService) {
        this.accountService = accountService;
        this.principalDetailService = principalDetailService;
    }



    /*
        param :
        String accountName,
        int accountTel,
        String accountType
     */
    @PostMapping("/register")
    @ApiOperation(
            value = "가입"
            , notes = "신규 가입을 진행한다."
            , produces = "application/json"
            , response = Account.class
    )
    public ResponseEntity<?> registerAccount(
            @Validated(
                    Groups.registerGroup.class
            )
            @RequestBody Account account
    ) {
        return accountService.registerAccount(account);
    }


    /*
        param :
        String accountSerial,
        String accountType
     */
    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(
            @Validated(
                    Groups.loginGroup.class
            )
            @RequestBody Account account
            , HttpServletResponse response
    ) {
        PrincipalDetails principalDetails = principalDetailService.loadUserByUsername(account);

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
            return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK",null, Locale.getDefault()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(getMessageSource().getMessage("HTTP_UNAUTHORIZED",null, Locale.getDefault()));
        }
    }


    /*
        param :
        String accountSerial,
        String tableNo
     */
    @ApiOperation(value = "Sub 추가")
    @PostMapping("/sub/add")
    public ResponseEntity<?> addSub(
            @Validated(Groups.subAddGroup.class)
            @RequestBody Sub sub
    ){
        sub.setSubSerial(sub.getAccountSerial()+sub.getTableNo());
        return accountService.addSub(sub);
    }

    /*
    param :
    String accountSeral
 */
    @GetMapping("/sub")
    public ResponseEntity<?> readSub(
            @RequestParam String accountSerial
    ){
        return accountService.readSub(accountSerial);
    }


    /*
        param :
        String subSerial
     */
    @ApiOperation(value = "서브 삭제")
    @DeleteMapping("/delete/{subSerial}")
    public ResponseEntity<?> deleteSub(
            @ApiParam(
                    value = "서브 시리얼"
                    , required = true
                    , type = "string"
            )
            @PathVariable String subSerial
    ){
        return accountService.deleteSubBySerial(subSerial);
    }


    /*
        param :
        String subSerial,
        int subActive
     */
    @ApiOperation(value = "서브 비활성화")
    @PutMapping("/active")
    public ResponseEntity<?> subActive(
        @Validated(Groups.subActiveGroup.class)
        @RequestBody Sub sub
    ){
        return accountService.updateSubActive(sub);
    }

    /*
        param :
        string subSerial,
        string accountSerial
     */
    @ApiOperation(value = "서브 수정")
    @PutMapping("/sub/modify")
    public ResponseEntity<?> subModify(
            @Validated(Groups.subModifyGroup.class)
            @RequestBody Sub sub
    ){
        return accountService.updateSub(sub);
    }
}
