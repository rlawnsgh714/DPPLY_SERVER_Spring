package com.stuent.dpply.domain.posting.entity.repository;

import com.stuent.dpply.domain.posting.entity.Posting;
import com.stuent.dpply.domain.posting.entity.PostingComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostingCommentRepository extends JpaRepository<PostingComment, Long> {

    List<PostingComment> findByPosting(Posting posting);
    void deleteByPosting(Posting posting);
}
