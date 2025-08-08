package com.bootcamp.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClienteResponse {
    private Long usuario_id;
    private String nombre;
    private String email;
    private String direccion;
    private String telefono; // <- ¡esto debe existir!
    private Integer edad;
    private String genero;
    private String tipo_usuario;
    public boolean activo;
    }

