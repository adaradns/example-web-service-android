package com.service.web.example.adara.examplwebservice.POJO;

/**
 * Created by Adara on 10/30/2016.
 */
public class Usuario {

    private int usuarioId;
    private String nombre;
    private String email;

    public Usuario(int usuarioId, String nombre, String email){
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.email = email;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
