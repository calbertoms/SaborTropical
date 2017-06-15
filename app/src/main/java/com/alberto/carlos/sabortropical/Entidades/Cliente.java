package com.alberto.carlos.sabortropical.Entidades;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.ArmazenamentoDao;
import com.alberto.carlos.sabortropical.Dao.ClienteDao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SuporteE6530 on 07/06/2017.
 */

public class Cliente extends Pessoa implements Serializable {

    private String regiao;
    private int pontos;
    private Endereco endereco;
    private Database databaseCliente;
    private SQLiteDatabase conn;

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return this.getId() + " - " + this.getNome() + " - " + this.getSobreNome() + " - " + this.getRegiao() + " - " + this.getPontos();
    }

    public long Cadastrar(Context context) throws SQLException {

        databaseCliente = new Database(context);
        conn = databaseCliente.getWritableDatabase();
        ClienteDao dao = new ClienteDao(conn);

        Long idCliente= dao.inserir(this);
        dao.fechar();

        return idCliente;
    }

    public List<Cliente> Visualizar(Context context) throws SQLException {

        List<Cliente> clientes;

        databaseCliente = new Database(context);
        conn = databaseCliente.getReadableDatabase();
        ClienteDao dao = new ClienteDao(conn);
        clientes = dao.listarClientes();
        dao.fechar();

        return clientes;

    }

    public int Editar(Context context) throws SQLException {

        databaseCliente = new Database(context);
        conn = databaseCliente.getWritableDatabase();
        ClienteDao dao = new ClienteDao(conn);

        int qtdAtualizadas = dao.atualizar(this);
        dao.fechar();

        return qtdAtualizadas;

    }

    public void Deletar(Context context,long id_pessoa,long id_endereco) throws SQLException{

        databaseCliente = new Database(context);
        conn = databaseCliente.getWritableDatabase();
        ClienteDao dao = new ClienteDao(conn);
        dao.deletar(id_pessoa,id_endereco);
        dao.fechar();

    }

}
