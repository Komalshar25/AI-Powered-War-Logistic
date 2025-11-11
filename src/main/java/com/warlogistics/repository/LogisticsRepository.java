package com.warlogistics.repository;

import com.warlogistics.model.Logistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsRepository extends JpaRepository<Logistics, Long> {
    // Custom query methods can be added here
}
