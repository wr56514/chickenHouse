package org.marcinski.chickenHouse.service;

import org.marcinski.chickenHouse.dto.CycleDto;
import org.marcinski.chickenHouse.dto.DayDto;
import org.marcinski.chickenHouse.entity.Day;
import org.marcinski.chickenHouse.mapper.DayMapper;
import org.marcinski.chickenHouse.repository.DayRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DayService {

    private DayRepository dayRepository;
    private CycleService cycleService;

    public DayService(DayRepository dayRepository, CycleService cycleService) {
        this.dayRepository = dayRepository;
        this.cycleService = cycleService;
    }

    public void createDay(DayDto dayDto, Long cycleId) {
        Optional<CycleDto> cycleById = cycleService.getById(cycleId);
        if (cycleById.isPresent()){
            CycleDto cycleDto = cycleById.get();
            dayDto.setCycleDto(cycleDto);

            Day day = DayMapper.INSTANCE.mapTo(dayDto);
            dayRepository.save(day);
        }
    }

    public List<DayDto> getAllDaysByCycleIdSortedByDayNumber(Long cycleId){
        Optional<CycleDto> cycleById = cycleService.getById(cycleId);
        List<DayDto> dayDtos = new ArrayList<>();
        if (cycleById.isPresent()){
            CycleDto cycleDto = cycleById.get();
            dayDtos.addAll(cycleDto.getDaysDto());
            dayDtos.sort(Comparator.comparingInt(DayDto::getDayNumber).reversed());
        }
        return dayDtos;
    }
}
