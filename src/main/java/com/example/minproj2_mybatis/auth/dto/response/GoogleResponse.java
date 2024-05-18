package com.example.minproj2_mybatis.auth.dto.response;

import java.util.Map;

public class GoogleResponse implements OAuth2Response {
    private final Map<String, Object> attributes;

    public GoogleResponse(Map<String, Object> attributes) {
        this.attributes = attributes != null ? attributes : Map.of();  // Null 체크 및 기본값 설정
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");  // 예: Google의 경우 "sub" 필드 사용
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getProvider() {
        return "google";
    }
}
