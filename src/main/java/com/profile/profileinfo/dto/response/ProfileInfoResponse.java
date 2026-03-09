package com.profile.profileinfo.dto.response;

import com.profile.common.Response;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProfileInfoResponse implements Response {

    private Long id;
    private String name;
    private String title;
    private String email;
    private String phone;
    private String location;
    private String bio;
    private String profilePicture;
}
