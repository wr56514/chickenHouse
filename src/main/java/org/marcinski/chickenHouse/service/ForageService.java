package org.marcinski.chickenHouse.service;

import org.marcinski.chickenHouse.dto.ForageDto;
import org.marcinski.chickenHouse.entity.Forage;
import org.marcinski.chickenHouse.mapper.ForageMapper;
import org.marcinski.chickenHouse.repository.ForageRepository;
import org.springframework.stereotype.Service;

@Service
public class ForageService {

    private ForageRepository forageRepository;

    public ForageService(ForageRepository forageRepository) {
        this.forageRepository = forageRepository;
    }

    public ForageDto createForage(ForageDto forageDto){
        Forage saved = forageRepository.save(ForageMapper.INSTANCE.mapTo(forageDto));
        return ForageMapper.INSTANCE.mapTo(saved);
    }
}
