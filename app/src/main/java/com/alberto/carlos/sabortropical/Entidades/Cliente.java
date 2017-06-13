package com.alberto.carlos.sabortropical.Entidades;

import java.io.Serializable;

/**
 * Created by SuporteE6530 on 07/06/2017.
 */

public class Cliente extends Pessoa implements Serializable {

    private String regiao;
    private int pontos;
    private Endereco endereco;

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return this.getId() + " - " + this.getNome() + " - " + this.getSobreNome() + " - " + this.getRegiao() + " - " + this.getPontos();
    }
}
