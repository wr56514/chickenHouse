package org.marcinski.chickenHouse.controller;

import org.marcinski.chickenHouse.dto.ChickenHouseDto;
import org.marcinski.chickenHouse.dto.CycleDto;
import org.marcinski.chickenHouse.service.ChickenHouseService;
import org.marcinski.chickenHouse.service.CycleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HouseController {

    private ChickenHouseService chickenHouseService;
    private CycleService cycleService;

    public HouseController(ChickenHouseService chickenHouseService, CycleService cycleService) {
        this.chickenHouseService = chickenHouseService;
        this.cycleService = cycleService;
    }

    @GetMapping("/{id}")
    public ModelAndView house(@PathVariable Long id, Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        CycleDto cycleDto = new CycleDto();

        Optional<ChickenHouseDto> chickenHouseById = chickenHouseService.getChickenHouseById(id);
        if (chickenHouseById.isPresent()) {
            String userEmailFromHouse = chickenHouseById.get().getUserDto().getEmail();
            String userEmailFromPrincipal = principal.getName();

            if (userEmailFromHouse.equals(userEmailFromPrincipal)) {
                List<CycleDto> cycleDtos = cycleService.getAllByChickenHouseId(id);
                modelAndView.addObject("house", chickenHouseById.get());
                //TODO nie działa dodawanie daty
                modelAndView.addObject("date", LocalDate.now());
                modelAndView.addObject("cycleDto", cycleDto);
                modelAndView.addObject("cyclesDto", cycleDtos);
                modelAndView.setViewName("house");
            } else {
                modelAndView.setViewName("redirect:/home");
            }
        }else {
            modelAndView.setViewName("redirect:/home");
        }

        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView createCycle(@Valid CycleDto cycleDto, @PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();

        cycleService.createCycle(cycleDto, id);
        //TODO validacja
        modelAndView.setViewName("redirect:/home/" + id);

        return modelAndView;
    }
}
