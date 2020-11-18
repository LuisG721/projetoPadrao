package com.projeto_padrao.api.servicos;

import com.projeto_padrao.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @POST("account/register/")
    Call<Usuario> registrar(@Body Usuario usuario);

    @POST("account/login/")
    Call<Usuario> logar(@Body Usuario usuario);

    @GET("account/user/")
    Call<Usuario> requisitarObjetoUsuario(@Header("Authorization") String key);

    @GET("lista-usuarios/{id}/")
    Call<Usuario> usuarioPeloId(@Header("Authorization") String key, @Path("id") Long id);

    @PUT("lista-usuarios/{id}/")
    Call<Usuario> editarUsuario(@Header("Authorization") String key, @Path("id") Long id, @Body Usuario usuario);

    @DELETE("lista-usuarios/{id}/")
    Call<Usuario> deletarUsuario(@Header("Authorization") String key, @Path("id") Long id);

    @GET("lista-usuarios/")
    Call<List<Usuario>> listarUsuariosAdmin(@Header("Authorization") String key);

}
