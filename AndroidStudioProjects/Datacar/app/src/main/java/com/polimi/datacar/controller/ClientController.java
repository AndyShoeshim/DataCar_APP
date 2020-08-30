package com.polimi.datacar.controller;

import com.polimi.datacar.BuildConfig;
import com.polimi.datacar.model.Cliente;
import com.polimi.datacar.network.ClientService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientController implements ClientService {

    private Retrofit retrofit;
    private static final String BASE_URL = BuildConfig.BASE_URL;


    public ClientController() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build();
    }


    @Override
    public Call<List<Cliente>> getAllClient(int id_officina) {
        return retrofit.create(ClientService.class).getAllClient(id_officina);
    }

    @Override
    public Call<Cliente> getSpecificClient(int id_officina, String cod_fiscale) {
        return retrofit.create(ClientService.class).getSpecificClient(id_officina,cod_fiscale);
    }

    @Override
    public Call<Void> addClient(int id_officina, Cliente obj) {
        return retrofit.create(ClientService.class).addClient(id_officina,obj);
    }

    @Override
    public Call<Cliente> updateClientInfo(int id_cliente, Cliente obj) {
        return retrofit.create(ClientService.class).updateClientInfo(id_cliente,obj);
    }

    @Override
    public Call<Void> deleteClient(int id_officina, String cod_fiscale) {
        return retrofit.create(ClientService.class).deleteClient(id_officina, cod_fiscale);
    }
}
