package com.alberto.carlos.sabortropical.Entidades;

/**
 * Created by carlos.alberto on 11/06/2017.
 */

public class Pedido {

    private long id;
    private String data;
    private String vencimento;
    private float precoTotal;
    private int condPag;
    private float desconto;
    private String observacao;
    private int status;
    private Cliente cliente;

    public Pedido(long id, String data, String vencimento, float precoTotal, int condPag, float desconto, String observacao, int status, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.vencimento = vencimento;
        this.precoTotal = precoTotal;
        this.condPag = condPag;
        this.desconto = desconto;
        this.observacao = observacao;
        this.status = status;
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(float precoTotal) {
        this.precoTotal = precoTotal;
    }

    public int getCondPag() {
        return condPag;
    }

    public void setCondPag(int condPag) {
        this.condPag = condPag;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
