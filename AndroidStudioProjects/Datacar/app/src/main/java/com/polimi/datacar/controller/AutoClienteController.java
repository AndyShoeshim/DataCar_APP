package com.polimi.datacar.controller;

import com.polimi.datacar.BuildConfig;
import com.polimi.datacar.model.Auto;
import com.polimi.datacar.model.Cliente;
import com.polimi.datacar.network.AutoClienteService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AutoClienteController implements AutoClienteService {

    private Retrofit retrofit;
    private static final String BASE_URL = BuildConfig.BASE_URL;

    public AutoClienteController() {
        this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Override
    public Call<Void> addAutoCliente(Auto auto) {
        return retrofit.create(AutoClienteService.class).addAutoCliente(auto);
    }

    @Override
    public Call<Auto> getAllAutoOfCliente(String cod_fiscale) {
        return retrofit.create(AutoClienteService.class).getAllAutoOfCliente(cod_fiscale);
    }

    @Override
    public Call<Cliente> getClienteFromTarga(String targa) {
        return retrofit.create(AutoClienteService.class).getClienteFromTarga(targa);
    }
}
