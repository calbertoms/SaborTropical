package com.alberto.carlos.sabortropical.Entidades;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.PedidoDao;
import com.alberto.carlos.sabortropical.Dao.ProdutoDao;
import com.alberto.carlos.sabortropical.Telas.Produto.ProdutosCadActivity;

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
    private Item item;
    private Usuario usuario;

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

    public Pedido() {
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long Vender(Context context) throws SQLException {

        Database databasePedido;
        SQLiteDatabase conn;
        long id;

        databasePedido = new Database(context);
        conn = databasePedido.getWritableDatabase();
        PedidoDao pedidoDao = new PedidoDao(conn);
        id = pedidoDao.inserirVenda(this);

        return id;

    }

    public long Comprar(Context context) throws SQLException {

        Database databasePedido;
        SQLiteDatabase conn;
        long id;

        databasePedido = new Database(context);
        conn = databasePedido.getWritableDatabase();
        PedidoDao pedidoDao = new PedidoDao(conn);
        id = pedidoDao.inserirCompra(this);

        return id;

    }



}
