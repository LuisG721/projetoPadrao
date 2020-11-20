package com.projeto_padrao.api.servicos;

import com.projeto_padrao.models.Tarefa;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.models.eventos.Evento;
import com.projeto_padrao.models.eventos.Favorito;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventoService {

    @GET("eventos/")
    Call<List<Evento>> listarEventos(@Header("Authorization") String key);

    @POST("favoritos/")
    Call<Favorito> adicionarFavoritos(@Header("Authorization") String key,@Body Favorito favorito);

    @DELETE("favoritos/{id}")
    Call<Favorito> deletarFavorito(@Header("Authorization") String key, @Path("id") Long id);

    @GET("favoritos/")
    Call<List<Favorito>> listarFavoritos(@Header("Authorization") String key);




}
