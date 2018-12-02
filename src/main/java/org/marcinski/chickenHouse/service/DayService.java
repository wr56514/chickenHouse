package org.marcinski.chickenHouse.service;

import org.marcinski.chickenHouse.dto.CycleDto;
import org.marcinski.chickenHouse.dto.DayDto;
import org.marcinski.chickenHouse.dto.ForageDto;
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
    private ForageService forageService;

    public DayService(DayRepository dayRepository, CycleService cycleService, ForageService forageService) {
        this.dayRepository = dayRepository;
        this.cycleService = cycleService;
        this.forageService = forageService;
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

    public long editDay(DayDto dayDto, Long dayId) {
        Optional<DayDto> optionalDayDto = getDayDtobyCycleId(dayId);
        long cycleId = 0;
        if (optionalDayDto.isPresent()){
            DayDto toEdit = optionalDayDto.get();
            toEdit.setNaturalDowns(dayDto.getNaturalDowns());
            toEdit.setSelectionDowns(dayDto.getSelectionDowns());
            toEdit.setWaterCounter(dayDto.getWaterCounter());
            toEdit.setAverageWeight(dayDto.getAverageWeight());
            toEdit.setComments(dayDto.getComments());
            cycleId = toEdit.getCycleDto().getId();
            dayRepository.save(DayMapper.INSTANCE.mapTo(toEdit));
        }
        return cycleId;
    }

    public long addForageToDay(ForageDto forageDto, Long dayId) {
        Optional<DayDto> optionalDayDto = getDayDtobyCycleId(dayId);
        long cycleId = 0;
        if (optionalDayDto.isPresent()){
            DayDto dayToEdit = optionalDayDto.get();
            ForageDto forageToAdd = forageService.createForage(forageDto);
            dayToEdit.setForageDto(forageToAdd);

            cycleId = dayToEdit.getCycleDto().getId();
            dayRepository.save(DayMapper.INSTANCE.mapTo(dayToEdit));
        }
        return cycleId;
    }

    private Optional<DayDto> getDayDtobyCycleId(Long dayId){
        Optional<Day> optionalDay = dayRepository.findById(dayId);
        DayDto dayDto = null;
        if (optionalDay.isPresent()){
            dayDto = DayMapper.INSTANCE.mapTo(optionalDay.get());
        }
        return Optional.ofNullable(dayDto);
    }
}
