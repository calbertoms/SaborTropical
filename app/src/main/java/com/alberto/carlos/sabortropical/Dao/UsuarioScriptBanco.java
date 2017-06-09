package com.alberto.carlos.sabortropical.Dao;

import android.content.Context;

import com.alberto.carlos.sabortropical.BancoDeDados.SQLiteHelper;

/**
 * Created by SuporteE6530 on 09/06/2017.
 */

public class UsuarioScriptBanco extends UsuarioDao {

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS usuarios; DROP TABLE IF EXISTS pessoas;";
    //Cria a tabela com id sequencial
    private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
            "create table pessoas (id integer primary key autoincrement, "
                    + "nome text not null, sobreNome text not null, dataNascimento text not null, corPele integer not null, "
                    + "corOlhos integer not null, sexo integer not null, nomePai text not null, nomeMae text not null, "
                    + "estadoCivil integer not null, cpf text not null, identidade text not null); "
                    + "create table usuarios (id integer references pessoas(id), email text not null, senha text not null, "
                    + "dataAdmissao text not null, nivelAcesso integer not null); "
    };

    private static final String NOME_BANCO = "sabor_tropical";
    private static final int VERSAO_BANCO = 1;
    private SQLiteHelper dbHelper;

    public UsuarioScriptBanco(Context ctx) {
        // Cria utilizando um script SQL
        dbHelper = new SQLiteHelper(ctx, NOME_BANCO, VERSAO_BANCO, SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_DELETE);
        //Abre o banco no modo escrita para poder alterar tambem
        db = dbHelper.getWritableDatabase();
    }

    //Fecha o banco
    public void fechar() {
        super.fechar();
        if(dbHelper != null) {
            dbHelper.close();
        }
    }


}
