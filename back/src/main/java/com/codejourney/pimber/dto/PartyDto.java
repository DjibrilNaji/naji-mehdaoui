package com.codejourney.pimber.dto;

import com.codejourney.pimber.entity.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class PartyDto {
    private UUID id;
    private int count_people;
    private int max_people;
    private LocalDateTime start_timestamp;
    private LocalDateTime end_timestamp;
    private LocalDateTime publication;
    private float price;
    private boolean bring_something;
}
