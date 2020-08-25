package com.polimi.datacar.model;

import java.util.List;

public class DescrizioneLavoro {


    int id;

    String descrizioneLavoro;

    List<Lavoro> lavoroEntities;

    public DescrizioneLavoro() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescrizioneLavoro(String descrizioneLavoro) {
        this.descrizioneLavoro = descrizioneLavoro;
    }

    public void setLavoroEntities(List<Lavoro> lavoroEntities) {
        this.lavoroEntities = lavoroEntities;
    }

    public int getId() {
        return id;
    }

    public String getDescrizioneLavoro() {
        return descrizioneLavoro;
    }

    public List<Lavoro> getLavoroEntities() {
        return lavoroEntities;
    }
}
