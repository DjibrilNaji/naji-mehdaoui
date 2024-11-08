package com.codejourney.pimber.dto;

import com.codejourney.pimber.entity.User;
import com.codejourney.pimber.model.Rating;
import lombok.Data;

import java.util.UUID;

@Data
public class CommentDto {
    private UUID id;
    private String text;
    private Rating rating;
    private User getPostingUser;
    private User postingUser;
    private User involvedUser;
}
