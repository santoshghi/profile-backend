package com.profile.profileinfo.repository;

import com.profile.profileinfo.entity.ProfileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileInfoRepository extends JpaRepository<ProfileInfo, Long> {

    @Query("SELECT pi FROM ProfileInfo pi ORDER BY pi.id desc")
    List<ProfileInfo> getProfileInfo();
}
