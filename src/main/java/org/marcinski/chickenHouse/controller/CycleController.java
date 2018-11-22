package org.marcinski.chickenHouse.controller;

import org.marcinski.chickenHouse.dto.CycleDto;
import org.marcinski.chickenHouse.service.CycleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/home")
public class CycleController {

    private CycleService cycleService;

    public CycleController(CycleService cycleService) {
        this.cycleService = cycleService;
    }

    @PostMapping("/{id}")
    public ModelAndView createCycle(@Valid CycleDto cycleDto, @PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        //TODO o co chodzi ustawia id cycle na parametr id???
        cycleDto.setId(null);
        cycleService.createCycle(cycleDto, id);
        //TODO validacja
        modelAndView.setViewName("redirect:/home/" + id);

        return modelAndView;
    }
}
