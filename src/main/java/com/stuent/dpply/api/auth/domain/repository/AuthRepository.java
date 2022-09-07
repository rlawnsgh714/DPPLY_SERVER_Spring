package com.stuent.dpply.api.auth.domain.repository;

import com.stuent.dpply.api.auth.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, String> {
}
