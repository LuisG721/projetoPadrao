package com.projeto_padrao;

import com.projeto_padrao.api.retrofit.RetrofitConfig;
import com.projeto_padrao.models.Usuario;
import com.projeto_padrao.models.eventos.Evento;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RetrofitCallTest {

    private static Usuario usuarioTeste = new Usuario("binksnosakebrook2@gmail.com", "123456");

    @Test
    public void A_testeRegistro() {

        Call<Usuario> call = new RetrofitConfig().setUserService().registrar(usuarioTeste);

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<Usuario> response = call.execute();
            Usuario usuario = response.body();

            if (response.isSuccessful()) {
                assertNotNull(usuario);
                usuarioTeste.setId(usuario.getId());
            } else {
                fail();
            }


        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void B_login_Success() {

        Call<Usuario> call = new RetrofitConfig().setUserService().logar(usuarioTeste);

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<Usuario> response = call.execute();
            Usuario usuario = response.body();

            if (response.isSuccessful()) {
                assertNotNull(usuario);
                usuarioTeste.setKey(usuario.getKey());
            } else {
                fail();
            }


        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }

    }

    @Test
    public void C_verificarUsuarioLogadoTest() {

        Call<Usuario> call = new RetrofitConfig().setUserService().requisitarObjetoUsuario("Token " + usuarioTeste.getKey());

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<Usuario> response = call.execute();
            Usuario usuario = response.body();

            if (response.isSuccessful()) {
                assertNotNull(usuario);
            } else {
                fail();
            }


        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }

    }

    @Test
    public void D_listarUsuariosTest() {

        Call<List<Usuario>> call = new RetrofitConfig().setUserService().listarUsuariosAdmin("Token " + usuarioTeste.getKey());

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<List<Usuario>> response = call.execute();
            List<Usuario> usuarios = response.body();

            if (response.isSuccessful()) {
                assertNotNull(usuarios);
            } else {
                fail();
            }


        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }

    }

    @Test
    public void E_listarEventosTest() {

        B_login_Success();


        Call<List<Evento>> call = new RetrofitConfig().setEventoService().listarEventos("Token " + usuarioTeste.getKey());

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<List<Evento>> response = call.execute();
            List<Evento> eventos = response.body();

            if (response.isSuccessful()) {
                assertNotNull(eventos);
            } else {
                fail();
            }


        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }

    }

}