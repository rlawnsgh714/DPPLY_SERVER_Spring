package com.stuent.dpply.api.posting.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "posting_comment")
@NoArgsConstructor
public class PostingComment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "fk_posting_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Posting posting;

    @Column(nullable = false)
    private String comment;
}
