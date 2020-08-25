package com.polimi.datacar.network;

import com.polimi.datacar.model.Officina;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OfficinaService {


    @POST("officina")
    @Headers({
            "Content-Type: application/json"
    })
    Call<Integer> registerOfficina(@Body Officina officina);

    @GET("officina/{email}/{password}")
    Call<Integer> officinaLogin(@Path("email") String email, @Path("password") String password);

    @PUT("officina/{p_iva}")
    @Headers({
            "Content-Type: application/json"
    })
    Call<Void> updateOfficina(@Path("p_iva") String p_iva, @Body Officina officina);


}
