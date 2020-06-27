package com.gabrielcasag.osworks.domain.repository;

import com.gabrielcasag.osworks.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
