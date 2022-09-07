package com.stuent.dpply.api.auth.domain.entity;

import com.stuent.dpply.api.auth.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Entity(name = "auth")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String uniqueId;

    @Column(nullable = false)
    private int grade;

    @Column(nullable = false)
    private int room;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
}
