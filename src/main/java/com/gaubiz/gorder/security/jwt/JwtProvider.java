package com.gaubiz.gorder.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gaubiz.gorder.security.auth.PrincipalDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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


    public Claims parseJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JwtProperties.SECRET)
                .parseClaimsJws(token)
                .getBody();

        ZoneId koreaTimeZone = ZoneId.of("Asia/Seoul");

        Date expirationDate = claims.getExpiration();
        ZonedDateTime koreaExpirationDateTime = expirationDate.toInstant().atZone(koreaTimeZone);
        Date koreaExpirationDate = Date.from(koreaExpirationDateTime.toInstant());

        Claims newClaims = Jwts.claims(claims); // 새로운 클레임 객체 생성
        newClaims.setExpiration(koreaExpirationDate); // 새로운 클레임 객체에 만료 시간 설정

        return newClaims;
    }

}
