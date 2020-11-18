package com.projeto_padrao.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.projeto_padrao.R;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;

public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        ImageView img=(ImageView)findViewById(R.id.img);

        Glide.with(StartActivity.this).load(R.drawable.loading_processmaker).asGif().into(img);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(Usuario.verificaUsuarioLogado()!=null){
                    Aplicacao.irParaEventosActivity(StartActivity.this);
                }else {
                    Aplicacao.irParaLoginActivity(StartActivity.this);
                }
                finish();
            }
        }, 1500);
/*


 */

    }

    public static String mostrarNotificacao(String teste) {

        teste = teste + "123456";
        return teste;
    }

}