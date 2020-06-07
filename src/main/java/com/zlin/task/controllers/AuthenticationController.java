package com.zlin.task.controllers;

import java.util.List;
import java.util.ArrayList;

import com.zlin.task.models.User;
import com.zlin.task.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users/")
public class AuthenticationController {

    @Autowired
    private UserService userRepo;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "users/registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String username, @RequestParam String firstName,
            @RequestParam String lastName, @RequestParam String password,
            @RequestParam String passwordConfirm,
            Model model) {
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setPasswordConfirm(passwordConfirm);;
        System.out.println(password);
        System.out.println(passwordConfirm);
        userRepo.saveUser(user);
        List<String> errors = new ArrayList<String>();
        try {
            userRepo.loadUserByUsername(username);
            userRepo.saveUser(user);
        } 
        catch (Exception e) {
            errors.add("Пользователь с таким именем существует");
        }
        finally{
            model.addAttribute("errors", errors);
        }
        /*try{
            try {
                userRepo.loadUserByUsername(username);
            } catch (Exception e) {
                errors.add("Пользователь с таким именем существует");
            }
            if(password!=passwordConfirm){
                errors.add("Пароли не совпадают");
                throw new Exception("Пароли не совпадают");
            }
            else{
                user.setPassword(password);
            }
            userRepo.saveUser(user);
        }
        catch(Exception ex)
        {
            model.addAttribute("errors", errors);
            return "users/registration";
        }*/        
        return "redirect:/users/";
    }
}