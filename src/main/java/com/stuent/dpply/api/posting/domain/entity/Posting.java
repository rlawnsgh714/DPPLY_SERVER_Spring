package com.stuent.dpply.api.posting.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stuent.dpply.api.posting.domain.enums.PostingStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "posting")
    @JsonBackReference
    private List<PostingSympathy> sympathyList;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostingStatus status;
}
