package com.codejourney.pimber.repository;

import com.codejourney.pimber.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartyRepository extends JpaRepository<Party, UUID> {
}
