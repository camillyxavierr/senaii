package com.exemplo.senai.controllers;

import com.exemplo.senai.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//usar um controller para der so os gets/ que vam ser para mostrar a dela
@Controller
public class PageController {

    final UsuarioService service;

    public PageController(UsuarioService service) {
        this.service = service;
    }

    //isso e para cair na dela de login
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/")
    public String getIndex(){

        return "redirect:/login";
    }

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }

}
