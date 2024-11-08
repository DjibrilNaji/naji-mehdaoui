package com.codejourney.pimber.repository;

import com.codejourney.pimber.entity.PartyVideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyVideoGameRepository extends JpaRepository<PartyVideoGame, Long> {
}
