package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.MeasureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureTypeRepository extends JpaRepository<MeasureType, Long> {
}
