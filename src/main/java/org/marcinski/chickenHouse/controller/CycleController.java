package org.marcinski.chickenHouse.controller;

import org.marcinski.chickenHouse.dto.CycleDto;
import org.marcinski.chickenHouse.dto.DayDto;
import org.marcinski.chickenHouse.service.CycleService;
import org.marcinski.chickenHouse.service.DayService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home/cycle")
public class CycleController {

    private CycleService cycleService;
    private DayService dayService;

    public CycleController(CycleService cycleService, DayService dayService) {
        this.cycleService = cycleService;
        this.dayService = dayService;
    }

    @PostMapping("/{chickenHouseId}")
    public ModelAndView createCycle(@Valid CycleDto cycleDto, @PathVariable Long chickenHouseId){
        ModelAndView modelAndView = new ModelAndView();
        cycleService.createCycle(cycleDto, chickenHouseId);
        //TODO validacja
        modelAndView.setViewName("redirect:/home/" + chickenHouseId);

        return modelAndView;
    }

    @PutMapping("/{id}")
    public ModelAndView updateCycle(@Valid CycleDto cycleDto, @PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        long chickenHouseId = cycleService.updateCycle(cycleDto, id);

        modelAndView.setViewName("redirect:/home/cycle/" + cycleDto.getId());

        return modelAndView;
    }

    @PutMapping("complete/{id}")
    public ModelAndView updateCycle(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        long chickenHouseId = cycleService.completeCycle(id);

        modelAndView.setViewName("redirect:/home/" + chickenHouseId);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getCycle(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        Optional<CycleDto> cycleDtoById = cycleService.getById(id);
        if (cycleDtoById.isPresent()){
            CycleDto cycleDto = cycleDtoById.get();
            DayDto dayDto = new DayDto();
            //TODO
            List<DayDto> dayDtos = dayService.getAllDaysByCycleIdSortedByDayNumber(id);
            int actualDayNumber = 1;
            if (dayDtos.size() > 0){
                actualDayNumber = dayDtos.get(0).getDayNumber() + 1;
            }
            dayDto.setDayNumber(actualDayNumber);

            int actualNumberOfChicken = cycleDto.getNumberOfChickens();
            for (DayDto dto : dayDtos) {
                int allDowns = dto.getNaturalDowns() + dto.getSelectionDowns();
                actualNumberOfChicken -= allDowns;
            }

            modelAndView.addObject("cycle", cycleDto);
            modelAndView.addObject("dayDto", dayDto);
            modelAndView.addObject("actualNumberOfChicken", actualNumberOfChicken);
            modelAndView.addObject("days", dayDtos);
        }
        modelAndView.setViewName("cycle");

        return modelAndView;
    }
}
