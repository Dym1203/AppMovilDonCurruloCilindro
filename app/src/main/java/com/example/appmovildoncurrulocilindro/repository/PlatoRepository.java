package com.example.appmovildoncurrulocilindro.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appmovildoncurrulocilindro.api.ConfigApi;
import com.example.appmovildoncurrulocilindro.api.PlatoApi;
import com.example.appmovildoncurrulocilindro.model.GenericResponse;
import com.example.appmovildoncurrulocilindro.model.service.Plato;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlatoRepository {
    private final PlatoApi api;
    private static PlatoRepository repository;

    public PlatoRepository() {
        this.api = ConfigApi.getPlatoApi();
    }

    public static PlatoRepository getInstance() {
        if (repository == null) {
            repository = new PlatoRepository();
        }
        return repository;
    }

    public LiveData<GenericResponse<List<Plato>>> listarPlatosRecomendados() {
        final MutableLiveData<GenericResponse<List<Plato>>> mld = new MutableLiveData<>();
        this.api.listarPlatosRecomendados().enqueue(new Callback<GenericResponse<List<Plato>>>() {
            @Override
            public void onResponse(Call<GenericResponse<List<Plato>>> call, Response<GenericResponse<List<Plato>>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<List<Plato>>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                t.printStackTrace();
            }
        });
        return mld;
    }

    public LiveData<GenericResponse<List<Plato>>> listarPlatosPorCategoria(int idC) {
        final MutableLiveData<GenericResponse<List<Plato>>> mld = new MutableLiveData<>();
        this.api.listarPlatosPorCategoria(idC).enqueue(new Callback<GenericResponse<List<Plato>>>() {
            @Override
            public void onResponse(Call<GenericResponse<List<Plato>>> call, Response<GenericResponse<List<Plato>>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<List<Plato>>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                t.printStackTrace();
            }
        });
        return mld;
    }
}