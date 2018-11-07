package org.marcinski.chickenHouse.controller;

import org.marcinski.chickenHouse.dto.ChickenHouseDto;
import org.marcinski.chickenHouse.dto.UserDto;
import org.marcinski.chickenHouse.service.ChickenHouseService;
import org.marcinski.chickenHouse.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private UserService userService;
    private ChickenHouseService chickenHouseService;

    public HomeController(UserService userService, ChickenHouseService chickenHouseService) {
        this.userService = userService;
        this.chickenHouseService = chickenHouseService;
    }

    @GetMapping("/home")
    public ModelAndView home(HttpServletResponse response, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserDto> userByEmail = userService.findUserByEmail(auth.getName());
        UserDto user = null;
        if (userByEmail.isPresent()){
            user = userByEmail.get();
        }
        if (user != null) {
            modelAndView.addObject("greeting", "Cześć " + user.getName());
            Cookie userCookie = getCookieWithUserId(user);
            response.addCookie(userCookie);
        }
        Cookie[] cookies = request.getCookies();
        Long userId = getUserId(cookies);

        List<ChickenHouseDto> chickenHouseDto = chickenHouseService.getChickenHouseDtoByUserId(userId);

        modelAndView.addObject("houses", chickenHouseDto);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    private Cookie getCookieWithUserId(UserDto user) {
        String name = "userId";
        String value = String.valueOf(user.getId());
        return new Cookie(name, value);
    }

    private Long getUserId(Cookie[] cookies) {
        Long userId = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")){
                userId = Long.valueOf(cookie.getValue());
            }
        }
        return userId;
    }
}