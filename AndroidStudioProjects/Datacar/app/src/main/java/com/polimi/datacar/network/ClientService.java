package com.polimi.datacar.network;

import com.polimi.datacar.model.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClientService {


    @GET("cliente/{id_officina}")
    Call<List<Cliente>> getAllClient(@Path("id_officina") int id_officina);

    @GET("cliente/{id_officina}/{cod_fiscale}")
    Call<Cliente> getSpecificClient(@Path("id_officina") int id_officina, @Path("cod_fiscale") String cod_fiscale);

    @POST("cliente/{id_officina}")
    @Headers({
            "Content-Type: application/json"
    })
    Call<Void> addClient(@Path("id_officina") int id_officina, @Body Cliente cliente);

    @PUT("cliente/{cod_fiscale}")
    @Headers({
            "Content-Type: application/json"
    })
    Call<Cliente> updateClientInfo(@Path("cod_fiscale") String cod_fiscale, @Body Cliente Cliente);

    @DELETE("cliente/{cod_fiscale}")
    Call<Void> deleteClient(@Path("cod_fiscale") String cod_fiscale);


}
