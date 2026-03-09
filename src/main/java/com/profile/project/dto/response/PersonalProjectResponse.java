package com.profile.project.dto.response;

import com.profile.common.Response;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PersonalProjectResponse implements Response {

    private Long id;
    private String name;
    private String description;
    private List<String> technologies;
    private String link;
    private String image;
}
