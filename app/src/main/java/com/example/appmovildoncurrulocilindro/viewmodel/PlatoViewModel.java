package com.example.appmovildoncurrulocilindro.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appmovildoncurrulocilindro.model.GenericResponse;
import com.example.appmovildoncurrulocilindro.model.service.Plato;
import com.example.appmovildoncurrulocilindro.repository.PlatoRepository;

import java.util.List;

public class PlatoViewModel extends AndroidViewModel {
    private final PlatoRepository repository;

    public PlatoViewModel(@NonNull Application application) {
        super(application);
        repository = PlatoRepository.getInstance();
    }

    public LiveData<GenericResponse<List<Plato>>> listarPlatosRecomendados() {
        return this.repository.listarPlatosRecomendados();
    }

    public LiveData<GenericResponse<List<Plato>>> listarPlatosPorCategoria(int idC) {
        return this.repository.listarPlatosPorCategoria(idC);
    }
}