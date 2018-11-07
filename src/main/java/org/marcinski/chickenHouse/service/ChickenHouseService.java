package org.marcinski.chickenHouse.service;

import org.marcinski.chickenHouse.dto.ChickenHouseDto;
import org.marcinski.chickenHouse.entity.ChickenHouse;
import org.marcinski.chickenHouse.mapper.ChickenHouseMapper;
import org.marcinski.chickenHouse.repository.ChickenHouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChickenHouseService {

    private ChickenHouseRepository chickenHouseRepository;

    public ChickenHouseService(ChickenHouseRepository chickenHouseRepository) {
        this.chickenHouseRepository = chickenHouseRepository;
    }

    public List<ChickenHouseDto> findChickenHouseDtoByUserId(Long userId) {
        List<ChickenHouse> byUserId = chickenHouseRepository.findAllByUserId(userId);
        return byUserId.stream()
                .map(ChickenHouseMapper.INSTANCE::mapToChickenHouseDto)
                .collect(Collectors.toList());
    }
}