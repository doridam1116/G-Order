package com.gaubiz.gorder.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gaubiz.gorder.security.auth.PrincipalDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    public static String createAccessToken(PrincipalDetails principalDetails) {
        return JWT.create()
                // 만료시간을 JwtProperties의 값으로 작성
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))
                // 내용의 id 클레임에 userId를 작성
                .withClaim("serial", principalDetails.getUser().getAccountSerial())
                .withClaim("type",principalDetails.getUser().getAccountType())
                // 서명의 알고리즘을 HS512로 하고 JwtProperties의 비밀키를 사용하여 암호화 한다.
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }


}
