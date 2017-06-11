package com.alberto.carlos.sabortropical.Entidades;

/**
 * Created by carlos.alberto on 11/06/2017.
 */

public class Pedido {

    private long id;
    private long precoUnitario;
    private int quantidade;
    private long precoTotal;

    public Pedido(long id, long precoUnitario, int quantidade, long precoTotal){

        this.id = id;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;


    }

    public Pedido( long precoUnitario, int quantidade, long precoTotal){

        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;


    }

    public Pedido(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(long precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public long getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(long precoTotal) {
        this.precoTotal = precoTotal;
    }
}
