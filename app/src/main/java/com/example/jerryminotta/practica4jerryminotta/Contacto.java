package com.example.jerryminotta.practica4jerryminotta;

import android.widget.ImageView;

public class Contacto {

   private String nombre, sexo;
   private String numero;
   ImageView iv_iconoSexo;


    public Contacto(String nombre, String sexo, String numero) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
