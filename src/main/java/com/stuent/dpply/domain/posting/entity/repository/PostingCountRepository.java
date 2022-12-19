package com.stuent.dpply.domain.posting.entity.repository;

import com.stuent.dpply.domain.posting.entity.PostingCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostingCountRepository extends JpaRepository<PostingCount, Long> {
}
