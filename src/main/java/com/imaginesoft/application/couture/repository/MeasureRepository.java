package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Long> {
}
