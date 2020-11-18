package com.projeto_padrao.activities.autenticacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Usuario;

import static com.projeto_padrao.statics.ConstantesGlobais.SENHAS_DISTINTAS;

public class RegisterActivity extends AppCompatActivity {

    private EditText register_editText_usuario,register_editText_senha,register_editText_email,register_editText_senha_repita;
    private Button register_button_usuario;
    private TextView register_text_registrar,register_textView_aviso_email,register_textView_aviso_senha,register_textView_aviso_senha_repetida;
    private ProgressBar register_progressBar_nome,register_progressBar_email,register_progressBar_senha,register_progressBar_senha_repita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        identificandoComponentes();
        inicializandoComponentes();

    }

    private void identificandoComponentes() {

        //-------------------IDENTIFICANDO OS COMPONENTES EM "login.xml"----------//
        this.register_editText_usuario = (EditText) findViewById(R.id.register_editText_usuario);
        this.register_editText_email = (EditText) findViewById(R.id.register_editText_email);

        this.register_editText_senha = (EditText) findViewById(R.id.register_editText_senha);
        this.register_editText_senha_repita = (EditText) findViewById(R.id.register_editText_senha_repita);

        this.register_button_usuario = (Button) findViewById(R.id.register_button_login);
        this.register_text_registrar = (TextView) findViewById(R.id.register_text_logar);

        this.register_textView_aviso_email = (TextView) findViewById(R.id.register_textView_aviso_email);
        this.register_textView_aviso_senha = (TextView) findViewById(R.id.register_textView_aviso_senha);
        this.register_textView_aviso_senha_repetida = (TextView) findViewById(R.id.register_textView_aviso_senha_repetida);

        this.register_progressBar_nome = (ProgressBar) findViewById(R.id.register_progressBar_nome);
        this.register_progressBar_email = (ProgressBar) findViewById(R.id.register_progressBar_email);
        this.register_progressBar_senha = (ProgressBar) findViewById(R.id.register_progressBar_senha);
        this.register_progressBar_senha_repita = (ProgressBar) findViewById(R.id.register_progressBar_senha_repita);

        //----------------------------------------------------------------------------------//
    }

    private void esconderAvisos() {
        register_textView_aviso_email.setVisibility(View.GONE);
        register_textView_aviso_senha.setVisibility(View.GONE);
        register_textView_aviso_senha_repetida.setVisibility(View.GONE);
    }

    private void inicializandoComponentes() {
        esconderAvisos();
        esconderProgressBar();
        //----------------------------- BOTÃO DE REGISTRO--------------------------------//

        register_button_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                esconderAvisos();

                if(verfificaSenhas()){

                    mostrarProgressBar();
                    Usuario usuario = criarObjetoUsuarioPelosCampos();
                    usuario.registrar();
                }

            }
        });


        //----------------------------- BOTÃO DE TRANSIÇÃO PARA O REGISTRO--------------------------------//
        register_text_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private Usuario criarObjetoUsuarioPelosCampos() {
        String email = register_editText_email.getText().toString();
        String senha = register_editText_senha.getText().toString();
        String nome = register_editText_usuario.getText().toString();
        Usuario usuario = new Usuario(email,senha,nome,RegisterActivity.this);
        return usuario;
    }

    public void mostrarAvisoEmail(String aviso){
        this.register_textView_aviso_email.setVisibility(View.VISIBLE);
        this.register_textView_aviso_email.setText(aviso);
    }

    public void mostrarAvisoSenha(String aviso){
        this.register_textView_aviso_senha.setVisibility(View.VISIBLE);
        this.register_textView_aviso_senha.setText(aviso);
    }

    public boolean verfificaSenhas(){
        String senha = this.register_editText_senha.getText().toString();
        String senha_repetida = this.register_editText_senha_repita.getText().toString();

        if(!senha.equals(senha_repetida)){
            this.register_textView_aviso_senha_repetida.setVisibility(View.VISIBLE);
            this.register_textView_aviso_senha_repetida.setText(SENHAS_DISTINTAS);
            return false;
        }
        this.register_textView_aviso_senha_repetida.setVisibility(View.GONE);
        return true;

    }

    public void mostrarProgressBar(){
        register_progressBar_nome.setVisibility(View.VISIBLE);
        register_progressBar_email.setVisibility(View.VISIBLE);
        register_progressBar_senha.setVisibility(View.VISIBLE);
        register_progressBar_senha_repita.setVisibility(View.VISIBLE);

        desabilitarBotao();
    }

    public void esconderProgressBar(){
        register_progressBar_nome.setVisibility(View.GONE);
        register_progressBar_email.setVisibility(View.GONE);
        register_progressBar_senha.setVisibility(View.GONE);
        register_progressBar_senha_repita.setVisibility(View.GONE);

        habilirarBotao();
    }

    public void habilirarBotao(){
        this.register_button_usuario.setBackground(ContextCompat.getDrawable(this, R.drawable.round_border_button));

        this.register_button_usuario.setEnabled(true);
    }
    public void desabilitarBotao(){
        this.register_button_usuario.setBackground(ContextCompat.getDrawable(this, R.drawable.round_button_blue_light));
        this.register_button_usuario.setEnabled(false);
    }
}