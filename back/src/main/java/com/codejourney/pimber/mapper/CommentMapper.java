package com.codejourney.pimber.mapper;

import com.codejourney.pimber.dto.CommentDto;
import com.codejourney.pimber.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto toDto(Comment comment);

    Comment toEntity(CommentDto commentDto);

    List<CommentDto> toDtos(List<Comment> comments);

    List<Comment> toEntities(List<CommentDto> commentDtos);
}
