package com.exemplo.senai.services;

import com.exemplo.senai.dtos.UsuarioDto;
import com.exemplo.senai.entities.UsuarioEntity;
import com.exemplo.senai.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }


    public UsuarioDto realizarLogin(UsuarioDto usuarioDto){

        Optional<UsuarioEntity> usuarioOP = repository.findByEmailAndSenha(usuarioDto.getEmail(), usuarioDto.getSenha());

        UsuarioDto usuarioDtoRetorno = new UsuarioDto();

        if (usuarioOP.isPresent()){

            // usuarioOP.get() = objeto do tipo UsuarioEntity
            // usuarioDto = objeto do tipo UsuarioDto
            // -- achei o usuário e senha
            usuarioDtoRetorno = converterEntityParaDto(usuarioOP.get());

            return usuarioDtoRetorno;
        }

        return usuarioDtoRetorno;

    }

    public List<UsuarioDto> obterListaUsuarios() {

        List<UsuarioDto> listaDto = new ArrayList<>();

        List<UsuarioEntity> listaUsuario = repository.findAll();

        for (UsuarioEntity usuarioEntity : listaUsuario) {
            listaDto.add(converterEntityParaDto(usuarioEntity));
        }

        return listaDto;

    }

    public void usuarioInserir(UsuarioDto usuarioDto){
        repository.save(converterDtoParaEntity(usuarioDto));
    }

    private UsuarioDto converterEntityParaDto (UsuarioEntity usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setId(usuario.getId());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        return usuarioDto;
    }

    private UsuarioEntity converterDtoParaEntity (UsuarioDto usuarioDto) {
        UsuarioEntity usuario = new UsuarioEntity();
        //usuario.setId();
        usuario.setId(usuarioDto.getId());
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(usuarioDto.getSenha());

        return usuario;
    }
}