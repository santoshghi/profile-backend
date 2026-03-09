package com.profile.security.dto.request;

import com.profile.common.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest implements Request {

    private String username;
    private String password;
}
