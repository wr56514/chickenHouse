package org.marcinski.chickenHouse.repository;

import org.marcinski.chickenHouse.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
