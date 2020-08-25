package com.polimi.datacar.model;

import java.util.List;


public class Cliente {


    private int id;

    private String nome;

    private String cognome;

    private String citta;

    private String cap;

    private String indirizzo;

    private String sesso;

    private String cod_fiscale;

    private int telefono;

    private String email;

    private int targhe_associate;

    private List<AutoCliente> autoClienteList;

    List<OfficinaCliente> officinaClienteList;

    public Cliente(){
    }

    public Cliente(String nome, String cognome, String citta, String cap, String indirizzo, String sesso, String cod_fiscale, int telefono, String email, int targhe_associate) {
        this.nome = nome;
        this.cognome = cognome;
        this.citta = citta;
        this.cap = cap;
        this.indirizzo = indirizzo;
        this.sesso = sesso;
        this.cod_fiscale = cod_fiscale;
        this.telefono = telefono;
        this.email = email;
        this.targhe_associate = targhe_associate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id_cliente) {
        this.id = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getCod_fiscale() {
        return cod_fiscale;
    }

    public void setCod_fiscale(String cod_fiscale) {
        this.cod_fiscale = cod_fiscale;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
            return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AutoCliente> getAutoClienteList() {
        return autoClienteList;
    }

    public void setAutoClienteList(List<AutoCliente> autoClienteList) {
        this.autoClienteList = autoClienteList;
    }

    public List<OfficinaCliente> getOfficinaClienteList() {
        return officinaClienteList;
    }

    public void setOfficinaClienteList(List<OfficinaCliente> officinaClienteList) {
        this.officinaClienteList = officinaClienteList;
    }

    public int getTarghe_associate() {
        return targhe_associate;
    }

    public void setTarghe_associate(int targhe_associate) {
        this.targhe_associate = targhe_associate;
    }
}
