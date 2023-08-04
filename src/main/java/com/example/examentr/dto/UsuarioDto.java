package com.example.examentr.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.examentr.entities.Usuario}
 */
@Value
public class UsuarioDto {
    String nombre;
    String correoElectronico;
    String contraseña;
}