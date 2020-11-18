package com.projeto_padrao.activities.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.projeto_padrao.R;
import com.projeto_padrao.activities.tarefa.ListarTarefasActivity;
import com.projeto_padrao.adapters.UsuariosAdapter;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;

import java.util.List;

public class ListarUsuariosActivity extends AppCompatActivity {

    private ListView usuarios_lista_listview;
    private UsuariosAdapter adaptador = null;
    private ImageView usuarios_lista_imageview_add;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuarios_lista);

        idenfificandoComponentes();
        inicializandoComponentes();
    }


    private void idenfificandoComponentes() {

        usuarios_lista_listview = (ListView) findViewById(R.id.usuarios_lista_listview);
        usuarios_lista_imageview_add = (ImageView) findViewById(R.id.usuarios_lista_imageview_add);

        List<Usuario> usuarios = Usuario.listAll(Usuario.class);
        UsuariosAdapter adaptador = new UsuariosAdapter(ListarUsuariosActivity.this, usuarios);
        usuarios_lista_listview.setAdapter(adaptador);

        usuarios_lista_imageview_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aplicacao.irParaRegisterActivity(ListarUsuariosActivity.this);
            }
        });
    }

    public void inicializandoComponentes() {


        Usuario usuario = Usuario.verificaUsuarioLogado();
        usuario.setContext(ListarUsuariosActivity.this);
        Usuario.listarUsuariosRemoto(usuario,usuarios_lista_listview);

    }

    @Override
    protected void onResume(){
        super.onResume();
        inicializandoComponentes();
    }

}