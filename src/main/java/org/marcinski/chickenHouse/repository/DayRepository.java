package org.marcinski.chickenHouse.repository;

import org.marcinski.chickenHouse.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day, Long> {
}