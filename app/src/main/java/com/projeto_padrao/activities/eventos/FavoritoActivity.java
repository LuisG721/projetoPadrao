package com.projeto_padrao.activities.eventos;

import androidx.appcompat.app.AppCompatActivity;
import com.projeto_padrao.R;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.models.eventos.Evento;
import com.projeto_padrao.models.eventos.Favorito;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class FavoritoActivity extends AppCompatActivity {

    Usuario usuario;
    ListView favorito_lista_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorito_lista);

        /*usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(FavoritoActivity.this);
        }*/
        idenfificandoComponentes();
        inicializandoComponentes();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu2, menu);
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

            case R.id.menu_item_eventos:
                Aplicacao.irParaEventosActivity(FavoritoActivity.this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void fazerLogoff() {
        usuario.setLogado(false);
        usuario.save();
        Aplicacao.irParaLoginActivity(FavoritoActivity.this);
    }


    private void idenfificandoComponentes() {
        favorito_lista_listview = findViewById(R.id.favorito_lista_listview);
    }

    public void inicializandoComponentes() {

        Usuario usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(FavoritoActivity.this);
            Favorito favorito = new Favorito(FavoritoActivity.this);
            favorito.receberListaDeFavoritos(usuario,favorito_lista_listview);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        inicializandoComponentes();
    }
}


