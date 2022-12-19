package com.stuent.dpply.domain.posting.entity.repository;

import com.stuent.dpply.domain.auth.entity.User;
import com.stuent.dpply.domain.posting.entity.Posting;
import com.stuent.dpply.domain.posting.entity.PostingSympathy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostingSympathyRepository extends JpaRepository<PostingSympathy, Long> {

    Optional<PostingSympathy> findByUserAndPosting(User user, Posting posting);
}
