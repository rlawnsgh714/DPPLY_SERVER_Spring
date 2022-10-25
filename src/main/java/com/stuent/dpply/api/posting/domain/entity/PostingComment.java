package com.stuent.dpply.api.posting.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Builder
    public PostingComment(Posting posting, String comment) {
        this.posting = posting;
        this.comment = comment;
        this.createAt = LocalDateTime.now();
    }
}
