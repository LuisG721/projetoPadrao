package com.projeto_padrao.models.chamados;

import com.projeto_padrao.models.Usuario;

public class Impressora {
    private StatusImpressora statusImpressora;
    private Usuario usuario;


    public StatusImpressora getStatusImpressora() {
        return statusImpressora;
    }

    public void setStatusImpressora(StatusImpressora statusImpressora) {
        this.statusImpressora = statusImpressora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
