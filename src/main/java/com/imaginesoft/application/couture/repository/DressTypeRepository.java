package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.DressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DressTypeRepository extends JpaRepository<DressType, Long> {
}
