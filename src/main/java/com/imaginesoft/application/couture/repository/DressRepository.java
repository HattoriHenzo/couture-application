package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.Dress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DressRepository extends JpaRepository<Dress, Long> {
}
