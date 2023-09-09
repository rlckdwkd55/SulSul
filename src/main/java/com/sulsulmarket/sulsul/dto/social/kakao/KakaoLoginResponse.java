package com.sulsulmarket.sulsul.dto.social.kakao;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.Map;

@Getter
@ToString
public class KakaoLoginResponse {

    private int status;
    private long id;
    private Date connectedAt;
    private Map<String, String> properties;
    private Map<String, Object> kakaoAccount;
    private Date date;
    private String server;
    private String accessControlAllowOrigin;
    private String accessControlAllowMethods;
    private String accessControlAllowHeaders;
    private String requestID;
    private String quotaType;
    private String contentType;
    private int contentLength;
    private String keepAlive;
    private String connection;

}
