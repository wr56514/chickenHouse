package org.marcinski.chickenHouse.repository;

import org.marcinski.chickenHouse.entity.ChickenHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChickenHouseRepository extends JpaRepository<ChickenHouse, Long> {
    List<ChickenHouse> findAllByUserEmail(String email);
}
