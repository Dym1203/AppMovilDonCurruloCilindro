package com.example.appmovildoncurrulocilindro.api;

import com.example.appmovildoncurrulocilindro.model.GenericResponse;
import com.example.appmovildoncurrulocilindro.model.service.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsuarioApi {
    //Ruta del controller usuario
    String base="api/usuario";

    //RUTA DEL CONTROLARDOR USUARIO + METODO
    @FormUrlEncoded
    @POST (base + "/login")
    Call<GenericResponse<Usuario>> login(@Field("email") String email, @Field("pass") String contrasenia);

    @POST(base)
    Call<GenericResponse<Usuario>> save(@Body Usuario u);

}
