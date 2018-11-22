package org.marcinski.chickenHouse.controller;

import org.marcinski.chickenHouse.dto.ChickenHouseDto;
import org.marcinski.chickenHouse.dto.CycleDto;
import org.marcinski.chickenHouse.dto.UserDto;
import org.marcinski.chickenHouse.service.ChickenHouseService;
import org.marcinski.chickenHouse.service.CycleService;
import org.marcinski.chickenHouse.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    private UserService userService;

    public HouseController(ChickenHouseService chickenHouseService, CycleService cycleService,
                           UserService userService) {
        this.chickenHouseService = chickenHouseService;
        this.cycleService = cycleService;
        this.userService = userService;
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

    @PostMapping("/new_house")
    public ModelAndView addNewHouse(@Valid ChickenHouseDto chickenHouseDto,
                                    BindingResult bindingResult,
                                    Principal principal){
        ModelAndView modelAndView = new ModelAndView();

        String email = principal.getName();

        Optional<UserDto> userByEmail = userService.findUserByEmail(email);
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("redirect:/home");
        }
        else {
            if (userByEmail.isPresent()) {
                chickenHouseDto.setUserDto(userByEmail.get());
                chickenHouseService.saveChickenHouse(chickenHouseDto);
                modelAndView.addObject("chickenHouseDto", new ChickenHouseDto());
            }
        }

        return new ModelAndView("redirect:/home");
    }

    @PutMapping("/{id}")
    public ModelAndView editHouse(@Valid ChickenHouseDto chickenHouseDto,
                                  @PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();

        Optional<ChickenHouseDto> chickenHouseById = chickenHouseService.getChickenHouseById(id);

        if (chickenHouseById.isPresent()){
            ChickenHouseDto house = chickenHouseById.get();
            house.setName(chickenHouseDto.getName());
            house.setAreaOfHouse(chickenHouseDto.getAreaOfHouse());
            chickenHouseService.saveChickenHouse(house);
            modelAndView.setViewName("redirect:/home/");
        }else {
            modelAndView.setViewName("redirect:/home/");
        }
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String deleteHouse(@PathVariable Long id){
        chickenHouseService.deteleHouse(id);
        return "redirect:/home/";
    }
}
