package com.polimi.datacar.model;


public class AutoCliente {

    private int id;

    private Cliente id_cliente;

    private Auto id_auto;

    private String targa;

    public AutoCliente(){
    }

    public AutoCliente(String targa, Cliente id_cliente, Auto id_auto){
        this.targa = targa;
        this.id_cliente = id_cliente;
        this.id_auto = id_auto;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getTarga() {
        return targa;
    }

    public int getId() {
        return id;
    }

    public Auto getId_auto() {
        return id_auto;
    }

    public Cliente getId_cliente() {
        return id_cliente;
    }
}
