package com.example.appmovildoncurrulocilindro.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appmovildoncurrulocilindro.api.ConfigApi;
import com.example.appmovildoncurrulocilindro.api.UsuarioApi;
import com.example.appmovildoncurrulocilindro.model.GenericResponse;
import com.example.appmovildoncurrulocilindro.model.service.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRepository {
    private static UsuarioRepository repository;
    private final UsuarioApi api;

    public UsuarioRepository() {
        this.api = ConfigApi.getUsuarioApi();
    }

    public static UsuarioRepository getInstance(){
        if(repository==null){
            repository= new UsuarioRepository();
        }
        return repository;
    }

    public LiveData<GenericResponse<Usuario>>login(String email, String contrasenia){
        final MutableLiveData<GenericResponse<Usuario>> mdl= new MutableLiveData<>();
        this.api.login(email,contrasenia).enqueue(new Callback<GenericResponse<Usuario>>() {
            @Override
            public void onResponse(Call<GenericResponse<Usuario>> call, Response<GenericResponse<Usuario>> response) {
                mdl.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<Usuario>> call, Throwable t) {
                mdl.setValue(new GenericResponse());
                System.out.println("Error al iniciar sesion " + t.getMessage());
            }
        });
        return mdl;
    }

    public LiveData<GenericResponse<Usuario>> save (Usuario u){
        final MutableLiveData<GenericResponse<Usuario>> mld = new MutableLiveData<>();
        this.api.save(u).enqueue(new Callback<GenericResponse<Usuario>>() {
            @Override
            public void onResponse(Call<GenericResponse<Usuario>> call, Response<GenericResponse<Usuario>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<Usuario>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                System.out.println("Se ha producido un error : " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }
}
