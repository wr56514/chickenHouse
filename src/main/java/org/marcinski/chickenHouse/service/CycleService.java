package org.marcinski.chickenHouse.service;

import org.marcinski.chickenHouse.dto.ChickenHouseDto;
import org.marcinski.chickenHouse.dto.CycleDto;
import org.marcinski.chickenHouse.entity.Cycle;
import org.marcinski.chickenHouse.mapper.CycleMapper;
import org.marcinski.chickenHouse.repository.CycleRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CycleService {

    private CycleRepository cycleRepository;
    private ChickenHouseService chickenHouseServiceService;

    public CycleService(CycleRepository cycleRepository, ChickenHouseService chickenHouseService) {
        this.cycleRepository = cycleRepository;
        this.chickenHouseServiceService = chickenHouseService;
    }

    public void createCycle(CycleDto cycleDto, Long id) {
        ChickenHouseDto chickenHouseById = chickenHouseServiceService.getChickenHouseById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chicken house with id: " + id + " not found"));

        cycleDto.setChickenHouseDto(chickenHouseById);

        Cycle cycle = CycleMapper.INSTANCE.mapTo(cycleDto);
        cycleRepository.save(cycle);
    }

    public List<CycleDto> getAllByChickenHouseId(Long id) {
        List<Cycle> cycles = cycleRepository.findAllByChickenHouseId(id);
        return cycles.stream()
                .map(CycleMapper.INSTANCE::mapTo)
                .collect(Collectors.toList());
    }

    public long updateCycle(CycleDto cycleDto, Long id) {
        Optional<Cycle> byId = cycleRepository.findById(id);
        long chickenHouseId = 0;
        if (byId.isPresent()){
            Cycle cycleToEdit = byId.get();
            cycleToEdit.setStartDay(cycleDto.getStartDay());
            cycleToEdit.setNumberOfChickens(cycleDto.getNumberOfChickens());
            cycleToEdit.setHatchery(cycleDto.getHatchery());
            cycleToEdit.setHybrid(cycleDto.getHybrid());
            cycleRepository.save(cycleToEdit);
            chickenHouseId = cycleToEdit.getChickenHouse().getId();
        }
        return chickenHouseId;
    }

    public long completeCycle(Long id) {
        Optional<Cycle> byId = cycleRepository.findById(id);
        long chickenHouseId = 0;
        if (byId.isPresent()){
            Cycle cycleToEdit = byId.get();
            cycleToEdit.setCompleted(true);
            cycleRepository.save(cycleToEdit);
            chickenHouseId = cycleToEdit.getChickenHouse().getId();
        }
        return chickenHouseId;
    }

    public Optional<CycleDto> getById(Long id) {
        Optional<Cycle> byId = cycleRepository.findById(id);
        CycleDto cycleDto = null;
        if (byId.isPresent()){
            Cycle cycle = byId.get();
            cycleDto = CycleMapper.INSTANCE.mapTo(cycle);
        }
        return Optional.ofNullable(cycleDto);
    }
}
