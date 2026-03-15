package com.profile.project.controller;

import com.profile.common.ApiResponse;
import com.profile.common.PathConstant;
import com.profile.project.dto.request.PersonalProjectRequest;
import com.profile.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PathConstant.PROJECT)
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @PostMapping
    public ResponseEntity<?> savePersonalProject(@RequestBody PersonalProjectRequest request) {
        service.savePersonalProject(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(HttpStatus.CREATED)
                        .message("Personal project saved successfully.")
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getPersonalProject() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .message("Personal project(s) fetch successfully.")
                        .data(service.getPersonalProject())
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> savePersonalProject(@RequestParam("projectId") Long projectId, @RequestBody PersonalProjectRequest request) {
        service.updatePersonalProject(projectId, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(HttpStatus.CREATED)
                        .message("Personal project updated successfully.")
                        .build());
    }

    @DeleteMapping
    public ResponseEntity<?> deletePersonalProject(@RequestParam("projectId") Long projectId) {
        service.deletePersonalProject(projectId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .message("Personal project deleted successfully.")
                        .build());
    }


}
