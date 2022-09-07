package com.stuent.dpply.api.posting.domain.entity;

import com.stuent.dpply.api.auth.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "posting_sympathy_user")
@NoArgsConstructor
public class PostingSympathy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_posting_id")
    private Posting posting;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user_id")
    private User user;
}
