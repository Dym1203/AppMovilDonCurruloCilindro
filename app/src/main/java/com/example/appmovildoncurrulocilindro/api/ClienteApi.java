package com.example.appmovildoncurrulocilindro.api;

import com.example.appmovildoncurrulocilindro.model.GenericResponse;
import com.example.appmovildoncurrulocilindro.model.service.Cliente;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClienteApi {
    String base = "api/cliente";

    @POST(base)
    Call<GenericResponse<Cliente>> guardarCliente(@Body Cliente c);
}
