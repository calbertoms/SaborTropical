package com.alberto.carlos.sabortropical.BancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SuporteE6530 on 09/06/2017.
 */

public class Database extends SQLiteOpenHelper {

    //Nome do Banco
    private static final String NOME_BANCO = "sabor_tropical_novo";

    private static final String SCRIPT_DATABASE_CREATE_PESSOA =
            "create table pessoas (id integer primary key autoincrement, "
                    + "nome text not null, sobreNome text not null, dataNascimento text not null, corPele integer not null, "
                    + "corOlhos integer not null, sexo integer not null, nomePai text not null, nomeMae text not null, "
                    + "estadoCivil integer not null, cpf text not null, identidade text not null); ";

    private static final String SCRIPT_DATABASE_CREATE_USUARIO=
                     "create table usuarios (id integer references pessoas(id), email text not null, senha text not null, "
                    + "dataAdmissao text not null, nivelAcesso integer not null); " ;

    private static final String SCRIPT_DATABASE_CREATE_ENDERECO =
            "create table endereco (id integer primary key autoincrement, "
                    + "logradouro text not null, numero integer not null, bairro text not null, cidade text not null, "
                    + "uf text not null, pais text not null, pontoReferencia text not null, cep text not null); ";

    private static final String SCRIPT_DATABASE_CREATE_CLIENTE=
            "create table clientes (id_pessoa integer references pessoas(id), id_endereco integer references endereco(id), regiao text not null, pontos integer not null); ";

    private static final String SCRIPT_DATABASE_CREATE_FORNECEDOR =
            "create table fornecedor (id integer primary key autoincrement, id_endereco integer references endereco(id), "
                    + "contrato integer not null, nome text not null, status integer not null, responsavel text not null, cnpj text not null, "
                    + "inscEstadual text not null, credito real not null, banco text not null, agencia text not null, contaCorrente text not null, tipoPagamento int not null, "
                    + "desempenho integer not null); ";

    private static final String SCRIPT_DATABASE_CREATE_ARMAZENAMENTO =
            "create table armazenamento (id integer primary key autoincrement, "
                    + "nome text not null, tamanhoExterno integer not null, areaUtil real not null, estadoConservacao integer not null, cor text not null, "
                    + "patrocinio text not null, dataFabricacao text not null, peso real not null, dataValidade text not null); ";

    private static final String SCRIPT_DATABASE_CREATE_PRODUTO =
            "create table produto (id integer primary key autoincrement, id_fornecedor integer references fornecedor(id), id_armazenamento integer references armazenamento(id), "
                    + "nome text not null, tipo text not null, categoria text not null, temperaturaArmazenamento integer not null, temperaturaTolerancia integer not null, "
                    + "maximoEmpilhamento integer not null, dataValidade text not null, dataFabricacao text not null, precoCompra real not null, precoVenda real not null); ";

    private static final String SCRIPT_DATABASE_CREATE_ITEM =
            "create table item (id integer primary key autoincrement, id_produto integer references produto(id), "
                    + "valorUnitario real not null, quantidade integer not null); ";

    private static final String SCRIPT_DATABASE_CREATE_VENDA =
            "create table venda (id integer primary key autoincrement, id_cliente integer references cliente(id_pessoa), id_item integer references item(id),  "
                    + "dataVenda text not null, dataVencimento text not null, precoTotal real not null, desconto real not null, condPag integer not null, "
                    + "status integer not null, observacao text not null); ";

    private static final String SCRIPT_DATABASE_CREATE_COMPRA =
            "create table compra (id integer primary key autoincrement, id_usuario integer references usuario(id), id_item integer references item(id),  "
                    + "dataVenda text not null, dataVencimento text not null, precoTotal real not null, desconto real not null, condPag integer not null, "
                    + "status integer not null, observacao text not null); ";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_PESSOA = "DROP TABLE IF EXISTS pessoas;";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_USUARIO = "DROP TABLE IF EXISTS usuarios;";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_ENDERECO = "DROP TABLE IF EXISTS endereco;";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_CLIENTE = "DROP TABLE IF EXISTS clientes;";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_FORNECEDOR = "DROP TABLE IF EXISTS fornecedor;";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_ARMAZENAMENTO = "DROP TABLE IF EXISTS armazenamento;";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_PRODUTO= "DROP TABLE IF EXISTS produto;";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_ITEM = "DROP TABLE IF EXISTS item;";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_VENDA = "DROP TABLE IF EXISTS venda;";

    //Script para fazer o drop da tabela
    private static final String SCRIPT_DATABASE_DELETE_COMPRA = "DROP TABLE IF EXISTS compra;";



    private static final String FOREING_KEY = "PRAGMA foreign_keys = ON;";

    private static final int VERSAO_BANCO = 4;



    public Database(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SCRIPT_DATABASE_CREATE_PESSOA);
        db.execSQL(SCRIPT_DATABASE_CREATE_USUARIO);
        db.execSQL(SCRIPT_DATABASE_CREATE_ENDERECO);
        db.execSQL(SCRIPT_DATABASE_CREATE_CLIENTE);
        db.execSQL(SCRIPT_DATABASE_CREATE_FORNECEDOR);
        db.execSQL(SCRIPT_DATABASE_CREATE_ARMAZENAMENTO);
        db.execSQL(SCRIPT_DATABASE_CREATE_PRODUTO);
        db.execSQL(SCRIPT_DATABASE_CREATE_ITEM);
        db.execSQL(SCRIPT_DATABASE_CREATE_VENDA);
        db.execSQL(SCRIPT_DATABASE_CREATE_COMPRA);
        db.execSQL(FOREING_KEY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SCRIPT_DATABASE_DELETE_VENDA);
        db.execSQL(SCRIPT_DATABASE_DELETE_COMPRA);
        db.execSQL(SCRIPT_DATABASE_DELETE_ITEM);
        db.execSQL(SCRIPT_DATABASE_DELETE_PRODUTO);
        db.execSQL(SCRIPT_DATABASE_DELETE_ARMAZENAMENTO);
        db.execSQL(SCRIPT_DATABASE_DELETE_FORNECEDOR);
        db.execSQL(SCRIPT_DATABASE_DELETE_ENDERECO);
        db.execSQL(SCRIPT_DATABASE_DELETE_CLIENTE);
        db.execSQL(SCRIPT_DATABASE_DELETE_USUARIO);
        db.execSQL(SCRIPT_DATABASE_DELETE_PESSOA);
        onCreate(db);


    }
}
