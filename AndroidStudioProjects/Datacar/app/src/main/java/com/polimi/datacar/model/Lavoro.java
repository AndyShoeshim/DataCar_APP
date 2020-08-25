package com.polimi.datacar.model;


import java.sql.Date;


public class Lavoro  {

    private Officina id_officina;

    private String targa;

    private String tipoLavoro;

    private String descLavoro;

    private Date dataScandenza;

    private boolean effettuato;

    public Lavoro() {
    }

    public Lavoro(Officina id_officina, String targa, String tipoLavoro, String descLavoro, Date dataScandenza, boolean effettuato) {
        this.id_officina = id_officina;
        this.targa = targa;
        this.tipoLavoro = tipoLavoro;
        this.descLavoro = descLavoro;
        this.dataScandenza = dataScandenza;
        this.effettuato = effettuato;
    }


    public Officina getId_officina() {
        return id_officina;
    }

    public String getTipoLavoro() {
        return tipoLavoro;
    }

    public void setId_officina(Officina id_officina) {
        this.id_officina = id_officina;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public void setTipoLavoro(String tipoLavoro) {
        this.tipoLavoro = tipoLavoro;
    }

    public void setDescLavoro(String descLavoro) {
        this.descLavoro = descLavoro;
    }

    public String getDescLavoro() {
        return descLavoro;
    }

    public String getTarga() {
        return targa;
    }

    public Date getDataScandenza() {
        return dataScandenza;
    }

    public void setEffettuato(boolean effettuato) {
        this.effettuato = effettuato;
    }

    public boolean isEffettuato() {
        return effettuato;
    }
}
