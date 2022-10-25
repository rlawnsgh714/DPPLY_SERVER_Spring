package com.stuent.dpply.api.posting.domain.entity;

import com.stuent.dpply.api.auth.domain.entity.User;
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

    @JoinColumn(name = "fk_user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private LocalDateTime createAt;

    public void modifyComment(String comment) {
        this.comment = comment;
    }

    @Builder
    public PostingComment(User user, Posting posting, String comment) {
        this.user = user;
        this.posting = posting;
        this.comment = comment;
        this.createAt = LocalDateTime.now();
    }
}
