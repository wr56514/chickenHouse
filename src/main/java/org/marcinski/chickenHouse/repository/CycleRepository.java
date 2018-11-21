package org.marcinski.chickenHouse.repository;

import org.marcinski.chickenHouse.entity.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CycleRepository extends JpaRepository<Cycle, Long> {
    List<Cycle> findAllByChickenHouseId(Long id);
}
