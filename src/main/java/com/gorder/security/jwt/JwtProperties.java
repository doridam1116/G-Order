package com.gorder.security.jwt;


public interface JwtProperties {

    final String SECRET = "Y2hvcHBhLWRvbnQtYml0ZS1tZS1zcHJpbmctYm9vdC1qd3QtdGVzdC1zZWNyZXQta2V5LWNob3BwYS1kb250LWJpdGUtbWUtc3ByaW5nLWJvb3Qtand0LXRlc3Qtc2VjcmV0LWtleQo="; // HS512 형식에 따른 비밀키 생성
    final int EXPIRATION_TIME =  24 * 60 * 60 * 1000; // 24시간
    final String TOKEN_PREFIX = "Bearer "; // 전달 받는 토큰의 선행값 제거를 위해 사용
}
