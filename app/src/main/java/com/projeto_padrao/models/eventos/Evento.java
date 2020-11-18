package com.projeto_padrao.models.eventos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;


import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.projeto_padrao.adapters.EventosAdapter;

import com.projeto_padrao.api.retrofit.RetrofitConfig;
import com.projeto_padrao.models.Tarefa;
import com.projeto_padrao.models.Usuario;


import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Evento extends SugarRecord {
    private String data;
    private String horario;
    private String local;
    private double preco;
    private String nomeEvento;
    private String descricao;
    private Context context;

    public Evento() {
    }

    public Evento(Context context) {
        this.context = context;
    }


    public void receberListaDeEventos(Usuario usuario, ListView evento_lista_listview) {
        Call<List<Evento>> call = new RetrofitConfig().setEventoService().listarEventos("Token " + usuario.getKey());
        call.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(@NotNull Call<List<Evento>> call, @NotNull Response<List<Evento>> response) {
                if (response.isSuccessful()) {
                    List<Evento> eventos = response.body();
                    Log.d("listarEventos", "listar");

                    if (eventos != null) {
                        for(Evento evento1 : eventos){
                            evento1.save();
                        }
                    }

                    EventosAdapter adaptador = new EventosAdapter(usuario.getContext(), eventos);
                    evento_lista_listview.setAdapter(adaptador);
                }

            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Log.d("listarEventos", "listar");
            }

        });
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }


    public void setHorario(Date Datahorario) {
        String horario = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Datahorario);
        this.horario = horario;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getData() {
        return data;
    }

    public void setData(Date data) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.data = sdf.format(data);
    }

    public String getHorario() {
        return horario;
    }

    public String getDescricao() {
        return descricao;

    }
}
