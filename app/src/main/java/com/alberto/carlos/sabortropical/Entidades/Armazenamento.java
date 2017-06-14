package com.alberto.carlos.sabortropical.Entidades;

import java.io.Serializable;

/**
 * Created by carlos.alberto on 10/06/2017.
 */

public class Armazenamento implements Serializable {

    private long id;
    private String nome;
    private int tamanhoExterno;
    private float areaUtil;
    private float peso;
    private int estadoConservacao;
    private String cor;
    private String patrocinio;
    private String dataFabricacao;
    private String dataValidade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTamanhoExterno() {
        return tamanhoExterno;
    }

    public void setTamanhoExterno(int tamanhoExterno) {
        this.tamanhoExterno = tamanhoExterno;
    }

    public float getAreaUtil() {
        return areaUtil;
    }

    public void setAreaUtil(float areaUtil) {
        this.areaUtil = areaUtil;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(int estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPatrocinio() {
        return patrocinio;
    }

    public void setPatrocinio(String patrocinio) {
        this.patrocinio = patrocinio;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(String dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    @Override
    public String toString() {
        return this.getId() + " - " + this.getNome() + " - Válido até " + this.getDataValidade();
    }

}
