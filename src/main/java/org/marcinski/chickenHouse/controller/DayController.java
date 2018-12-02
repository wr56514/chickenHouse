package org.marcinski.chickenHouse.controller;

import org.marcinski.chickenHouse.dto.DayDto;
import org.marcinski.chickenHouse.dto.ForageDto;
import org.marcinski.chickenHouse.service.DayService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("home/cycle/day")
public class DayController {

    private DayService dayService;

    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @PostMapping("/{cycleId}")
    public ModelAndView createDay(@Valid DayDto dayDto, @PathVariable Long cycleId){
        ModelAndView modelAndView = new ModelAndView();
        dayService.createDay(dayDto, cycleId);

        modelAndView.setViewName("redirect:/home/cycle/" + cycleId);
        return modelAndView;
    }

    @PutMapping("/{dayId}")
    public String editDay(@Valid DayDto dayDto, @PathVariable Long dayId){

        long cycleId = dayService.editDay(dayDto, dayId);

        return "redirect:/home/cycle/" + cycleId;
    }

    @PutMapping("/forage/{dayId}")
    public String addForageToDay(@Valid ForageDto forageDto, @PathVariable Long dayId){

        long cycleId = dayService.addForageToDay(forageDto, dayId);

        return "redirect:/home/cycle/" + cycleId;
    }
}
