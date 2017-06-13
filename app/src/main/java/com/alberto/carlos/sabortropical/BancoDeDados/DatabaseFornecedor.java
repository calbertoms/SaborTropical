package com.alberto.carlos.sabortropical.BancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SuporteE6530 on 09/06/2017.
 */

public class DatabaseFornecedor extends SQLiteOpenHelper {

    //Nome do Banco
    private static final String NOME_BANCO = "sabor_tropical";

    private static final String SCRIPT_DATABASE_CREATE_FORNECEDOR =
            "create table fornecedor (id integer primary key autoincrement, id_endereco integer references endereco(id), "
                    + "contrato integer not null, status integer not null, responsavel text not null, cnpj text not null, "
                    + "inscEstadual text not null, credito real not null, banco text not null, agencia text not null, contaCorrente text not null, tipoPagamento text not null, "
                    + "desempenho integer not null); ";

    private static final String SCRIPT_DATABASE_CREATE_ENDERECO =
            "create table endereco (id integer primary key autoincrement, "
                    + "logradouro text not null, numero integer not null, bairro text not null, cidade text not null, "
                    + "uf text not null, pais text not null, pontoReferencia text not null, cep text not null); ";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_ENDERECO = "DROP TABLE IF EXISTS endereco;";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_FORNECEDOR = "DROP TABLE IF EXISTS fornecedor;";

    private static final String FOREING_KEY = "PRAGMA foreign_keys = ON;";

    private static final int VERSAO_BANCO = 10;



    public DatabaseFornecedor(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SCRIPT_DATABASE_CREATE_ENDERECO);
        db.execSQL(SCRIPT_DATABASE_CREATE_FORNECEDOR);
        db.execSQL(FOREING_KEY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SCRIPT_DATABASE_DELETE_ENDERECO);
        db.execSQL(SCRIPT_DATABASE_DELETE_FORNECEDOR);
        onCreate(db);

    }
}
