package com.polimi.datacar.network;

import com.polimi.datacar.model.Lavoro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LavoroService {

    @GET("lavoro/{id_officina}")
    Call<List<Lavoro>> getAllLavoro(@Path("id_officina") int id_officina);

    @GET("lavoro/{id_officina}/{cod_fiscale}")
    Call<List<Lavoro>> getLavoroOfCliente(@Path("id_officina") int id_officina, @Path("cod_fiscale") String cod_fiscale);

    @POST("lavoro/{id_officina}")
    @Headers({
            "Content-Type: application/json"
    })
    Call<Void> addLavoro(@Path("id_officina") int id_officina, @Body Lavoro lavoro);
}
