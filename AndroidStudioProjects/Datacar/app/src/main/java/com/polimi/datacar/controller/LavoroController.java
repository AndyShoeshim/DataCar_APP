package com.polimi.datacar.controller;

import com.google.gson.GsonBuilder;
import com.polimi.datacar.BuildConfig;
import com.polimi.datacar.model.Lavoro;
import com.polimi.datacar.network.LavoroService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LavoroController implements LavoroService {

    private Retrofit retrofit;
    private static final String BASE_URL = BuildConfig.BASE_URL;

    public LavoroController(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("dd.MM.yyyy").create())).build();


    }

    @Override
    public Call<List<Lavoro>> getAllLavoro(int id_officina) {
        return retrofit.create(LavoroService.class).getAllLavoro(id_officina);
    }

    @Override
    public Call<List<Lavoro>> getLavoroOfCliente(int id_officina, String cod_fiscale) {
        return retrofit.create(LavoroService.class).getLavoroOfCliente(id_officina,cod_fiscale);
    }

    @Override
    public Call<Void> addLavoro(int id_officina, Lavoro lavoro) {
        return retrofit.create(LavoroService.class).addLavoro(id_officina,lavoro);
    }
}
