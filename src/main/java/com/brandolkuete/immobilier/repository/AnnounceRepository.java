package com.brandolkuete.immobilier.repository;

import com.brandolkuete.immobilier.entities.Announce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnounceRepository extends JpaRepository<Announce,Long> {
}
