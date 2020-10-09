package com.andrew.rental.repository;

import com.andrew.rental.model.ActiveRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActiveRentRepository extends JpaRepository<ActiveRent, UUID> {
    List<ActiveRent> findActiveRentsByClientId(UUID clientId);
}