package com.polimi.datacar.controller;

import com.polimi.datacar.BuildConfig;
import com.polimi.datacar.model.Officina;
import com.polimi.datacar.network.OfficinaService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OfficinaController implements OfficinaService {

    private Retrofit retrofit;
    private static final String BASE_URL = BuildConfig.BASE_URL;

    public OfficinaController() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public Call<Integer> registerOfficina(Officina officina) {
        return retrofit.create(OfficinaService.class).registerOfficina(officina);
    }

    @Override
    public Call<Integer> officinaLogin(String email, String password) {
        return retrofit.create(OfficinaService.class).officinaLogin(email,password);
    }

    @Override
    public Call<Void> updateOfficina(String p_iva, Officina officina) {
        return retrofit.create(OfficinaService.class).updateOfficina(p_iva,officina);
    }
}
