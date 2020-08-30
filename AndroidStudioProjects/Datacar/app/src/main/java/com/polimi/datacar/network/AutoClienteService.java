package com.polimi.datacar.network;

import com.polimi.datacar.model.Auto;
import com.polimi.datacar.model.Cliente;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AutoClienteService {

    @POST("autocliente")
    Call<Void> addAutoCliente(@Body Auto auto);

    @GET("autocliente/{cod_fiscale}")
    Call<Auto> getAllAutoOfCliente(@Path("cod_fiscale") String cod_fiscale);

    @GET("autocliente/{targa}")
    Call<Cliente> getClienteFromTarga(@Path("targa") String targa);
}
