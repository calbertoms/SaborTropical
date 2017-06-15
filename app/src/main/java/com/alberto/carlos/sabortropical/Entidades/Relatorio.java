package com.alberto.carlos.sabortropical.Entidades;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.PedidoDao;
import com.alberto.carlos.sabortropical.Dao.RelatorioDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuporteE6530 on 15/06/2017.
 */

public class Relatorio implements Serializable {

    private String dataInicio;
    private String dataFim;
    private int tipo;

    public Relatorio(String dataInicio, String dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Relatorio() {
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public List<Pedido> gerar(Context context) throws SQLException {

        Database databaseRelatorio;
        SQLiteDatabase conn;
        List<Pedido> pedidos;

        databaseRelatorio = new Database(context);
        conn = databaseRelatorio.getReadableDatabase();
        RelatorioDao relatorioDao = new RelatorioDao(conn);
        pedidos = relatorioDao.listarPedidos(this);

        return pedidos;

    }

}
