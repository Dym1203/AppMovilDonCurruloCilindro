package com.example.appmovildoncurrulocilindro.api;

import com.example.appmovildoncurrulocilindro.model.GenericResponse;
import com.example.appmovildoncurrulocilindro.model.service.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriaApi {
    String base = "api/categoria";

    @GET(base)
    Call<GenericResponse<List<Categoria>>> listarCategoriasActivas();
}
