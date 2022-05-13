package com.school.work.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String main(Model m){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model m){
        System.out.println("Home page Started");

        return "Myindex";
    }

    @GetMapping("/error")
    public String Error(Model m){
        System.out.println("Error page Started");
        String msg = "This page can’t be reached. Either you don't have rights to this page or this page is not available.";
        m.addAttribute("msg", msg);

        return "error_page";
    }



}