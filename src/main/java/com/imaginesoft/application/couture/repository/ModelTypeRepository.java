package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.ModelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelTypeRepository extends JpaRepository<ModelType, Long> {
}
