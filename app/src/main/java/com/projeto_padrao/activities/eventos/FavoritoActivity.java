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
        usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(FavoritoActivity.this);
        }
        idenfificandoComponentes();
        inicializandoComponentes();
    }

    private void idenfificandoComponentes() {
        favorito_lista_listview = findViewById(R.id.favorito_lista_listview);
    }

    public void inicializandoComponentes() {

        Usuario usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(FavoritoActivity.this);
            Favorito favorito = new Favorito(FavoritoActivity.this);
            favorito.listarEventosFavoritados(favorito_lista_listview);
        }
    }
}


