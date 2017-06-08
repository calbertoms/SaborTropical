package com.alberto.carlos.sabortropical.Entidades;

/**
 * Created by SuporteE6530 on 08/06/2017.
 */

public class Usuario extends Pessoa {

    private String email;
    private String senha;
    private String dataAdmissao;
    private int nivel;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

}
