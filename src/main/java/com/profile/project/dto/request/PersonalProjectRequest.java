package com.profile.project.dto.request;

import com.profile.common.Request;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonalProjectRequest implements Request {

    private String name;
    private String description;
    private List<String> technologies;
    private String link;
    private String image;
}
