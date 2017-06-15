package com.alberto.carlos.sabortropical.Entidades;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.ArmazenamentoDao;
import com.alberto.carlos.sabortropical.Dao.RelatorioDao;
import com.alberto.carlos.sabortropical.Telas.Armazenamento.ArmazenamentosCadActivity;
import com.alberto.carlos.sabortropical.Telas.Armazenamento.ArmazenamentosEditDelActivity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by carlos.alberto on 10/06/2017.
 */

public class Armazenamento implements Serializable {

    private long id;
    private String nome;
    private int tamanhoExterno;
    private float areaUtil;
    private float peso;
    private int estadoConservacao;
    private String cor;
    private String patrocinio;
    private String dataFabricacao;
    private String dataValidade;
    private Database databaseArmazenamento;
    private SQLiteDatabase conn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTamanhoExterno() {
        return tamanhoExterno;
    }

    public void setTamanhoExterno(int tamanhoExterno) {
        this.tamanhoExterno = tamanhoExterno;
    }

    public float getAreaUtil() {
        return areaUtil;
    }

    public void setAreaUtil(float areaUtil) {
        this.areaUtil = areaUtil;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(int estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPatrocinio() {
        return patrocinio;
    }

    public void setPatrocinio(String patrocinio) {
        this.patrocinio = patrocinio;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(String dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    @Override
    public String toString() {
        return this.getId() + " - " + this.getNome() + " - Válido até " + this.getDataValidade();
    }

    public long Cadastrar(Context context) throws SQLException {

        databaseArmazenamento = new Database(context);
        conn = databaseArmazenamento.getWritableDatabase();
        ArmazenamentoDao dao = new ArmazenamentoDao(conn);

        Long idArmazenamento = dao.inserir(this);
        dao.fechar();

        return idArmazenamento;
    }

    public List<Armazenamento> Visualizar(Context context) throws SQLException {

        List<Armazenamento> armazenamentos;

        databaseArmazenamento = new Database(context);
        conn = databaseArmazenamento.getReadableDatabase();
        ArmazenamentoDao dao = new ArmazenamentoDao(conn);
        armazenamentos = dao.listarArmazenamento();
        dao.fechar();

        return armazenamentos;

    }

    public int Editar(Context context) throws SQLException {

        databaseArmazenamento = new Database(context);
        conn = databaseArmazenamento.getWritableDatabase();
        ArmazenamentoDao dao = new ArmazenamentoDao(conn);

        int qtdAtualizadas = dao.atualizar(this);
        dao.fechar();

        return qtdAtualizadas;

    }

    public void Deletar(Context context,long id) throws SQLException{

        databaseArmazenamento = new Database(context);
        conn = databaseArmazenamento.getWritableDatabase();
        ArmazenamentoDao dao = new ArmazenamentoDao(conn);
        dao.deletar(id);
        dao.fechar();

    }

}
