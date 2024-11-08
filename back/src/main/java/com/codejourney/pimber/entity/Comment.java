package com.codejourney.pimber.entity;

import com.codejourney.pimber.model.Rating;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "comment", indexes = @Index(name = "index_rating_comment", columnList = "rating"))
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String text;

    @Enumerated(EnumType.ORDINAL)
    private Rating rating;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User postingUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User involvedUser;
}
