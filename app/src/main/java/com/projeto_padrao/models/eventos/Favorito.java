package com.projeto_padrao.models.eventos;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;
import com.projeto_padrao.activities.autenticacao.RegisterActivity;
import com.projeto_padrao.activities.eventos.EventosActivity;
import com.projeto_padrao.activities.tarefa.ListarTarefasActivity;
import com.projeto_padrao.activities.usuario.ListarUsuariosActivity;
import com.projeto_padrao.activities.usuario.UsuarioDetalheActivity;
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

    public static void find(Class<Favorito> favoritoClass, Long id, Long id1) {
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

    private void confirmarFavoritoNaoDeletado() {
        Toast.makeText(this.context, "Erro ao deletar favorito", Toast.LENGTH_SHORT).show();

    }

    public void deletarFavoritoBanco(){

        this.delete();
    }

    private void confirmarFavoritoDeletado() {
        Toast.makeText(this.context, "Favorito Deletado", Toast.LENGTH_SHORT).show();
    }

    public void receberListaDeFavoritos(Usuario usuario, ListView favoritos_lista_listview) {
        Call<List<Favorito>> call = new RetrofitConfig().setEventoService().listarFavoritos("Token " + usuario.getKey());
        call.enqueue(new Callback<List<Favorito>>() {
            @Override
            public void onResponse(@NotNull Call<List<Favorito>> call, @NotNull Response<List<Favorito>> response) {
                if (response.isSuccessful()) {
                    List<Favorito> favoritos = response.body();
                    Log.d("listarFavoritos", "listar");

                    Favorito.deleteAll((Favorito.class));

                    if (favoritos != null) {
                        for(Favorito favorito1 : favoritos){
                            favorito1.save();
                        }
                    }


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

    public void deletarFavorito(String key) {
        Call<Favorito> call = new RetrofitConfig().setEventoService().deletarFavorito("Token "+key, this.getId());
        call.enqueue(new Callback<Favorito>() {

            @Override
            public void onResponse(@NonNull Call<Favorito> call, @NonNull Response<Favorito> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        deletarFavoritoBanco();

                        confirmarFavoritoDeletado();
                    }
                }else{

                    confirmarFavoritoNaoDeletado();

                }


            }

            @Override
            public void onFailure(Call<Favorito> call, Throwable t) {
                Log.e("retrofit", "Erro ao enviar o Favorito:" + t.getMessage());


            }
        });

    }



    public void atualizarFavoritos(Usuario usuario) {
        Call<List<Favorito>> call = new RetrofitConfig().setEventoService().listarFavoritos("Token " + usuario.getKey());
        call.enqueue(new Callback<List<Favorito>>() {
            @Override
            public void onResponse(@NotNull Call<List<Favorito>> call, @NotNull Response<List<Favorito>> response) {
                if (response.isSuccessful()) {
                    List<Favorito> favoritos = response.body();
                    Log.d("listarFavoritos", "listar");

                    Favorito.deleteAll((Favorito.class));

                    if (favoritos != null) {
                        for(Favorito favorito1 : favoritos){
                            favorito1.save();
                        }
                    }


                }

            }

            @Override
            public void onFailure(Call<List<Favorito>> call, Throwable t) {
                Log.d("listarFavoritos", "listar");
            }

        });
    }


}
