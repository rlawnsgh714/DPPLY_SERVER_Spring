package com.stuent.dpply.api.posting.domain.repository;

import com.stuent.dpply.api.posting.domain.entity.Posting;
import com.stuent.dpply.api.posting.domain.entity.PostingComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostingCommentRepository extends JpaRepository<PostingComment, Long> {

    List<PostingComment> findByPosting(Posting posting);
}
