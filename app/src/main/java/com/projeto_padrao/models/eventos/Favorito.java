package com.projeto_padrao.models.eventos;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;
import com.projeto_padrao.activities.autenticacao.RegisterActivity;
import com.projeto_padrao.activities.eventos.EventosActivity;
import com.projeto_padrao.activities.usuario.ListarUsuariosActivity;
import com.projeto_padrao.adapters.EventosAdapter;
import com.projeto_padrao.adapters.FavoritosAdapter;
import com.projeto_padrao.api.retrofit.RetrofitConfig;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Favorito extends SugarRecord {


    private long usuario;
    private long evento;
    private  Context context;



    public Favorito() { }

    public Favorito(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }

    public long getEvento() {
        return evento;
    }

    public void setEvento(long evento) {
        this.evento = evento;
    }

    public void adicionarFavorito(String key, Context context) {
        Call<Favorito> call = new RetrofitConfig().setEventoService().adicionarFavoritos("Token "+key, this);
        call.enqueue(new Callback<Favorito>() {
            @Override
            public void onResponse(@NonNull Call<Favorito> call, @NonNull Response<Favorito> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                    }
                } else {
            }
        }
            @Override
            public void onFailure(Call<Favorito> call, Throwable t) {
                Log.d("erro_no_favorito", t.toString());
            }
            });
    }


   /* public void deletarUsuario() {
        Call<Usuario> call = new RetrofitConfig().setUserService().deletarUsuario("Token "+this.getKey(),this.getId());
        call.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                if (response.isSuccessful()) {
                    confirmarUsuarioDeletado();
                    if(Usuario.verificaUsuarioLogado()!=null){
                        ((ListarUsuariosActivity)context).inicializandoComponentes();
                    }else {
                        deletarUsuarioBanco();
                        Aplicacao.irParaListarLoginActivity(context);
                    }

                }else {
                    confirmarUsuarioNaoDeletado();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Log.e("retrofit", "Erro ao enviar o usuario:" + t.getMessage());

            }
        });

    }*/


    public void receberListaDeFavoritos(Usuario usuario, ListView favoritos_lista_listview) {
        Call<List<Favorito>> call = new RetrofitConfig().setEventoService().listarFavoritos("Token " + usuario.getKey());
        call.enqueue(new Callback<List<Favorito>>() {
            @Override
            public void onResponse(@NotNull Call<List<Favorito>> call, @NotNull Response<List<Favorito>> response) {
                if (response.isSuccessful()) {
                    List<Favorito> favoritos = response.body();
                    Log.d("listarFavoritos", "listar");

                    if (favoritos != null) {
                        for(Favorito favorito1 : favoritos){
                            favorito1.save();
                        }
                    }

                  /*   List<Evento> eventos = new ArrayList<Evento>();

                   for (Favorito favorito1 : favoritos){


                        favorito1.getId(evento);
                    };*/

                    FavoritosAdapter adaptador = new FavoritosAdapter(usuario.getContext(), favoritos);
                    favoritos_lista_listview.setAdapter(adaptador);
                }

            }

            @Override
            public void onFailure(Call<List<Favorito>> call, Throwable t) {
                Log.d("listarFavoritos", "listar");
            }

        });
    }

}
