package com.stuent.dpply.api.posting.domain.repository;

import com.stuent.dpply.api.posting.domain.entity.PostingCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostingCountRepository extends JpaRepository<PostingCount, Long> {
}
