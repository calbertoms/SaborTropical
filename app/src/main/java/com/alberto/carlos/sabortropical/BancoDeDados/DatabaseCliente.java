package com.alberto.carlos.sabortropical.BancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SuporteE6530 on 09/06/2017.
 */

public class DatabaseCliente extends SQLiteOpenHelper {

    //Nome do Banco
    private static final String NOME_BANCO = "sabor_tropical";

    private static final String SCRIPT_DATABASE_CREATE_PESSOA =
            "create table pessoas (id integer primary key autoincrement, "
                    + "nome text not null, sobreNome text not null, dataNascimento text not null, corPele integer not null, "
                    + "corOlhos integer not null, sexo integer not null, nomePai text not null, nomeMae text not null, "
                    + "estadoCivil integer not null, cpf text not null, identidade text not null); ";

    private static final String SCRIPT_DATABASE_CREATE_ENDERECO =
            "create table endereco (id integer primary key autoincrement, "
                    + "logradouro text not null, numero integer not null, bairro text not null, cidade text not null, "
                    + "uf text not null, pais text not null, pontoReferencia text not null, cep text not null); ";

    private static final String SCRIPT_DATABASE_CREATE_CLIENTE=
                     "create table clientes (id_pessoa integer references pessoas(id), id_endereco integer references endereco(id), regiao text not null, pontos integer not null); ";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_PESSOA = "DROP TABLE IF EXISTS pessoas;";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_ENDERECO = "DROP TABLE IF EXISTS endereco;";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_CLIENTE = "DROP TABLE IF EXISTS clientes;";

    private static final String FOREING_KEY = "PRAGMA foreign_keys = ON;";

    private static final int VERSAO_BANCO = 18;



    public DatabaseCliente(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SCRIPT_DATABASE_CREATE_PESSOA);
        db.execSQL(SCRIPT_DATABASE_CREATE_ENDERECO);
        db.execSQL(SCRIPT_DATABASE_CREATE_CLIENTE);
        db.execSQL(FOREING_KEY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SCRIPT_DATABASE_DELETE_ENDERECO);
        db.execSQL(SCRIPT_DATABASE_DELETE_CLIENTE);
        db.execSQL(SCRIPT_DATABASE_DELETE_PESSOA);
        onCreate(db);

    }
}
