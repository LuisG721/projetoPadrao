package com.projeto_padrao.activities.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.projeto_padrao.R;
import com.projeto_padrao.activities.AppActivity;
import com.projeto_padrao.activities.tarefa.ListarTarefasActivity;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Tarefa;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.models.eventos.Evento;
import com.projeto_padrao.models.eventos.Favorito;

public class EventosActivity extends AppCompatActivity {


    Usuario usuario;
    ListView evento_lista_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos_lista);

        usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(EventosActivity.this);
        }

        idenfificandoComponentes();
        inicializandoComponentes();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_logoff:
                fazerLogoff();
                return true;
            case R.id.menu_item_deletar_conta:
                usuario.deletarUsuario();
                return true;

            case R.id.menu_item_favorito:
                Aplicacao.irParaFavoritoActivity(EventosActivity.this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fazerLogoff() {
        usuario.setLogado(false);
        usuario.save();
        Aplicacao.irParaLoginActivity(EventosActivity.this);
    }


    private void idenfificandoComponentes() {

        evento_lista_listview = findViewById(R.id.evento_lista_listview);

    }

    public void inicializandoComponentes() {


        Usuario usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(EventosActivity.this);

            Favorito favorito = new Favorito(EventosActivity.this);
            favorito.receberListaDeFavoritos(usuario,evento_lista_listview);
            Evento evento = new Evento(EventosActivity.this);
            evento.receberListaDeEventos(usuario,evento_lista_listview);

        }



    }



    @Override
    public void onBackPressed() {
        Aplicacao.fecharApp(EventosActivity.this);

        super.onBackPressed();
    }

}


