package com.alberto.carlos.sabortropical.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alberto.carlos.sabortropical.Entidades.Cliente;
import com.alberto.carlos.sabortropical.Entidades.Endereco;
import com.alberto.carlos.sabortropical.Entidades.Fornecedor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuporteE6530 on 08/06/2017.
 */

//classe de persistencia do cliente
public class FornecedorDao {
    //nome do banco
    private static final String CATEGORIA = "Sabor Tropical";
    //Nome da tabela
    private static final String NOME_TABELA_FORNECEDOR = "fornecedor";
    //Nome da tabela
    private static final String NOME_TABELA_ENDERECO = "endereco";

    //objeto de conexão com banco
    private SQLiteDatabase conn;

    public FornecedorDao(SQLiteDatabase conn) {

        this.conn = conn;

    }

    //metodo inserir fornecedor
    public Long inserir(Fornecedor fornecedor) {

        ContentValues valuesEndereco = new ContentValues();
        valuesEndereco.put("logradouro", fornecedor.getEndereco().getLogradouro());
        valuesEndereco.put("numero", fornecedor.getEndereco().getNumero());
        valuesEndereco.put("bairro", fornecedor.getEndereco().getBairro());
        valuesEndereco.put("cidade", fornecedor.getEndereco().getCidade());
        valuesEndereco.put("uf", fornecedor.getEndereco().getUf());
        valuesEndereco.put("pais", fornecedor.getEndereco().getPais());
        valuesEndereco.put("pontoReferencia", fornecedor.getEndereco().getPontoreferencia());
        valuesEndereco.put("cep", fornecedor.getEndereco().getCep());

        Long id_endereco = inserirEndereco(valuesEndereco);

        ContentValues valuesFornecedor = new ContentValues();
        valuesFornecedor.put("id_endereco", id_endereco);
        valuesFornecedor.put("contrato", fornecedor.getContrato());
        valuesFornecedor.put("status", fornecedor.getStatus());
        valuesFornecedor.put("responsavel", fornecedor.getNomeResponsavel());
        valuesFornecedor.put("cnpj", fornecedor.getCnpj());
        valuesFornecedor.put("inscEstadual", fornecedor.getInscEstadual());
        valuesFornecedor.put("credito", fornecedor.getCredito());
        valuesFornecedor.put("banco", fornecedor.getBanco());
        valuesFornecedor.put("agencia", fornecedor.getAgencia());
        valuesFornecedor.put("contaCorrente", fornecedor.getContaCorrente());
        valuesFornecedor.put("tipoPagamento", fornecedor.getTipoPagamento());
        valuesFornecedor.put("desempenho", fornecedor.getDesempenho());

        Long id_fornecedor = inserirFornecedor(valuesFornecedor);

        return id_fornecedor;

    }

    //metodo inseri fornecedor no banco
    private Long inserirFornecedor(ContentValues values) {
        Long id = conn.insert(NOME_TABELA_FORNECEDOR, null, values);
        return id;
    }

    //metodo inseri endereco no banco
    private Long inserirEndereco(ContentValues values) {
        Long id = conn.insert(NOME_TABELA_ENDERECO, null, values);
        return id;
    }

    //metodo atualiza Fornecedor
    public int atualizar(Fornecedor fornecedor) {

        ContentValues valuesEndereco = new ContentValues();
        valuesEndereco.put("logradouro", fornecedor.getEndereco().getLogradouro());
        valuesEndereco.put("numero", fornecedor.getEndereco().getNumero());
        valuesEndereco.put("bairro", fornecedor.getEndereco().getBairro());
        valuesEndereco.put("cidade", fornecedor.getEndereco().getCidade());
        valuesEndereco.put("uf", fornecedor.getEndereco().getUf());
        valuesEndereco.put("pais", fornecedor.getEndereco().getPais());
        valuesEndereco.put("pontoReferencia", fornecedor.getEndereco().getPontoreferencia());
        valuesEndereco.put("cep", fornecedor.getEndereco().getCep());

        String id_endereco = String.valueOf(fornecedor.getEndereco().getId());
        String where = "id=?";
        String[] whereArgs = new String[] { id_endereco };
        int countEndereco = atualizarEndereco(valuesEndereco, where, whereArgs);

        ContentValues valuesFornecedor = new ContentValues();
        valuesFornecedor.put("contrato", fornecedor.getContrato());
        valuesFornecedor.put("status", fornecedor.getStatus());
        valuesFornecedor.put("responsavel", fornecedor.getNomeResponsavel());
        valuesFornecedor.put("cnpj", fornecedor.getCnpj());
        valuesFornecedor.put("inscEstadual", fornecedor.getInscEstadual());
        valuesFornecedor.put("credito", fornecedor.getCredito());
        valuesFornecedor.put("banco", fornecedor.getBanco());
        valuesFornecedor.put("agencia", fornecedor.getAgencia());
        valuesFornecedor.put("contaCorrente", fornecedor.getContaCorrente());
        valuesFornecedor.put("tipoPagamento", fornecedor.getTipoPagamento());
        valuesFornecedor.put("desempenho", fornecedor.getDesempenho());

        String id_fornecedor = String.valueOf(fornecedor.getId());
        where = "id=?";
        whereArgs = new String[] { id_fornecedor};
        int countFornecedor = atualizarFornecedor(valuesFornecedor, where, whereArgs);


        int count = countFornecedor + countEndereco;
        return count;

    }

