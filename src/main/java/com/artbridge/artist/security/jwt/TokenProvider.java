package com.artbridge.artist.security.jwt;

import org.springframework.security.core.Authentication;

public interface TokenProvider {
    /**
     * 인증 정보를 기반으로 토큰을 생성합니다.
     *
     * @param authentication 인증 정보
     * @param rememberMe 기억하기 옵션 (true: 장기간 유지, false: 세션 기반)
     * @return 생성된 토큰의 문자열
     */
    @Deprecated(since = "3.0", forRemoval = true)
    String createToken(Authentication authentication, boolean rememberMe);

    /**
     * 토큰을 기반으로 인증 정보를 가져옵니다.
     *
     * @param token 토큰 문자열
     * @return 인증 정보를 담은 Authentication 객체
     */
    Authentication getAuthentication(String token);

    /**
     * 토큰에서 사용자 ID를 추출합니다.
     *
     * @param token 토큰 문자열
     * @return 추출된 사용자 ID (Long)
     */
    Long getUserIdFromToken(String token);

    /**
     * 토큰의 유효성을 검증합니다.
     *
     * @param authToken 인증 토큰 문자열
     * @return 토큰의 유효성 여부 (true: 유효, false: 유효하지 않음)
     */
    boolean validateToken(String authToken);
}
