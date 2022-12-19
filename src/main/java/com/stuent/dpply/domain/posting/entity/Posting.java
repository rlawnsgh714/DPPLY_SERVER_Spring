package com.stuent.dpply.domain.posting.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stuent.dpply.domain.auth.entity.User;
import com.stuent.dpply.common.enums.PostingStatus;
import com.stuent.dpply.common.enums.PostingTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity(name = "posting")
@NoArgsConstructor
public class Posting {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private int sympathyCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostingStatus status;

    @CreatedDate
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostingTag tag;

    private LocalDate updateAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user_id")
    private User user;

    private String imageUrl;

    public void updatePosting(String title, String text, PostingStatus status, LocalDate updateAt){
        this.title = title;
        this.text = text;
        this.status = status;
        this.updateAt = updateAt;
    }

    @Builder
    public Posting(String title, String text, User user, PostingTag tag){
        this.title = title;
        this.text = text;
        this.user = user;
        this.tag = tag;
        this.sympathyCount = 0;
        this.status = PostingStatus.WAITING;
        this.createAt = LocalDate.now();
    }
}
