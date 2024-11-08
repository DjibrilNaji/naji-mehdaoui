package com.codejourney.pimber.service;

import com.codejourney.pimber.dto.CommentDto;
import com.codejourney.pimber.entity.Comment;
import com.codejourney.pimber.mapper.CommentMapper;
import com.codejourney.pimber.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CommentService {
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public CommentService(CommentMapper commentMapper, CommentRepository commentRepository) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
    }

    public List<CommentDto> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Comment> commentPage = commentRepository.findAll(pageRequest);

        return commentMapper.toDtos(commentPage.getContent());
    }

    public CommentDto findById(Long id) throws ResponseStatusException {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, String.format("Comment with id %s not found", id
                        )));

        return commentMapper.toDto(comment);
    }

    public CommentDto postComment(CommentDto commentDto) {
        Comment comment = commentMapper.toEntity(commentDto);
        Comment commentSaved = commentRepository.save(comment);

        return commentMapper.toDto(commentSaved);
    }
}
