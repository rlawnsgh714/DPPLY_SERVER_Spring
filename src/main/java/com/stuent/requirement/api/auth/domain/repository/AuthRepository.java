package com.stuent.requirement.api.auth.domain.repository;

import com.stuent.requirement.api.auth.domain.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String> {
}
