package org.marcinski.chickenHouse.service;

import org.marcinski.chickenHouse.dto.ForageDto;
import org.marcinski.chickenHouse.entity.Forage;
import org.marcinski.chickenHouse.mapper.ForageMapper;
import org.marcinski.chickenHouse.repository.ForageRepository;
import org.springframework.stereotype.Service;

@Service
public class ForageService {

    private ForageRepository forageRepository;
    private ForageMapper forageMapper;

    public ForageService(ForageRepository forageRepository, ForageMapper forageMapper) {
        this.forageRepository = forageRepository;
        this.forageMapper = forageMapper;
    }

    public ForageDto createForage(ForageDto forageDto){
        Forage saved = forageRepository.save(forageMapper.mapTo(forageDto));
        return forageMapper.mapTo(saved);
    }
}
