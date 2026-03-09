package com.profile.project.mapper;

import com.profile.common.StatusConstant;
import com.profile.project.dto.request.PersonalProjectRequest;
import com.profile.project.dto.response.PersonalProjectResponse;
import com.profile.project.entity.Project;
import com.profile.project.entity.Technology;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ProjectMapper {

    public Project toEntity(PersonalProjectRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setLink(request.getLink());
        project.setImage(request.getImage());
        project.setDescription(request.getDescription());
        project.setStatus(StatusConstant.CREATED);
        return project;
    }

    public List<Technology> toEntity(Project project, PersonalProjectRequest request) {
        return request.getTechnologies().stream().map(technology -> {
            Technology tech = new Technology();
            tech.setProject(project);
            tech.setName(technology);
            tech.setStatus(StatusConstant.CREATED);
            return tech;
        }).toList();
    }

    public void toSaveEntity(Project project, PersonalProjectRequest request) {
        if (request.getName() != null || !request.getName().isBlank()) {
            project.setName(request.getName());
        }
        if (request.getDescription() != null || !request.getDescription().isBlank()) {
            project.setDescription(request.getDescription());
        }
        if (request.getLink() != null || !request.getLink().isBlank()) {
            project.setLink(request.getLink());
        }
        if (request.getImage() != null || !request.getImage().isBlank()) {
            project.setImage(request.getImage());
        }
        project.setStatus(StatusConstant.EDITED);
    }

    public List<PersonalProjectResponse> toResponse(List<Project> projects) {
        return projects.stream().map(project -> PersonalProjectResponse.builder()
                        .id(project.getId())
                        .name(project.getName())
                        .description(project.getDescription())
//                        .technologies(project.getTechnologyList().stream().map(Technology::getName).toList())
                        .technologies(project.getTechnologyList().stream()
                                .filter(technology -> !technology.getStatus().equals(StatusConstant.DELETED))
                                .map(Technology::getName).toList()) //filter deleted tech
                        .link(project.getLink())
                        .image(project.getImage())
                        .build()
        ).toList();
    }

}
