package com.projeto_padrao.api.servicos;

import com.projeto_padrao.models.Tarefa;
import com.projeto_padrao.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface TarefaService {

    @GET("tarefas/")
    Call<List<Tarefa>> listarTarefas(@Header("Authorization") String key);

    @DELETE("tarefas/{id}/")
    Call<Tarefa> deletarTarefa(@Header("Authorization") String key, @Path("id") Long id);
}
