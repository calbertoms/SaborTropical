package com.alberto.carlos.sabortropical.Entidades;

/**
 * Created by SuporteE6530 on 07/06/2017.
 */

public abstract class Endereco {


    private long id;
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String pais;
    private String pontoreferencia;
    private String cep;



    public Endereco (long id,String logradouro,int numero, String bairro, String cidade, String uf, String pais, String pontoreferencia, String cep){

        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.pais = pais;
        this.pontoreferencia = pontoreferencia;
        this.cep = cep;

    }

    public Endereco (String logradouro,int numero, String bairro, String cidade, String uf, String pais, String pontoreferencia, String cep){

        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.pais = pais;
        this.pontoreferencia = pontoreferencia;
        this.cep = cep;

    }

    public Endereco(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPontoreferencia() {
        return pontoreferencia;
    }

    public void setPontoreferencia(String pontoreferencia) {
        this.pontoreferencia = pontoreferencia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }


}
