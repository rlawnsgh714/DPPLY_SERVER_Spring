package com.stuent.dpply.api.posting.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.stuent.dpply.api.auth.domain.entity.User;
import com.stuent.dpply.api.posting.domain.enums.PostingStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity(name = "posting")
@NoArgsConstructor
public class Posting {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private int sympathyCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostingStatus status;

    @OneToMany(mappedBy = "posting")
    @JsonBackReference
    private List<PostingSympathy> sympathyList;

    @CreatedDate
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user_id")
    private User user;

    public void updatePosting(String text){
        this.text = text;
    }
    public void updateStatus(PostingStatus status){
        this.status = status;
    }

    @Builder
    public Posting(String text, User user){
        this.text = text;
        this.user = user;
        this.sympathyCount = 0;
        this.status = PostingStatus.WAITING;
        this.createAt = LocalDate.now();
    }
}
