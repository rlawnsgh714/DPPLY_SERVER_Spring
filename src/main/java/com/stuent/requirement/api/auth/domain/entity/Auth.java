package com.stuent.requirement.api.auth.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@Entity(name = "auth")
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
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
}
