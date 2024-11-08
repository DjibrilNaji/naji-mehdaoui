package com.codejourney.pimber.repository;

import com.codejourney.pimber.entity.BoardGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoardGameRepository extends JpaRepository<BoardGame, UUID> {
}
