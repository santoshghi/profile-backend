package com.profile.profileinfo.dto.request;

import com.profile.common.Request;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileInfoRequest implements Request {

    @NotBlank(message = "Name is required")
    private String name;
    private String title;
    private String email;
    private String phone;
    private String location;
    private String bio;
    private String profilePicture;

}
