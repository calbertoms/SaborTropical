package com.alberto.carlos.sabortropical.Entidades;

/**
 * Created by carlos.alberto on 10/06/2017.
 */

public class Armazenamento {

    private long id;
    private int tamanho;
    private long areaUtil;
    private long peso;
    private int estadoConservacao;
    private int cor;
    private String patrocinio;
    private String dataFabricacao;
    private String validade;
    private String conservante;


    public Armazenamento( long id,int tamanho, long areaUtil,long peso, int estadoConservacao, int cor, String patrocinio, String dataFabricacao, String validade, String conservante) {

        this.id = id;
        this.tamanho = tamanho;
        this.areaUtil = areaUtil;
        this.peso = peso;
        this.estadoConservacao = estadoConservacao;
        this.cor = cor;
        this.patrocinio = patrocinio;
        this.dataFabricacao = dataFabricacao;
        this.validade = validade;
        this.conservante = conservante;
    }

    public Armazenamento(int tamanho, long areaUtil,long peso, int estadoConservacao, int cor, String patrocinio, String dataFabricacao, String validade, String conservante) {

        this.tamanho = tamanho;
        this.areaUtil = areaUtil;
        this.peso = peso;
        this.estadoConservacao = estadoConservacao;
        this.cor = cor;
        this.patrocinio = patrocinio;
        this.dataFabricacao = dataFabricacao;
        this.validade = validade;
        this.conservante = conservante;
    }

    public Armazenamento (){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public long getAreaUtil() {
        return areaUtil;
    }

    public void setAreaUtil(long areaUtil) {
        this.areaUtil = areaUtil;
    }

    public long getPeso() {
        return peso;
    }

    public void setPeso(long peso) {
        this.peso = peso;
    }

    public int getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(int estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
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

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getConservante() {
        return conservante;
    }

    public void setConservante(String conservante) {
        this.conservante = conservante;
    }
}
