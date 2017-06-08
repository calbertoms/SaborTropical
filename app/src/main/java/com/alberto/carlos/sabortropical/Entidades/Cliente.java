package com.alberto.carlos.sabortropical.Entidades;

/**
 * Created by SuporteE6530 on 07/06/2017.
 */

class Cliente extends Pessoa {

    private String regiao;
    private int pontos;

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

}
