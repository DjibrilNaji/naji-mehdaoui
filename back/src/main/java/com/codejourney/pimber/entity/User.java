package com.codejourney.pimber.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, length = 30)
    private String username;

    private String password;

    private String email;

    private LocalDateTime created_at;

    @OneToMany(mappedBy = "postingUser")
    private Set<Comment> postedComments;

    @OneToMany(mappedBy = "involvedUser")
    private Set<Comment> involvedComments;

    @ManyToMany(mappedBy = "invitedUsers")
    private Set<Party> parties;

    @OneToMany(mappedBy = "creator")
    private Set<Party> createdParties;
}
