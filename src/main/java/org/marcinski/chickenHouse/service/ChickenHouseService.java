package org.marcinski.chickenHouse.service;

import org.marcinski.chickenHouse.dto.ChickenHouseDto;
import org.marcinski.chickenHouse.entity.ChickenHouse;
import org.marcinski.chickenHouse.mapper.ChickenHouseMapper;
import org.marcinski.chickenHouse.repository.ChickenHouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChickenHouseService {

    private ChickenHouseRepository chickenHouseRepository;

    public ChickenHouseService(ChickenHouseRepository chickenHouseRepository) {
        this.chickenHouseRepository = chickenHouseRepository;
    }

    public List<ChickenHouseDto> findChickenHousesDtoByUserEmail(String email) {
        List<ChickenHouse> byUserEmail = chickenHouseRepository.findAllByUserEmail(email);
        return byUserEmail.stream()
                .map(ChickenHouseMapper.INSTANCE::mapTo)
                .collect(Collectors.toList());
    }

    public void saveChickenHouse(ChickenHouseDto chickenHouseDto) {
        ChickenHouse chickenHouse = ChickenHouseMapper.INSTANCE.mapTo(chickenHouseDto);
        chickenHouseRepository.save(chickenHouse);
    }

    public Optional<ChickenHouseDto> getChickenHouseById(Long id) {
        Optional<ChickenHouse> houseById = chickenHouseRepository.findById(id);
        ChickenHouseDto houseDto = null;
        if (houseById.isPresent()){
            houseDto = ChickenHouseMapper.INSTANCE.mapTo(houseById.get());
        }
        return Optional.ofNullable(houseDto);
    }

    public void deteleHouse(Long id) {
        chickenHouseRepository.deleteById(id);
    }
}
