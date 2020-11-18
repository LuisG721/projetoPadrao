package com.projeto_padrao.activities.autenticacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.observers.ActivityObserver;

public class LoginActivity extends AppCompatActivity {

    //DECLARANDO OBJETOS
    private EditText login_editText_email,login_editText_senha;
    private Button login_button_usuario;
    private TextView login_text_registrar,login_textview_erro_email,login_textview_erro_senha,login_textview_erro_credenciais;
    private ProgressBar login_progressBar_email,login_progressBar_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        this.getLifecycle().addObserver(new ActivityObserver());

        if(Usuario.verificaUsuarioLogado()!=null){
            Aplicacao.irParaEventosActivity(LoginActivity.this);
        }else {
            identificandoComponentes();
            inicializandoComponentes();
        }
    }

    private void identificandoComponentes() {

        //-------------------IDENTIFICANDO OS COMPONENTES EM "login.xml"----------//
        login_editText_email = (EditText) findViewById(R.id.login_editText_email);
        login_editText_senha = (EditText) findViewById(R.id.login_editText_senha);
        login_button_usuario = (Button) findViewById(R.id.login_button_login);
        login_text_registrar = (TextView) findViewById(R.id.login_text_registrar);
        login_textview_erro_email = (TextView) findViewById(R.id.login_textview_erro_email);
        login_textview_erro_senha = (TextView) findViewById(R.id.login_textview_erro_senha);
        login_textview_erro_credenciais = (TextView) findViewById(R.id.login_textview_erro_credenciais);


        login_progressBar_email = (ProgressBar) findViewById(R.id.login_progressBar_email);
        login_progressBar_password = (ProgressBar) findViewById(R.id.login_progressBar_password);

    }

    private void inicializandoComponentes() {
        //----------------------------- BOTÃO DE LOGIN--------------------------------//

        esconderComponentes();
        esconderProgressBar();

        login_button_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                esconderComponentes();
                mostrarProgressBar();
                desabilitarBotao();

                String email = login_editText_email.getText().toString();
                String senha = login_editText_senha.getText().toString();

                Usuario usuarioLogado = new Usuario(email,senha,null,LoginActivity.this);
                usuarioLogado.logar();

                Log.d("autenticação","  \nUSUARIO: "+ email + "\nSENHA:"+ senha);


            }
        });

        //------------------------- BOTÃO DE TRANSIÇÃO PARA O REGISTRO--------------------------//
        login_text_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void esconderComponentes() {
        login_textview_erro_email.setVisibility(View.GONE);
        login_textview_erro_senha.setVisibility(View.GONE);
        login_textview_erro_credenciais.setVisibility(View.GONE);

    }

    public void mostrarProgressBar(){
        login_progressBar_email.setVisibility(View.VISIBLE);
        login_progressBar_password.setVisibility(View.VISIBLE);
    }

    public void esconderProgressBar(){
        login_progressBar_email.setVisibility(View.GONE);
        login_progressBar_password.setVisibility(View.GONE);

        habilirarBotao();
    }

    public void mostrarAvisoEmail(String aviso){
        this.login_textview_erro_email.setVisibility(View.VISIBLE);
        this.login_textview_erro_email.setText(aviso);
    }

    public void mostrarAvisoSenha(String aviso){
        this.login_textview_erro_senha.setVisibility(View.VISIBLE);
        this.login_textview_erro_senha.setText(aviso);
    }
    public void mostrarAvisoCredenciais(String aviso){
        this.login_textview_erro_credenciais.setVisibility(View.VISIBLE);
        this.login_textview_erro_credenciais.setText(aviso);
    }

    public void habilirarBotao(){
        this.login_button_usuario.setBackground(ContextCompat.getDrawable(this, R.drawable.round_border_button));

        this.login_button_usuario.setEnabled(true);
    }
    public void desabilitarBotao(){
        this.login_button_usuario.setBackground(ContextCompat.getDrawable(this, R.drawable.round_button_blue_light));
        this.login_button_usuario.setEnabled(false);
    }



}