package com.codejourney.pimber.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private int count_people;

    private int max_people;

    private LocalDateTime start_timestamp;

    private LocalDateTime end_timestamp;

    private LocalDateTime publication;

    private float price;

    private boolean bring_something;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<PartyVideoGame> videoGames;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<BoardGame> boardGames;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Photo> photos;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User creator;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> invitedUsers;
}
