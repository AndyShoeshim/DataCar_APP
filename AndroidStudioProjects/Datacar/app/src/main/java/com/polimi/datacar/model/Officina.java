package com.polimi.datacar.model;

import java.util.List;

public class Officina {

    private int id_officina;

    private String rag_sociale;

    private String p_iva;

    private String email;

    private String password;

    private int num_telefono;

    private String indirizzo;

    List<OfficinaCliente> officinaClienteList;

    public Officina(){
    }

    public Officina(Officina officina){
        this.rag_sociale = officina.getRag_sociale();
        this.email = officina.getEmail();
        this.password = officina.getPassword();
        this.p_iva = officina.getP_iva();
        this.num_telefono = officina.getNum_telefono();
        this.indirizzo = officina.getIndirizzo();
    }

    public int getId_officina() {
        return id_officina;
    }

    public void setId_officina(int id_officina) {
        this.id_officina = id_officina;
    }

    public String getRag_sociale() {
        return rag_sociale;
    }

    public void setRag_sociale(String rag_sociale) {
        this.rag_sociale = rag_sociale;
    }

    public String getP_iva() {
        return p_iva;
    }

    public void setP_iva(String p_iva) {
        this.p_iva = p_iva;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNum_telefono() {
        return num_telefono;
    }

    public void setNum_telefono(int num_telefono) {
        this.num_telefono = num_telefono;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setOfficinaClienteList(List<OfficinaCliente> officinaClienteList) {
        this.officinaClienteList = officinaClienteList;
    }

    public List<OfficinaCliente> getOfficinaClienteList() {
        return officinaClienteList;
    }
}
