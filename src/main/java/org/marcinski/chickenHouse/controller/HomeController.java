package org.marcinski.chickenHouse.controller;

import org.marcinski.chickenHouse.model.User;
import org.marcinski.chickenHouse.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class HomeController {

    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userByEmail = userService.findUserByEmail(auth.getName());
        User user = null;
        if (userByEmail.isPresent()){
            user = userByEmail.get();
        }
        if (user != null) {
            modelAndView.addObject("userName", "Witamy " + user.getName() + " (" + user.getEmail() + ")");
        }
        modelAndView.addObject("userMessage","Zawartość widoczna tylko dla zalogowanych");
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
