package com.codejourney.pimber.repository;

import com.codejourney.pimber.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}