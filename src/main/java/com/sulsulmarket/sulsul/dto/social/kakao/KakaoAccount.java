package com.sulsulmarket.sulsul.dto.social.kakao;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KakaoAccount {
    private boolean profile_nickname_needs_agreement;
    private Profile profile;
    private boolean has_email;
    private boolean email_needs_agreement;
    private boolean is_email_valid;
    private boolean is_email_verified;
    private String email;
    private boolean has_birthday;
    private boolean birthday_needs_agreement;
    private String birthday;
    private String birthday_type;
    private boolean has_gender;
    private boolean gender_needs_agreement;
    private String gender;
}
