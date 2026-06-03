package com.exemplo.senai.services;

import com.exemplo.senai.dtos.UsuarioDto;
import com.exemplo.senai.entities.UsuarioEntity;
import com.exemplo.senai.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }


    public boolean realizarLogin(UsuarioDto usuarioDto){

        Optional<UsuarioEntity> usuarioOP = repository.findByEmailAndSenha(usuarioDto.getEmail(), usuarioDto.getSenha());

        //se o login existe ele retorna true que e verdadeiro
        if (usuarioOP.isPresent()){
            return true;
        }
        return false;
    }

    //public

}
