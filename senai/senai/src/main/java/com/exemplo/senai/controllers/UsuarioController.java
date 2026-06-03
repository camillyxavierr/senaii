package com.exemplo.senai.controllers;

import com.exemplo.senai.dtos.UsuarioDto;
import com.exemplo.senai.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }


    @PostMapping("/login")
    public String realizarLogin(String email, String senha, Model model, RedirectAttributes redirectAttributes) {

        //SYSTEM OUT serve para inprimir o codigo e ver oq pode esta errado
        System.out.println("email"+email+"senha"+senha);
        //criacao de uma varial baseada no dto
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setEmail(email);
        usuarioDto.setSenha(senha);

        boolean retorno = service.realizarLogin(usuarioDto);

        System.out.println("retorno"+retorno);
        //Ver se está retornando false ou verdadeiro/true
        if (retorno) {
            redirectAttributes.addFlashAttribute("usuario","Bem-vindo joão");
            return "redirect:/home";
        }
        model.addAttribute("erro","E-mail ou senha invalidos.");
        return "login";

    }
}
