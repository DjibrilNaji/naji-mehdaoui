package com.codejourney.pimber.repository;

import com.codejourney.pimber.entity.BoardGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, UUID> {
}
