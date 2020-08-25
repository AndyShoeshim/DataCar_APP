package com.polimi.datacar.model;

import java.util.List;


public class Auto {

    private int id;

    private String marca;

    private String modello;

    private String motore;

    private String cilindrata;

    private String carburante;

    private String cavalli;

    private List<AutoCliente> autoClienteEntities;


    public Auto() {
    }

    public Auto(String marca, String modello, String motore, String cilindrata, String carburante, String cavalli) {
        this.marca = marca;
        this.modello = modello;
        this.motore = motore;
        this.cilindrata = cilindrata;
        this.carburante = carburante;
        this.cavalli = cavalli;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getMotore() {
        return motore;
    }

    public void setMotore(String motore) {
        this.motore = motore;
    }

    public String getCilindrata() {
        return cilindrata;
    }

    public void setCilindrata(String cilindrata) {
        this.cilindrata = cilindrata;
    }

    public String getCarburante() {
        return carburante;
    }

    public void setCarburante(String carburante) {
        this.carburante = carburante;
    }

    public String getCavalli() {
        return cavalli;
    }

    public void setCavalli(String cavalli) {
        this.cavalli = cavalli;
    }

    public void setAutoClienteEntities(List<AutoCliente> autoClienteEntities) {
        this.autoClienteEntities = autoClienteEntities;
    }

    public List<AutoCliente> getAutoClienteEntities() {
        return autoClienteEntities;
    }
}
