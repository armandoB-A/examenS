package com.example.examentr.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.examentr.entities.Usuario}
 */
@Value
public class UsuarioDto1 implements Serializable {
    String nombre;
    String correoElectronico;
    String contraseña;
    List<Integer> roles;
}