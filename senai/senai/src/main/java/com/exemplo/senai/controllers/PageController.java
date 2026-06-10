package com.exemplo.senai.controllers;

import com.exemplo.senai.dtos.UsuarioDto;
import com.exemplo.senai.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/usuariolista")
    public String getUsuarioLista(Model model){

        model.addAttribute("usuarios", service.obterListaUsuarios());
        return "usuariolista";
    }

    @GetMapping("/usuarioinserir")
    public String getUsuarioInserir(Model model){

        UsuarioDto dto = new UsuarioDto();
        model.addAttribute("usuario",dto);

        return "usuarioinserir";
    }

    @GetMapping("/atualizar/{Id}")
    public String get(Model model){

        return "/atualizar/{Id}";

    }
}
