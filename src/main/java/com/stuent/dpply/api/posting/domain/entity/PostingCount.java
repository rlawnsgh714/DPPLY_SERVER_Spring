package com.stuent.dpply.api.posting.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name = "posting_count")
public class PostingCount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int count;
}
