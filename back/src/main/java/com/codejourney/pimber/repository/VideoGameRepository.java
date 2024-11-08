package com.codejourney.pimber.repository;

import com.codejourney.pimber.entity.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VideoGameRepository extends JpaRepository<VideoGame, UUID> {

}
