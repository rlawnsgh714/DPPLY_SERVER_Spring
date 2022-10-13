package com.stuent.dpply.api.posting.domain.repository;

import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.posting.domain.entity.Posting;
import com.stuent.dpply.api.posting.domain.entity.PostingSympathy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostingSympathyRepository extends JpaRepository<PostingSympathy, Long> {

    Optional<PostingSympathy> findByUserAndPosting(User user, Posting posting);
}
