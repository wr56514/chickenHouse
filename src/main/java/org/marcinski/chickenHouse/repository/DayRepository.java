package org.marcinski.chickenHouse.repository;

import org.marcinski.chickenHouse.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DayRepository extends JpaRepository<Day, Long> {
    Optional<Day> findByCycleId(Long id);
}
