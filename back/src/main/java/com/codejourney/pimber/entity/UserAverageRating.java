package com.codejourney.pimber.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "user_average_ratings")
public class UserAverageRating {
    @Id
    private UUID userId;

    private Double averageRating;
}
