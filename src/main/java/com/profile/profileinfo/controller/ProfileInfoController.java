package com.profile.profileinfo.controller;

import com.profile.common.ApiResponse;
import com.profile.common.PathConstant;
import com.profile.profileinfo.dto.request.ProfileInfoRequest;
import com.profile.profileinfo.service.ProfileInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PathConstant.PROFILE_INFO)
@RequiredArgsConstructor
public class ProfileInfoController {

    private final ProfileInfoService service;

    @PostMapping
    public ResponseEntity<?> saveProfileInfo(@Valid @RequestBody ProfileInfoRequest request) {
        service.saveProfileInfo(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(HttpStatus.CREATED)
                        .message("Profile Info created successfully.")
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getProfileInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .message("Profile Info fetched successfully.")
                        .data(service.getProfileInfo())
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> updateProfileInfo(@RequestParam("id") Long profileInfoId, @RequestBody ProfileInfoRequest request) {
        service.updateProfileInfo(profileInfoId, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .message("Profile Info update successfully.")
                        .build());
    }
}
