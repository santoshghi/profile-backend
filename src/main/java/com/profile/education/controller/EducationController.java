package com.profile.education.controller;

import com.profile.common.PathConstant;
import com.profile.education.dto.request.EducationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConstant.EDUCATION)
public class EducationController {

    @PostMapping
    public ResponseEntity<?> saveEducation(@RequestBody EducationRequest request) {
        return null;
    }
}
