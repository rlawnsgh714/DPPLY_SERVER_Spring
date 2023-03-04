package com.stuent.dpply.domain.posting.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stuent.dpply.domain.auth.entity.User;
import com.stuent.dpply.common.enums.PostingSympathyStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "posting_sympathy")
@NoArgsConstructor
public class PostingSympathy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_posting_id")
    @JsonBackReference
    private Posting posting;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostingSympathyStatus status;

    public void updateStatus(PostingSympathyStatus status) {
        this.status = status;
    }

    @Builder
    public PostingSympathy(Posting posting, User user, PostingSympathyStatus status){
        this.posting = posting;
        this.user = user;
        this.status = status;
    }
}
