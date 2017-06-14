package com.alberto.carlos.sabortropical.BancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SuporteE6530 on 09/06/2017.
 */

public class DatabaseArmazenamento extends SQLiteOpenHelper {

    //Nome do Banco
    private static final String NOME_BANCO = "sabor_tropical";

    private static final String SCRIPT_DATABASE_CREATE_ARMAZENAMENTO =
            "create table armazenamento (id integer primary key autoincrement, "
                    + "nome text not null, tamanhoExterno integer not null, areaUtil real not null, estadoConservacao integer not null, cor text not null, "
                    + "patrocinio text not null, dataFabricacao text not null, peso real not null, dataValidade text not null); ";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_ARMAZENAMENTO = "DROP TABLE IF EXISTS armazenamento;";

    private static final int VERSAO_BANCO = 18;



    public DatabaseArmazenamento(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SCRIPT_DATABASE_CREATE_ARMAZENAMENTO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SCRIPT_DATABASE_DELETE_ARMAZENAMENTO);
        onCreate(db);

    }
}
