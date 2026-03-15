package com.profile.security.dto.response;

import com.profile.common.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse implements Response {

    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
