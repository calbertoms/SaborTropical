package com.alberto.carlos.sabortropical.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alberto.carlos.sabortropical.Entidades.Armazenamento;
import com.alberto.carlos.sabortropical.Entidades.Endereco;
import com.alberto.carlos.sabortropical.Entidades.Fornecedor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuporteE6530 on 08/06/2017.
 */

//classe de persistencia do cliente
public class ArmazenamentoDao {
    //nome do banco
    private static final String CATEGORIA = "Sabor Tropical";
    //Nome da tabela
    private static final String NOME_TABELA_ARMAZENAMENTO = "armazenamento";

    //objeto de conexão com banco
    private SQLiteDatabase conn;

    public ArmazenamentoDao(SQLiteDatabase conn) {

        this.conn = conn;

    }

    //metodo inserir fornecedor
    public Long inserir(Armazenamento armazenamento) throws SQLException {

        ContentValues values = new ContentValues();
        values.put("nome", armazenamento.getNome());
        values.put("tamanhoExterno", armazenamento.getTamanhoExterno());
        values.put("areaUtil", armazenamento.getAreaUtil());
        values.put("estadoConservacao", armazenamento.getEstadoConservacao());
        values.put("cor", armazenamento.getCor());
        values.put("patrocinio", armazenamento.getPatrocinio());
        values.put("dataFabricacao", armazenamento.getDataFabricacao());
        values.put("peso", armazenamento.getPeso());
        values.put("dataValidade", armazenamento.getDataValidade());

        Long id = inserirArmazenameto(values);

        return id;

    }

    //metodo inseri fornecedor no banco
    private Long inserirArmazenameto(ContentValues values) throws SQLException {
        Long id = conn.insert(NOME_TABELA_ARMAZENAMENTO, null, values);
        return id;
    }

    //metodo atualiza Fornecedor
    public int atualizar(Armazenamento armazenamento)  {

        ContentValues values = new ContentValues();
        values.put("nome", armazenamento.getNome());
        values.put("tamanhoExterno", armazenamento.getTamanhoExterno());
        values.put("areaUtil", armazenamento.getAreaUtil());
        values.put("estadoConservacao", armazenamento.getEstadoConservacao());
        values.put("cor", armazenamento.getCor());
        values.put("patrocinio", armazenamento.getPatrocinio());
        values.put("dataFabricacao", armazenamento.getDataFabricacao());
        values.put("peso", armazenamento.getPeso());
        values.put("dataValidade", armazenamento.getDataValidade());

        String id = String.valueOf(armazenamento.getId());
        String where = "id=?";
        String[] whereArgs = new String[] { id };
        int count = atualizar(values, where, whereArgs);

        return count;

    }

    //metodo atualiza armazenamento
    private int atualizar(ContentValues values, String where, String[] whereArgs) {

        int count = conn.update(NOME_TABELA_ARMAZENAMENTO, values, where, whereArgs);
        Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
        return count;

    }


    //metodo deleta armazenamento
    public void deletar(Long id_Armazenamento) {

        String where = "id=?";
        String id = String.valueOf(id_Armazenamento);
        String[] whereArgs = new String[] { id };
        deletar(where, whereArgs);

    }

    //metodo deleta Armazenamento
    private void deletar(String where, String[] whereArgs) {

        conn.delete(NOME_TABELA_ARMAZENAMENTO, where, whereArgs);
        Log.i(CATEGORIA, "Deletou registro");
    }

    //Retorna o cursor com todos os clientes
    public Cursor getCursor() throws SQLException {

        Cursor cursor =  conn.rawQuery("SELECT * FROM armazenamento; ", null);
        return cursor;

    }

    
    //metodo retorna uma lista de armazenamento
    public List<Armazenamento> listarArmazenamento() throws SQLException{

        List<Armazenamento> armazenamentos = new ArrayList<>();

        Cursor c = getCursor();
        if(c == null){
            return null;
        }

        while(c.moveToNext()) {
            //Recupera os indices das colunas
            Armazenamento armazenamento = new Armazenamento();
            armazenamento.setId(c.getLong(c.getColumnIndex("id")));
            armazenamento.setNome(c.getString(c.getColumnIndex("nome")));
            armazenamento.setTamanhoExterno(c.getInt(c.getColumnIndex("tamanhoExterno")));
            armazenamento.setAreaUtil(c.getFloat(c.getColumnIndex("areaUtil")));
            armazenamento.setEstadoConservacao(c.getInt(c.getColumnIndex("estadoConservacao")));
            armazenamento.setCor(c.getString(c.getColumnIndex("cor")));
            armazenamento.setPatrocinio(c.getString(c.getColumnIndex("patrocinio")));
            armazenamento.setDataFabricacao(c.getString(c.getColumnIndex("dataFabricacao")));
            armazenamento.setPeso(c.getFloat(c.getColumnIndex("peso")));
            armazenamento.setDataValidade(c.getString(c.getColumnIndex("dataValidade")));

            armazenamentos.add(armazenamento);
        }

        return armazenamentos;
    }

    //Fechar o banco
    public void fechar(){
        if(conn != null) {
            conn.close();
        }
    }


}
