package org.marcinski.chickenHouse.controller;

import org.marcinski.chickenHouse.entity.User;
import org.marcinski.chickenHouse.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> userExists = userService.findUserByEmail(user.getEmail());
        if (!userExists.equals(Optional.empty())){
            bindingResult.rejectValue("email", "error.user",
                    "Istnieje już użytkownik o podanym emailu!");
        }
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("registration");
        }else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Użytkownik został zarejestrowany");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }
}
