package com.example.examentr.controllers;

import com.example.examentr.dto.UsuarioDto;
import com.example.examentr.dto.UsuarioDto1;
import com.example.examentr.entities.Usuario;
import com.example.examentr.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsusarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsers(){
        return new ResponseEntity<>(usuarioService.getUsers(),HttpStatus.OK);
    }
     @GetMapping("id/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable int id){
        return new ResponseEntity<>(usuarioService.getUserByID(id), HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<Usuario> createUser(@RequestBody UsuarioDto usuario){
        return new ResponseEntity<>(usuarioService.createUser(usuario),HttpStatus.OK);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        return new ResponseEntity<>(
                usuarioService.deleteUserByID(id)?
                        "se borro con exito":
                        "no se pudo borrar",HttpStatus.OK);
    }
    @PutMapping("id/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable int id, @RequestBody UsuarioDto1 usuarioDto){
        return new ResponseEntity<>(usuarioService.updateUser(id, usuarioDto), HttpStatus.OK);
    }
}
