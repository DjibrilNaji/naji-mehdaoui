package com.codejourney.pimber.repository;

import com.codejourney.pimber.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlatformRepository extends JpaRepository<Platform, UUID> {

}
