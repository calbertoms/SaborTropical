package com.alberto.carlos.sabortropical.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alberto.carlos.sabortropical.Entidades.Armazenamento;
import com.alberto.carlos.sabortropical.Entidades.Endereco;
import com.alberto.carlos.sabortropical.Entidades.Fornecedor;
import com.alberto.carlos.sabortropical.Entidades.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuporteE6530 on 08/06/2017.
 */

//classe de persistencia do cliente
public class ProdutoDao {
    //nome do banco
    private static final String CATEGORIA = "Sabor Tropical";
    //Nome da tabela
    private static final String NOME_TABELA_PRODUTO = "produto";

    //objeto de conexão com banco
    private SQLiteDatabase conn;

    public ProdutoDao(SQLiteDatabase conn) {

        this.conn = conn;

    }

    //metodo inserir Produto
    public Long inserir(Produto produto) throws SQLException {

        ContentValues values = new ContentValues();
        values.put("id_fornecedor", produto.getFornecedor().getId());
        values.put("id_armazenamento", produto.getArmazenamento().getId());
        values.put("nome", produto.getNome());
        values.put("tipo", produto.getTipo());
        values.put("categoria", produto.getCategoria());
        values.put("temperaturaArmazenamento", produto.getTemperaturaArmazenagem());
        values.put("temperaturaTolerancia", produto.getTemperaturaTolerancia());
        values.put("maximoEmpilhamento", produto.getMaximoEmpilhamento());
        values.put("dataValidade", produto.getDataValidade());
        values.put("dataFabricacao", produto.getDataFabricacao());
        values.put("precoCompra", produto.getPrecoCompra());
        values.put("precoVenda", produto.getPrecoVenda());

        Long id = inserir(values);

        return id;

    }

    //metodo inseri fornecedor no banco
    private Long inserir(ContentValues values) throws SQLException {
        Long id = conn.insert(NOME_TABELA_PRODUTO, null, values);
        return id;
    }

    //metodo atualiza Fornecedor
    public int atualizar(Produto produto)  {

        ContentValues values = new ContentValues();
        values.put("id_fornecedor", produto.getFornecedor().getId());
        values.put("id_armazenamento", produto.getArmazenamento().getId());
        values.put("nome", produto.getNome());
        values.put("tipo", produto.getTipo());
        values.put("categoria", produto.getCategoria());
        values.put("temperaturaArmazenamento", produto.getTemperaturaArmazenagem());
        values.put("temperaturaTolerancia", produto.getTemperaturaTolerancia());
        values.put("maximoEmpilhamento", produto.getMaximoEmpilhamento());
        values.put("dataValidade", produto.getDataValidade());
        values.put("dataFabricacao", produto.getDataFabricacao());
        values.put("precoCompra", produto.getPrecoCompra());
        values.put("precoVenda", produto.getPrecoVenda());

        String id = String.valueOf(produto.getId());
        String where = "id=?";
        String[] whereArgs = new String[] { id };
        int count = atualizar(values, where, whereArgs);

        return count;

    }

    //metodo atualiza produto
    private int atualizar(ContentValues values, String where, String[] whereArgs) {

        int count = conn.update(NOME_TABELA_PRODUTO, values, where, whereArgs);
        Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
        return count;

    }


    //metodo deleta produto
    public void deletar(Long id_Produto) {

        String where = "id=?";
        String id = String.valueOf(id_Produto);
        String[] whereArgs = new String[] { id };
        deletar(where, whereArgs);

    }

    //metodo deleta Armazenamento
    private void deletar(String where, String[] whereArgs) {

        conn.delete(NOME_TABELA_PRODUTO, where, whereArgs);
        Log.i(CATEGORIA, "Deletou registro");
    }

