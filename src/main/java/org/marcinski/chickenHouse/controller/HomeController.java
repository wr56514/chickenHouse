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
            Cookie userCookie = getCookieWithUserUUID(user);
            response.addCookie(userCookie);
        }
        Cookie[] cookies = request.getCookies();
        String userUUID = getUserId(cookies);

        List<ChickenHouseDto> chickenHouseDto = chickenHouseService.findChickenHousesDtoByUserUUID(userUUID);

        modelAndView.addObject("houses", chickenHouseDto);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    private Cookie getCookieWithUserUUID(UserDto user) {
        String name = "user";
        String value = user.getUuid().toString();
        return new Cookie(name, value);
    }

    private String getUserId(Cookie[] cookies) {
        String userId = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")){
                userId = cookie.getValue();
            }
        }
        return userId;
    }
}