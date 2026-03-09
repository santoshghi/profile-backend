package com.profile.education.dto.request;

import com.profile.common.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationRequest implements Request {

    private String degree;
    private String institution;
    private String year;
    private String description;
}
