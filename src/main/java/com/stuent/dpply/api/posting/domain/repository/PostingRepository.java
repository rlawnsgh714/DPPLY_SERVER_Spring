package com.stuent.dpply.api.posting.domain.repository;

import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.posting.domain.entity.Posting;
import com.stuent.dpply.api.posting.domain.enums.PostingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findByStatusOrderByCreateAt(PostingStatus status);

    List<Posting> findByStatusOrderBySympathyCount(PostingStatus status);

    int countByUserAndCreateAtBetween(User user, LocalDate createAt, LocalDate createAt2);
}
