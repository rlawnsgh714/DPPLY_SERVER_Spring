package com.stuent.dpply.domain.posting.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stuent.dpply.domain.auth.entity.User;
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

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
