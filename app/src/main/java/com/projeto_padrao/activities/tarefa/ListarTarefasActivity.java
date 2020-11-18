package com.projeto_padrao.activities.tarefa;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto_padrao.R;
import com.projeto_padrao.activities.usuario.ListarUsuariosActivity;
import com.projeto_padrao.adapters.UsuariosAdapter;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Tarefa;
import com.projeto_padrao.models.Usuario;

public class ListarTarefasActivity extends AppCompatActivity {

    ListView tarefas_lista_listview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarefa_lista);

        idenfificandoComponentes();
        inicializandoComponentes();
    }


    private void idenfificandoComponentes() {

        tarefas_lista_listview = findViewById(R.id.tarefas_lista_listview);

    }

    public void inicializandoComponentes() {


        Usuario usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(ListarTarefasActivity.this);
            Tarefa.receberListaDeTarefas(usuario,tarefas_lista_listview);
        }


    }

    @Override
    protected void onResume(){
        super.onResume();
        inicializandoComponentes();
    }

}