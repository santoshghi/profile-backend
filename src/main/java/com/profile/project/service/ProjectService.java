package com.profile.project.service;

import com.profile.common.StatusConstant;
import com.profile.common.exception.DataNotFoundException;
import com.profile.project.dto.request.PersonalProjectRequest;
import com.profile.project.dto.response.PersonalProjectResponse;
import com.profile.project.entity.Project;
import com.profile.project.mapper.ProjectMapper;
import com.profile.project.repository.ProjectRepository;
import com.profile.project.repository.TechnologyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository repository;
    private final TechnologyRepository technologyRepository;

    public void savePersonalProject(PersonalProjectRequest request) {
        Project project = repository.save(ProjectMapper.toEntity(request));
        technologyRepository.saveAll(ProjectMapper.toEntity(project, request));
    }

    public List<PersonalProjectResponse> getPersonalProject() {
        List<Project> projects = repository.findAll().stream().filter(project -> !project.getStatus().equals(StatusConstant.DELETED)).toList();
        if (projects.isEmpty()) {
            throw new DataNotFoundException("Personal projects not found.");
        }
        return ProjectMapper.toResponse(projects);
    }

    public void updatePersonalProject(Long projectId, PersonalProjectRequest request) {
        Project project = repository.findById(projectId)
                .orElseThrow(() -> new DataNotFoundException("Personal project(s) not found."));
        ProjectMapper.toSaveEntity(project, request);
        if (request.getTechnologies() != null || !request.getTechnologies().isEmpty()) {
            project.getTechnologyList().forEach(technology -> {
                technology.setStatus(StatusConstant.DELETED);
            });
            technologyRepository.saveAll(ProjectMapper.toEntity(project, request));
        }
    }

    @Transactional
    public void deletePersonalProject(Long projectId) {
        Project project = repository.findById(projectId)
                .orElseThrow(() -> new DataNotFoundException("Personal project(s) not found."));
        project.setStatus(StatusConstant.DELETED);
        project.getTechnologyList().forEach(technology -> {
            technology.setStatus(StatusConstant.DELETED);
        });
    }
}
