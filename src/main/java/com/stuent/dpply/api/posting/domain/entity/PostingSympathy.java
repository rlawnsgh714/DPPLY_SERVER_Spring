package com.stuent.dpply.api.posting.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stuent.dpply.api.auth.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "posting_sympathy")
@NoArgsConstructor
public class PostingSympathy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_posting_id")
    @JsonManagedReference
    private Posting posting;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user_id")
    private User user;

    @Builder
    public PostingSympathy(Posting posting, User user){
        this.posting = posting;
        this.user = user;
    }
}
