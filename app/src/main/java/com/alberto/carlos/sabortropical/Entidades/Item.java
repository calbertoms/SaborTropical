package com.alberto.carlos.sabortropical.Entidades;

/**
 * Created by carlos.alberto on 11/06/2017.
 */

public class Item {

    private long id;
    private String data;
    private long valorTotal;

    public Item (long id, String data, long valorTotal){

        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;

    }

    public Item (String data, long valorTotal){

        this.data = data;
        this.valorTotal = valorTotal;

    }

    public Item(){

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

    public long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(long valorTotal) {
        this.valorTotal = valorTotal;
    }
}
