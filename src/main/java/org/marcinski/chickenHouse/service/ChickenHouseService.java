package org.marcinski.chickenHouse.service;

import org.marcinski.chickenHouse.dto.ChickenHouseDto;
import org.marcinski.chickenHouse.entity.ChickenHouse;
import org.marcinski.chickenHouse.mapper.ChickenHouseMapper;
import org.marcinski.chickenHouse.mapper.UserMapper;
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

    public List<ChickenHouseDto> findChickenHousesDtoByUserUUID(String userUUID) {
        List<ChickenHouse> byUserId = chickenHouseRepository.findAllByUserUuid(userUUID);
        return byUserId.stream()
                .map(ChickenHouseMapper.INSTANCE::mapToChickenHouseDto)
                .collect(Collectors.toList());
    }

    public void saveChickenHouse(ChickenHouseDto chickenHouseDto) {
        ChickenHouse chickenHouse = ChickenHouseMapper.INSTANCE.mapToChickenHouse(chickenHouseDto);
        //TODO
        //mapowanie nie działa?
        chickenHouse.setUser(UserMapper.INSTANCE.mapTo(chickenHouseDto.getUserDto()));
        chickenHouseRepository.save(chickenHouse);
    }
}
