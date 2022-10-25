package com.stuent.dpply.api.posting.domain.repository;

import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.posting.domain.entity.Posting;
import com.stuent.dpply.api.posting.domain.enums.PostingStatus;
import com.stuent.dpply.api.posting.domain.enums.PostingTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findByStatusOrderByCreateAt(PostingStatus status);

    List<Posting> findByStatusOrderBySympathyCount(PostingStatus status);

    List<Posting> findByTag(PostingTag tag);

    int countByUserAndCreateAtBetween(User user, LocalDate createAt, LocalDate createAt2);
}
