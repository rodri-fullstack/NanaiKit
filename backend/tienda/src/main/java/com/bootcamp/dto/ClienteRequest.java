package com.bootcamp.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ClienteRequest {
    private String nombre;
    private String email;
    private String contrasena;
    private String direccion;
    private String telefono;
    private Integer edad;
    private String genero;
    private String tipo_usuario;

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }
}

