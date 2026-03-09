package com.profile.profileinfo.entity;

import com.profile.common.StatusConstant;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile_info")
public class ProfileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "bio", nullable = false)
    private String bio;

    @Column(name = "profile_picture", nullable = false)
    private String profilePicture;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StatusConstant status;
}
