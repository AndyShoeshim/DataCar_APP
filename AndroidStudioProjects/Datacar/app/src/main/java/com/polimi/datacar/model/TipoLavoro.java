package com.polimi.datacar.model;

import java.util.List;

public class TipoLavoro {

    private int id;

    private String categoriaLavoro;

    List<Lavoro> lavoroEntities;

    public TipoLavoro() {
    }

    public List<Lavoro> getLavoroEntities() {
        return lavoroEntities;
    }

    public String getCategoriaLavoro() {
        return categoriaLavoro;
    }

    public int getId() {
        return id;
    }
}
