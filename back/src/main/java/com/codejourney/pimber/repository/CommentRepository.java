package com.codejourney.pimber.repository;

import com.codejourney.pimber.entity.Comment;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c JOIN FETCH c.involvedUser JOIN FETCH c.postingUser")
    Page<Comment> findAll(Pageable pageable);

    @Cacheable("comments")
    @Query("SELECT c FROM Comment c JOIN FETCH c.involvedUser JOIN FETCH c.postingUser WHERE c.id = :id")
    Optional<Comment> findCommentById(@Param("id") Long id);
}
