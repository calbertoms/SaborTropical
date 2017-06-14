package com.alberto.carlos.sabortropical.Entidades;


import java.io.Serializable;

/**
 * Created by carlos.alberto on 11/06/2017.
 */

public class Produto implements Serializable {

    private long id;
    private String nome;
    private String tipo;
    private String categoria;
    private int temperaturaArmazenagem;
    private int temperaturaTolerancia;
    private int maximoEmpilhamento;
    private Fornecedor fornecedor;
    private Armazenamento armazenamento;
    private String dataValidade;
    private String dataFabricacao;
    private float precoCompra;
    private float precoVenda;

    public Produto(long id, String nome, String tipo, String categoria, int temperaturaArmazenagem, int temperaturaTolerancia, int maximoEmpilhamento, Fornecedor fornecedor, Armazenamento armazenamento, String dataValidade, String dataFabricacao, float precoCompra, float precoVenda) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.temperaturaArmazenagem = temperaturaArmazenagem;
        this.temperaturaTolerancia = temperaturaTolerancia;
        this.maximoEmpilhamento = maximoEmpilhamento;
        this.fornecedor = fornecedor;
        this.armazenamento = armazenamento;
        this.dataValidade = dataValidade;
        this.dataFabricacao = dataFabricacao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
    }

    public Produto(String nome, String tipo, String categoria, int temperaturaArmazenagem, int temperaturaTolerancia, int maximoEmpilhamento, Fornecedor fornecedor, Armazenamento armazenamento, String dataValidade, String dataFabricacao, float precoCompra, float precoVenda) {
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.temperaturaArmazenagem = temperaturaArmazenagem;
        this.temperaturaTolerancia = temperaturaTolerancia;
        this.maximoEmpilhamento = maximoEmpilhamento;
        this.fornecedor = fornecedor;
        this.armazenamento = armazenamento;
        this.dataValidade = dataValidade;
        this.dataFabricacao = dataFabricacao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
    }

    public Produto() {
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getTemperaturaArmazenagem() {
        return temperaturaArmazenagem;
    }

    public void setTemperaturaArmazenagem(int temperaturaArmazenagem) {
        this.temperaturaArmazenagem = temperaturaArmazenagem;
    }

    public int getTemperaturaTolerancia() {
        return temperaturaTolerancia;
    }

    public void setTemperaturaTolerancia(int temperaturaTolerancia) {
        this.temperaturaTolerancia = temperaturaTolerancia;
    }

    public int getMaximoEmpilhamento() {
        return maximoEmpilhamento;
    }

    public void setMaximoEmpilhamento(int maximoEmpilhamento) {
        this.maximoEmpilhamento = maximoEmpilhamento;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Armazenamento getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(Armazenamento armazenamento) {
        this.armazenamento = armazenamento;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(String dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public float getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(float precoCompra) {
        this.precoCompra = precoCompra;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    @Override
    public String toString() {
        return this.getId() + " - " + this.getNome() + " - " + this.getArmazenamento().getNome();
    }
}