    //Retorna o cursor com todos os clientes
    public Cursor getCursor() throws SQLException {

        Cursor cursor =  conn.rawQuery("SELECT t1.id as id_produto,t1.id_fornecedor as id_fornecedor_produto,t1.id_armazenamento as id_armazenamento_produto,t1.nome as nome_produto,t1.tipo,t1.categoria,t1.temperaturaArmazenamento,t1.temperaturaTolerancia,t1.maximoEmpilhamento,t1.dataValidade as dataValidadeProduto,t1.dataFabricacao as dataFabricacaoProduto,t1.precoCompra,t1.precoVenda,t2.id as id_armazenamento,t2.nome as nome_armazenamento,t2.tamanhoExterno,t2.areaUtil,t2.estadoConservacao,t2.cor,t2.patrocinio,t2.dataFabricacao as dataFabricacaoArmazenamento,t2.peso,t2.dataValidade as dataValidadeArmazenamento,t3.id as id_fornecedor,t3.id_endereco as id_endereco_fornecedor,t3.contrato,t3.nome as nome_fornecedor,t3.status,t3.responsavel,t3.cnpj,t3.inscEstadual,t3.credito,t3.banco,t3.agencia,t3.contaCorrente,t3.tipoPagamento,t3.desempenho,t4.id as id_endereco,t4.logradouro,t4.numero,t4.bairro,t4.cidade,t4.uf,t4.pais,t4.pontoReferencia,t4.cep FROM produto t1 INNER JOIN armazenamento t2 ON (t1.id_armazenamento = t2.id) INNER JOIN fornecedor t3 ON (t1.id_fornecedor = t3.id) INNER JOIN endereco t4 ON (t3.id_endereco = t4.id) ", null);
        return cursor;

    }

    
    //metodo retorna uma lista de produtos
    public List<Produto> listarProduto() throws SQLException{

        List<Produto> produtos = new ArrayList<>();

        Cursor c = getCursor();
        if(c == null){
            return null;
        }

        while(c.moveToNext()) {
            //Recupera os indices das colunas
            Produto produto = new Produto();
            Fornecedor fornecedor = new Fornecedor();
            Armazenamento armazenamento = new Armazenamento();
            Endereco endereco = new Endereco();
            produto.setId(c.getLong(c.getColumnIndex("id_produto")));
            produto.setNome(c.getString(c.getColumnIndex("nome_produto")));
            produto.setTipo(c.getString(c.getColumnIndex("tipo")));
            produto.setCategoria(c.getString(c.getColumnIndex("categoria")));
            produto.setTemperaturaArmazenagem(c.getInt(c.getColumnIndex("temperaturaArmazenamento")));
            produto.setTemperaturaTolerancia(c.getInt(c.getColumnIndex("temperaturaTolerancia")));
            produto.setMaximoEmpilhamento(c.getInt(c.getColumnIndex("maximoEmpilhamento")));
            produto.setDataValidade(c.getString(c.getColumnIndex("dataValidadeProduto")));
            produto.setDataFabricacao(c.getString(c.getColumnIndex("dataFabricacaoProduto")));
            produto.setPrecoCompra(c.getFloat(c.getColumnIndex("precoCompra")));
            produto.setPrecoVenda(c.getFloat(c.getColumnIndex("precoVenda")));
            armazenamento.setId(c.getLong(c.getColumnIndex("id_armazenamento")));
            armazenamento.setNome(c.getString(c.getColumnIndex("nome_armazenamento")));
            armazenamento.setTamanhoExterno(c.getInt(c.getColumnIndex("tamanhoExterno")));
            armazenamento.setAreaUtil(c.getFloat(c.getColumnIndex("areaUtil")));
            armazenamento.setEstadoConservacao(c.getInt(c.getColumnIndex("estadoConservacao")));
            armazenamento.setCor(c.getString(c.getColumnIndex("cor")));
            armazenamento.setPatrocinio(c.getString(c.getColumnIndex("patrocinio")));
            armazenamento.setDataFabricacao(c.getString(c.getColumnIndex("dataFabricacaoArmazenamento")));
            armazenamento.setPeso(c.getFloat(c.getColumnIndex("peso")));
            armazenamento.setDataValidade(c.getString(c.getColumnIndex("dataValidadeArmazenamento")));
            fornecedor.setId(c.getLong(c.getColumnIndex("id_fornecedor")));
            fornecedor.setContrato(c.getInt(c.getColumnIndex("contrato")));
            fornecedor.setNome(c.getString(c.getColumnIndex("nome_fornecedor")));
            fornecedor.setStatus(c.getInt(c.getColumnIndex("status")));
            fornecedor.setNomeResponsavel(c.getString(c.getColumnIndex("responsavel")));
            fornecedor.setCnpj(c.getString(c.getColumnIndex("cnpj")));
            fornecedor.setInscEstadual(c.getString(c.getColumnIndex("inscEstadual")));
            fornecedor.setCredito(c.getFloat(c.getColumnIndex("credito")));
            fornecedor.setBanco(c.getString(c.getColumnIndex("banco")));
            fornecedor.setAgencia(c.getString(c.getColumnIndex("agencia")));
            fornecedor.setContaCorrente(c.getString(c.getColumnIndex("contaCorrente")));
            fornecedor.setTipoPagamento(c.getInt(c.getColumnIndex("tipoPagamento")));
            fornecedor.setDesempenho(c.getInt(c.getColumnIndex("desempenho")));
            endereco.setId(c.getLong(c.getColumnIndex("id_endereco")));
            endereco.setLogradouro(c.getString(c.getColumnIndex("logradouro")));
            endereco.setNumero(c.getInt(c.getColumnIndex("numero")));
            endereco.setBairro(c.getString(c.getColumnIndex("bairro")));
            endereco.setCidade(c.getString(c.getColumnIndex("cidade")));
            endereco.setUf(c.getString(c.getColumnIndex("uf")));
            endereco.setPais(c.getString(c.getColumnIndex("pais")));
            endereco.setPontoreferencia(c.getString(c.getColumnIndex("pontoReferencia")));
            endereco.setCep(c.getString(c.getColumnIndex("cep")));
            fornecedor.setEndereco(endereco);
            produto.setArmazenamento(armazenamento);
            produto.setFornecedor(fornecedor);

            produtos.add(produto);
        }

        return produtos;
    }

    //Fechar o banco
    public void fechar(){
        if(conn != null) {
            conn.close();
        }
    }


}
