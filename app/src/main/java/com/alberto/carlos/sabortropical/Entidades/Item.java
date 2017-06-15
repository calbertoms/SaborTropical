package com.alberto.carlos.sabortropical.Entidades;

/**
 * Created by carlos.alberto on 11/06/2017.
 */

public class Item {

    private int quantidade;
    private float valorUnitario;
    private Produto produto;

    public Item(int quantidade, float valorUnitario, Produto produto) {
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
