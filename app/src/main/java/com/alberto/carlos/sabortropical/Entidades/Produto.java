package com.alberto.carlos.sabortropical.Entidades;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.ClienteDao;
import com.alberto.carlos.sabortropical.Dao.ProdutoDao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by carlos.alberto on 11/06/2017.
 */

public class Produto implements Serializable {

    private long id;
    private String nome;
    private String tipo;
    private String categoria;
    private int temperaturaArmazenagem;
    private int temperaturaTolerancia;
    private int maximoEmpilhamento;
    private Fornecedor fornecedor;
    private Armazenamento armazenamento;
    private String dataValidade;
    private String dataFabricacao;
    private float precoCompra;
    private float precoVenda;
    private Database databaseProduto;
    private SQLiteDatabase conn;

    public Produto(long id, String nome, String tipo, String categoria, int temperaturaArmazenagem, int temperaturaTolerancia, int maximoEmpilhamento, Fornecedor fornecedor, Armazenamento armazenamento, String dataValidade, String dataFabricacao, float precoCompra, float precoVenda) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.temperaturaArmazenagem = temperaturaArmazenagem;
        this.temperaturaTolerancia = temperaturaTolerancia;
        this.maximoEmpilhamento = maximoEmpilhamento;
        this.fornecedor = fornecedor;
        this.armazenamento = armazenamento;
        this.dataValidade = dataValidade;
        this.dataFabricacao = dataFabricacao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
    }

    public Produto(String nome, String tipo, String categoria, int temperaturaArmazenagem, int temperaturaTolerancia, int maximoEmpilhamento, Fornecedor fornecedor, Armazenamento armazenamento, String dataValidade, String dataFabricacao, float precoCompra, float precoVenda) {
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.temperaturaArmazenagem = temperaturaArmazenagem;
        this.temperaturaTolerancia = temperaturaTolerancia;
        this.maximoEmpilhamento = maximoEmpilhamento;
        this.fornecedor = fornecedor;
        this.armazenamento = armazenamento;
        this.dataValidade = dataValidade;
        this.dataFabricacao = dataFabricacao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
    }

    public Produto() {
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getTemperaturaArmazenagem() {
        return temperaturaArmazenagem;
    }

    public void setTemperaturaArmazenagem(int temperaturaArmazenagem) {
        this.temperaturaArmazenagem = temperaturaArmazenagem;
    }

    public int getTemperaturaTolerancia() {
        return temperaturaTolerancia;
    }

    public void setTemperaturaTolerancia(int temperaturaTolerancia) {
        this.temperaturaTolerancia = temperaturaTolerancia;
    }

    public int getMaximoEmpilhamento() {
        return maximoEmpilhamento;
    }

    public void setMaximoEmpilhamento(int maximoEmpilhamento) {
        this.maximoEmpilhamento = maximoEmpilhamento;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Armazenamento getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(Armazenamento armazenamento) {
        this.armazenamento = armazenamento;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(String dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public float getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(float precoCompra) {
        this.precoCompra = precoCompra;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    @Override
    public String toString() {
        return this.getId() + " - " + this.getNome() + " - R$ " + this.getPrecoVenda();
    }

    public long Cadastrar(Context context) throws SQLException {

        databaseProduto = new Database(context);
        conn = databaseProduto.getWritableDatabase();
        ProdutoDao dao = new ProdutoDao(conn);

        Long idProduto= dao.inserir(this);
        dao.fechar();

        return idProduto;
    }

    public List<Produto> Visualizar(Context context) throws SQLException {

        List<Produto> produtos;

        databaseProduto = new Database(context);
        conn = databaseProduto.getReadableDatabase();
        ProdutoDao dao = new ProdutoDao(conn);
        produtos = dao.listarProduto();
        dao.fechar();

        return produtos;

    }

    public int Editar(Context context) throws SQLException {

        databaseProduto = new Database(context);
        conn = databaseProduto.getWritableDatabase();
        ProdutoDao dao = new ProdutoDao(conn);

        int qtdAtualizadas = dao.atualizar(this);
        dao.fechar();

        return qtdAtualizadas;

    }

    public void Deletar(Context context,long id_produto) throws SQLException{

        databaseProduto = new Database(context);
        conn = databaseProduto.getWritableDatabase();
        ProdutoDao dao = new ProdutoDao(conn);
        dao.deletar(id_produto);
        dao.fechar();

    }

}
