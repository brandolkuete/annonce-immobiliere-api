package com.brandolkuete.immobilier.repository;

import com.brandolkuete.immobilier.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
