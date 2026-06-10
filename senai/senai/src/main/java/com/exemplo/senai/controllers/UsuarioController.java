package com.exemplo.senai.controllers;

import com.exemplo.senai.dtos.UsuarioDto;
import com.exemplo.senai.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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

        UsuarioDto usuarioDtoRetorno = service.realizarLogin(usuarioDto);

        if (usuarioDtoRetorno.getNome() != null) {

            redirectAttributes.addFlashAttribute("mensagem","Bem-Vindo, " + usuarioDtoRetorno.getNome());
            return "redirect:/home";
        }

        model.addAttribute("erro","E-mail ou senha inválido.");
        return "login";
    }

    @PostMapping("/usuarioinserir")
    public String usuarioInserir(@Valid @ModelAttribute("usuario") UsuarioDto usuarioDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){

       if (bindingResult.hasErrors()){
            return "usuarioinserir";
        }

        service.usuarioInserir(usuarioDto);

        redirectAttributes.addFlashAttribute("mensagem", "Usuario cadastrado com sucesso.");

        return "redirect:/usuariolista";
    }


}
