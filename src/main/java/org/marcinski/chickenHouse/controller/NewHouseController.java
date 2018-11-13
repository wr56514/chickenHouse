package org.marcinski.chickenHouse.controller;

import org.marcinski.chickenHouse.dto.ChickenHouseDto;
import org.marcinski.chickenHouse.dto.UserDto;
import org.marcinski.chickenHouse.service.ChickenHouseService;
import org.marcinski.chickenHouse.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class NewHouseController {

    private UserService userService;
    private ChickenHouseService chickenHouseService;

    public NewHouseController(UserService userService, ChickenHouseService chickenHouseService) {
        this.userService = userService;
        this.chickenHouseService = chickenHouseService;
    }

    @GetMapping("/new_house")
    public ModelAndView newHouse(){
        ModelAndView modelAndView = new ModelAndView();
        ChickenHouseDto chickenHouseDto = new ChickenHouseDto();
        modelAndView.addObject("chickenHouseDto", chickenHouseDto);
        modelAndView.setViewName("new_house");
        return modelAndView;
    }

    @PostMapping("/new_house")
    public ModelAndView addNewHouse(@Valid ChickenHouseDto chickenHouseDto,
                                    BindingResult bindingResult,
                                    HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        Cookie[] cookies = request.getCookies();
        String userUUID = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")){
                userUUID = cookie.getValue();
            }
        }

        Optional<UserDto> userById = userService.findUserByUUID(userUUID);
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("new_house");
        }
        else {
            if (userById.isPresent()) {
                chickenHouseDto.setUserDto(userById.get());
                chickenHouseService.saveChickenHouse(chickenHouseDto);
                modelAndView.addObject("chickenHouseDto", new ChickenHouseDto());
            }
        }

        return new ModelAndView("redirect:/home");
    }
}