    //metodo atualiza fornecedor
    private int atualizarFornecedor(ContentValues values, String where, String[] whereArgs) {

        int count = conn.update(NOME_TABELA_FORNECEDOR, values, where, whereArgs);
        Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
        return count;

    }

    //metodo atualiza endereco
    private int atualizarEndereco(ContentValues values, String where, String[] whereArgs) {

        int count = conn.update(NOME_TABELA_ENDERECO, values, where, whereArgs);
        Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
        return count;

    }


    //metodo deleta fornecedor
    public void deletar(Long id_Fornecedor, Long id_Endereco) {

        String where = "id=?";
        String id_endereco = String.valueOf(id_Endereco);
        String[] whereArgs = new String[] { id_endereco };
        deletarEndereco(where, whereArgs);
        String id_fornecedor = String.valueOf(id_Fornecedor);
        where = "id=?";
        whereArgs = new String[] { id_fornecedor };
        deletarFornecedor(where, whereArgs);

    }

    //metodo deleta pessoa
    private void deletarEndereco(String where, String[] whereArgs) {

        conn.delete(NOME_TABELA_ENDERECO, where, whereArgs);
        Log.i(CATEGORIA, "Deletou registro");
    }

    //metodo deleta cliente
    private void deletarFornecedor(String where, String[] whereArgs) {

        conn.delete(NOME_TABELA_FORNECEDOR, where, whereArgs);
        Log.i(CATEGORIA, "Deletou registro");

    }

    //Retorna o cursor com todos os clientes
    public Cursor getCursor() {

        try {
            Cursor cursor =  conn.rawQuery("SELECT t1.id,t1.id_endereco,t1.contrato,t1.status,t1.responsavel,t1.cnpj,t1.inscEstadual,t1.credito,t1.banco,t1.agencia,t1.contaCorrente,t1.tipoPagamento,t1.desempenho,t2.logradouro,t2.numero,t2.bairro,t2.cidade,t2.uf,t2.pais,t2.pontoReferencia,t2.cep FROM fornecedor t1 INNER JOIN endereco t2 ON (t1.id_endereco = t2.id) ", null);
            return cursor;
        } catch (SQLException e) {
            Log.d(CATEGORIA, "Erro ao buscar os fornecedores: " + e.toString());
            return null;
        }

    }

    
    //metodo retorna uma lista de fornecedor
    public List<Fornecedor> listarFornecedor(){

        List<Fornecedor> fornecedores = new ArrayList<>();

        Cursor c = getCursor();
        if(c == null){
            return null;
        }

        while(c.moveToNext()) {
            //Recupera os indices das colunas
            Fornecedor fornecedor = new Fornecedor();
            Endereco endereco = new Endereco();
            fornecedor.setId(c.getLong(c.getColumnIndex("id")));
            fornecedor.setContrato(c.getInt(c.getColumnIndex("contrato")));
            fornecedor.setStatus(c.getInt(c.getColumnIndex("status")));
            fornecedor.setNomeResponsavel(c.getString(c.getColumnIndex("responsavel")));
            fornecedor.setCnpj(c.getString(c.getColumnIndex("cnpj")));
            fornecedor.setInscEstadual(c.getString(c.getColumnIndex("inscEstadual")));
            fornecedor.setCredito(c.getDouble(c.getColumnIndex("credito")));
            fornecedor.setBanco(c.getString(c.getColumnIndex("banco")));
            fornecedor.setAgencia(c.getString(c.getColumnIndex("agencia")));
            fornecedor.setContaCorrente(c.getString(c.getColumnIndex("contaCorrente")));
            fornecedor.setTipoPagamento(c.getString(c.getColumnIndex("tipoPagamento")));
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


            fornecedores.add(fornecedor);
        }

        return fornecedores;
    }

    //Fechar o banco
    public void fechar(){
        if(conn != null) {
            conn.close();
        }
    }


}
