package com.stuent.dpply.api.posting.domain.repository;

import com.stuent.dpply.api.posting.domain.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Integer> {
}
