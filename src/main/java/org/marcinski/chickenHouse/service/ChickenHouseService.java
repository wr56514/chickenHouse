package org.marcinski.chickenHouse.service;

import org.marcinski.chickenHouse.dto.ChickenHouseDto;
import org.marcinski.chickenHouse.entity.ChickenHouse;
import org.marcinski.chickenHouse.mapper.ChickenHouseMapper;
import org.marcinski.chickenHouse.repository.ChickenHouseRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChickenHouseService {

    private ChickenHouseRepository chickenHouseRepository;
    private ChickenHouseMapper chickenHouseMapper;

    public ChickenHouseService(ChickenHouseRepository chickenHouseRepository, ChickenHouseMapper chickenHouseMapper) {
        this.chickenHouseRepository = chickenHouseRepository;
        this.chickenHouseMapper = chickenHouseMapper;
    }

    public List<ChickenHouseDto> findChickenHousesDtoByUserEmail(String email) {
        List<ChickenHouse> byUserEmail = chickenHouseRepository.findAllByUserEmail(email);
        return byUserEmail.stream()
                .map(chickenHouseMapper::mapTo)
                .collect(Collectors.toList());
    }

    public void saveChickenHouse(ChickenHouseDto chickenHouseDto) {
        ChickenHouse chickenHouse = chickenHouseMapper.mapTo(chickenHouseDto);
        chickenHouseRepository.save(chickenHouse);
    }

    public ChickenHouseDto getChickenHouseById(Long id) {
        return chickenHouseRepository.findById(id)
                .map(chickenHouseMapper::mapTo)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void deleteHouse(Long id) {
        chickenHouseRepository.deleteById(id);
    }
}
