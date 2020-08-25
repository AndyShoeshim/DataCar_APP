package com.polimi.datacar.model;


public class OfficinaCliente {

    private int id;

    private Officina id_officina;

    private Cliente id_cliente;

    public OfficinaCliente() {
    }

    public OfficinaCliente(Officina id_officina, Cliente id_cliente) {
        this.id_officina = id_officina;
        this.id_cliente = id_cliente;
    }

    public Cliente getId_cliente() {
        return id_cliente;
    }

    public Officina getId_officina() {
        return id_officina;
    }


}
