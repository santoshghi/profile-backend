package com.profile.profileinfo.mapper;

import com.profile.common.StatusConstant;
import com.profile.profileinfo.dto.request.ProfileInfoRequest;
import com.profile.profileinfo.dto.response.ProfileInfoResponse;
import com.profile.profileinfo.entity.ProfileInfo;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

@UtilityClass
public class ProfileInfoMapper {

    public ProfileInfo toEntity(ProfileInfoRequest request) {
        ProfileInfo profileInfo = new ProfileInfo();
        profileInfo.setName(request.getName());
        profileInfo.setTitle(request.getTitle());
        profileInfo.setEmail(request.getEmail());
        profileInfo.setBio(request.getBio());
        profileInfo.setPhone(request.getPhone());
        profileInfo.setLocation(request.getLocation());
        profileInfo.setStatus(StatusConstant.CREATED);
        profileInfo.setProfilePicture(request.getProfilePicture());
        return profileInfo;
    }

    public List<ProfileInfoResponse> toResponse(List<ProfileInfo> profileInfoList) {
        return profileInfoList.stream().map(profileInfo -> ProfileInfoResponse.builder()
                .id(profileInfo.getId())
                .name(profileInfo.getName())
                .bio(profileInfo.getBio())
                .email(profileInfo.getEmail())
                .title(profileInfo.getTitle())
                .phone(profileInfo.getPhone())
                .location(profileInfo.getLocation())
                .profilePicture(profileInfo.getProfilePicture())
                .build()).toList();
    }

    public ProfileInfo toEntity(ProfileInfo profileInfo, ProfileInfoRequest request) {

        if (Objects.nonNull(request.getName())) {
            profileInfo.setName(request.getName());
        }

        if (Objects.nonNull(request.getBio())) {
            profileInfo.setBio(request.getBio());
        }

        if (Objects.nonNull(request.getTitle())) {
            profileInfo.setTitle(request.getTitle());
        }

        if (Objects.nonNull(request.getEmail())) {
            profileInfo.setEmail(request.getEmail());
        }

        if (Objects.nonNull(request.getLocation())) {
            profileInfo.setLocation(request.getLocation());
        }

        if (Objects.nonNull(request.getPhone())) {
            profileInfo.setPhone(request.getPhone());
        }

        if (Objects.nonNull(request.getProfilePicture())) {
            profileInfo.setProfilePicture(request.getProfilePicture());
        }
        profileInfo.setStatus(StatusConstant.EDITED);
        return profileInfo;
    }


}
