package com.example.examentr.services;

import com.example.examentr.dto.RolDto;
import com.example.examentr.dto.UsuarioDto;
import com.example.examentr.dto.UsuarioDto1;
import com.example.examentr.entities.Rol;
import com.example.examentr.entities.Usuario;
import com.example.examentr.repositories.RolRepository;
import com.example.examentr.repositories.UsuarioRepository;
import com.example.examentr.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsers(){
        return usuarioRepository.findAll();
    }
    public Usuario getUserByID(int id){
        return usuarioRepository.findById(id).isPresent()?usuarioRepository.findById(id).get():null;
    }

    public Usuario createUser(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setContraseña(usuarioDto.getContraseña());
        usuario.setCorreoElectronico(usuarioDto.getCorreoElectronico());
        return usuarioRepository.save(usuario);
    }
    public boolean deleteUserByID(int id){
        if (usuarioRepository.findById(id).isPresent()){
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Usuario updateUser(int id, UsuarioDto1 usuarioDto1){

        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.setNombre(usuarioDto1.getNombre());
        usuario.setContraseña(usuarioDto1.getContraseña());
        usuario.setCorreoElectronico(usuarioDto1.getCorreoElectronico());
        List<Rol> roles;
        roles=rolRepository.findAllById(usuarioDto1.getRoles());
        usuario.setRoles(roles);
        return usuarioRepository.save(usuario);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = usuarioRepository.findByNombre(username);

        if(user.isPresent()){
            return new SecurityUser(user.get());
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}
