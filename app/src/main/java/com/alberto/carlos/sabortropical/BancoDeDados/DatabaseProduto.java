package com.alberto.carlos.sabortropical.BancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SuporteE6530 on 09/06/2017.
 */

public class DatabaseProduto extends SQLiteOpenHelper {

    //Nome do Banco
    private static final String NOME_BANCO = "sabor_tropical";

    private static final String SCRIPT_DATABASE_CREATE_PRODUTO =
            "create table produto (id integer primary key autoincrement, id_fornecedor integer references fornecedor(id), id_armazenamento integer references armazenamento(id), "
                    + "nome text not null, tipo text not null, categoria text not null, temperaturaArmazenamento integer not null, temperaturaTolerancia integer not null, "
                    + "maximoEmpilhamento integer not null, dataValidade text not null, dataFabricacao text not null, precoCompra real not null, precoVenda real not null); ";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_PRODUTO= "DROP TABLE IF EXISTS produto;";

    private static final String FOREING_KEY = "PRAGMA foreign_keys = ON;";

    private static final int VERSAO_BANCO = 18;



    public DatabaseProduto(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SCRIPT_DATABASE_CREATE_PRODUTO);
        db.execSQL(FOREING_KEY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SCRIPT_DATABASE_DELETE_PRODUTO);
        onCreate(db);

    }
}
