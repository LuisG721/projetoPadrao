package com.projeto_padrao.models;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;
import com.projeto_padrao.activities.tarefa.ListarTarefasActivity;
import com.projeto_padrao.adapters.TarefasAdapter;
import com.projeto_padrao.api.retrofit.RetrofitConfig;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tarefa extends SugarRecord {
    private boolean realizada;
    private String descricao;
    private Context context;


    public Tarefa() {
    }

    public Tarefa(Context context) {
        this.context = context;
    }

    public static void receberListaDeTarefas(Usuario usuario, ListView tarefas_lista_listview){
        Call<List<Tarefa>> call = new RetrofitConfig().setTarefaService().listarTarefas("Token "+ usuario.getKey());
        call.enqueue(new Callback<List<Tarefa>>() {
            @Override
            public void onResponse(@NotNull Call<List<Tarefa>> call, @NotNull Response<List<Tarefa>> response) {
                if(response.isSuccessful()){
                    List<Tarefa> tarefas = response.body();
                    Log.d("listarUsuarios","listar");


                    if (tarefas != null) {
                        for(Tarefa tarefa : tarefas){ tarefa.save();
                        }
                    }

                    TarefasAdapter adaptador = new TarefasAdapter(usuario.getContext(), tarefas);
                    tarefas_lista_listview.setAdapter(adaptador);

                }
            }

            @Override
            public void onFailure(Call<List<Tarefa>> call, Throwable t) {
                Log.d("listarUsuarios","listar");

            }
        });
    }

    public void deletarTarefa(String key) {
        Call<Tarefa> call = new RetrofitConfig().setTarefaService().deletarTarefa("Token "+key,this.getId());
        call.enqueue(new Callback<Tarefa>() {

            @Override
            public void onResponse(@NonNull Call<Tarefa> call, @NonNull Response<Tarefa> response) {
                if (response.isSuccessful()) {
                    Log.e("resposta_tarefa", "sucesso:" +response.body());

                    //TODO falta buscara pelo id e deletar no banco interno

                    ((ListarTarefasActivity)context).inicializandoComponentes();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Tarefa> call, @NonNull Throwable t) {
                Log.e("resposta_tarefa", "Erro ao enviar a tarefa:" + t.getMessage());

            }
        });

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
