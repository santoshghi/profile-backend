package com.profile.profileinfo.service;

import com.profile.common.exception.DataNotFoundException;
import com.profile.profileinfo.dto.request.ProfileInfoRequest;
import com.profile.profileinfo.dto.response.ProfileInfoResponse;
import com.profile.profileinfo.entity.ProfileInfo;
import com.profile.profileinfo.mapper.ProfileInfoMapper;
import com.profile.profileinfo.repository.ProfileInfoRepository;
import com.profile.security.service.SecurityContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileInfoService {

    private final ProfileInfoRepository repository;
    private final SecurityContextService securityContext;

    public void saveProfileInfo(ProfileInfoRequest request) {
        String username = securityContext.getUsername();
        repository.save(ProfileInfoMapper.toEntity(request));
    }

    public void updateProfileInfo(Long id, ProfileInfoRequest request) {
        ProfileInfo optionalProfileInfo = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Profile information not found."));
        repository.save(ProfileInfoMapper.toEntity(optionalProfileInfo, request));
    }

    public List<ProfileInfoResponse> getProfileInfo() {
        List<ProfileInfo> profileInfoList = repository.getProfileInfo();
        if (profileInfoList.isEmpty()) {
            throw new DataNotFoundException("Profile information not found.");
        }
        return ProfileInfoMapper.toResponse(profileInfoList);
    }
}
