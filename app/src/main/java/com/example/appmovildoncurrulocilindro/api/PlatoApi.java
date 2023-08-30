package com.example.appmovildoncurrulocilindro.api;

import com.example.appmovildoncurrulocilindro.model.GenericResponse;
import com.example.appmovildoncurrulocilindro.model.service.Plato;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlatoApi {
    String base = "api/plato";

    @GET(base)
    Call<GenericResponse<List<Plato>>> listarPlatosRecomendados();

    @GET(base + "/{idC}")
    Call<GenericResponse<List<Plato>>> listarPlatosPorCategoria(@Path("idC") int idC);
}
