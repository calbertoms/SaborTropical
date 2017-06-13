package com.alberto.carlos.sabortropical.Entidades;


/**
 * Created by carlos.alberto on 11/06/2017.
 */

public class Produto {

    private long id;
    private String nome;
    private String tipo;
    private String categoria;
    private long temperaturaArmazenagem;
    private long temperaturaTolerancia;
    private long maximoEmpilhamento;
    private String fornecedor;
    private String validade;
    private String dataFabricacao;
    private long precoCompra;
    private long precoVenda;

    public Produto(long id, String nome, String tipo, String categoria, long temperaturaArmazenagem, long temperaturaTolerancia, long maximoEmpilhamento, String fornecedor, String validade, String dataFabricacao, long precoCompra, long precoVenda){

        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.temperaturaArmazenagem = temperaturaArmazenagem;
        this.temperaturaTolerancia = temperaturaTolerancia;
        this.maximoEmpilhamento = maximoEmpilhamento;
        this.fornecedor = fornecedor;
        this.validade = validade;
        this.dataFabricacao = dataFabricacao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;

    }


    public Produto(String nome, String tipo, String categoria, long temperaturaArmazenagem, long temperaturaTolerancia, long maximoEmpilhamento, String fornecedor, String validade, String dataFabricacao, long precoCompra, long precoVenda){

        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.temperaturaArmazenagem = temperaturaArmazenagem;
        this.temperaturaTolerancia = temperaturaTolerancia;
        this.maximoEmpilhamento = maximoEmpilhamento;
        this.fornecedor = fornecedor;
        this.validade = validade;
        this.dataFabricacao = dataFabricacao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;

    }

    public Produto(){

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

    public long getTemperaturaArmazenagem() {
        return temperaturaArmazenagem;
    }

    public void setTemperaturaArmazenagem(long temperaturaArmazenagem) {
        this.temperaturaArmazenagem = temperaturaArmazenagem;
    }

    public long gettemperaturaTolerancia() {
        return temperaturaTolerancia;
    }

    public void settemperaturaTolerancia(long temperaturaTolerancia) {
        this.temperaturaTolerancia = temperaturaTolerancia;
    }

    public long getMaximoEmpilhamento() {
        return maximoEmpilhamento;
    }

    public void setMaximoEmpilhamento(long maximoEmpilhamento) {
        this.maximoEmpilhamento = maximoEmpilhamento;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(String dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public long getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(long precoCompra) {
        this.precoCompra = precoCompra;
    }

    public long getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(long precoVenda) {
        this.precoVenda = precoVenda;
    }
}
