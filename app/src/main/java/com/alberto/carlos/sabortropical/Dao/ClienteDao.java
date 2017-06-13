package com.alberto.carlos.sabortropical.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.alberto.carlos.sabortropical.Entidades.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuporteE6530 on 08/06/2017.
 */

//classe de persistencia do cliente
public class ClienteDao {
    //nome do banco
    private static final String CATEGORIA = "Sabor Tropical";
    //Nome da tabela
    private static final String NOME_TABELA_PESSOA = "pessoas";
    //Nome da tabela
    private static final String NOME_TABELA_CLIENTE = "clientes";

    //objeto de conexão com banco
    private SQLiteDatabase conn;

    public ClienteDao(SQLiteDatabase conn) {

        this.conn = conn;

    }

    //metodo inserir cliente
    public Long inserir(Cliente cliente) {

        ContentValues valuesPessoa = new ContentValues();
        valuesPessoa.put("nome", cliente.getNome());
        valuesPessoa.put("sobreNome", cliente.getSobreNome());
        valuesPessoa.put("dataNascimento", cliente.getDataNascimento());
        valuesPessoa.put("corPele", cliente.getCorPele());
        valuesPessoa.put("corOlhos", cliente.getCorOlhos());
        valuesPessoa.put("sexo", cliente.getSexo());
        valuesPessoa.put("nomePai", cliente.getNomePai());
        valuesPessoa.put("nomeMae", cliente.getNomeMae());
        valuesPessoa.put("estadoCivil", cliente.getEstadoCivil());
        valuesPessoa.put("cpf", cliente.getCpf());
        valuesPessoa.put("identidade", cliente.getIdentidade());

        Long id = inserirPessoa(valuesPessoa);

        ContentValues valuescliente = new ContentValues();
        valuescliente.put("id", id);
        valuescliente.put("regiao", cliente.getRegiao());
        valuescliente.put("pontos", cliente.getPontos());

        inserirCliente(valuescliente);
        return id;

    }

    //metodo inseri pessoa no banco
    private Long inserirPessoa(ContentValues values) {
        Long id = conn.insert(NOME_TABELA_PESSOA, null, values);
        return id;
    }


    //metodo inseri cliente no banco
    private void inserirCliente(ContentValues values) {
        conn.insert(NOME_TABELA_CLIENTE, null, values);
    }

    //metodo atualiza cliente
    public int atualizar(Cliente cliente) {

        ContentValues valuesPessoa = new ContentValues();
        valuesPessoa.put("nome", cliente.getNome());
        valuesPessoa.put("sobreNome", cliente.getSobreNome());
        valuesPessoa.put("dataNascimento", cliente.getDataNascimento());
        valuesPessoa.put("corPele", cliente.getCorPele());
        valuesPessoa.put("corOlhos", cliente.getCorOlhos());
        valuesPessoa.put("sexo", cliente.getSexo());
        valuesPessoa.put("nomePai", cliente.getNomePai());
        valuesPessoa.put("nomeMae", cliente.getNomeMae());
        valuesPessoa.put("estadoCivil", cliente.getEstadoCivil());
        valuesPessoa.put("cpf", cliente.getCpf());
        valuesPessoa.put("identidade", cliente.getIdentidade());

        String id = String.valueOf(cliente.getId());
        String where = "id=?";
        String[] whereArgs = new String[] { id };
        int countPessoa = atualizarPessoa(valuesPessoa, where, whereArgs);

        ContentValues valuescliente = new ContentValues();

        valuescliente.put("regiao", cliente.getRegiao());
        valuescliente.put("pontos", cliente.getPontos());
        int countcliente = atualizarcliente(valuescliente, where, whereArgs);


        int count = countPessoa + countcliente;
        return count;

    }

    //metodo atualiza pessoa
    private int atualizarPessoa(ContentValues values, String where, String[] whereArgs) {

        int count = conn.update(NOME_TABELA_PESSOA, values, where, whereArgs);
        Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
        return count;

    }

    //metodo atualiza cliente
    private int atualizarcliente(ContentValues values, String where, String[] whereArgs) {

        int count = conn.update(NOME_TABELA_CLIENTE, values, where, whereArgs);
        Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
        return count;

    }


    //metodo deleta cliente
    public void deletar(Long id) {

        String where = "id=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[] { _id };
        deletarCliente(where, whereArgs);
        deletarPessoa(where, whereArgs);

    }

    //metodo deleta cliente
    private void deletarCliente(String where, String[] whereArgs) {

        conn.delete(NOME_TABELA_CLIENTE, where, whereArgs);
        Log.i(CATEGORIA, "Deletou registro");

    }

    //metodo deleta pessoa
    private void deletarPessoa(String where, String[] whereArgs) {

        conn.delete(NOME_TABELA_PESSOA, where, whereArgs);
        Log.i(CATEGORIA, "Deletou registro");
    }


    //Retorna o cursor com todos os clientes
    public Cursor getCursor() {

        try {
            Cursor cursor =  conn.rawQuery("SELECT t1.id,t1.nome,t1.sobreNome,t1.dataNascimento,t1.corPele,t1.corOlhos,t1.sexo,t1.nomePai,t1.nomeMae,t1.estadoCivil,t1.cpf,t1.identidade,t2.email,t2.senha,t2.dataAdmissao,t2.nivelAcesso FROM pessoas t1 INNER JOIN clientes t2 ON (t1.id = t2.id)", null);
            return cursor;
        } catch (SQLException e) {
            Log.d(CATEGORIA, "Erro ao buscar os clientes: " + e.toString());
            return null;
        }

    }

    
    //metodo retorna uma lista de cliente
    public List<Cliente> listarClientes(){

        List<Cliente> clientes = new ArrayList<>();

        Cursor c = getCursor();
        if(c == null){
            return null;
        }

        while(c.moveToNext()) {
            //Recupera os indices das colunas
            Cliente cliente = new Cliente();
            cliente.setId(c.getLong(c.getColumnIndex("id")));
            cliente.setNome(c.getString(c.getColumnIndex("nome")));
            cliente.setSobreNome(c.getString(c.getColumnIndex("sobreNome")));
            cliente.setDataNascimento(c.getString(c.getColumnIndex("dataNascimento")));
            cliente.setCorPele(c.getInt(c.getColumnIndex("corPele")));
            cliente.setCorOlhos(c.getInt(c.getColumnIndex("corOlhos")));
            cliente.setSexo(c.getInt(c.getColumnIndex("sexo")));
            cliente.setNomePai(c.getString(c.getColumnIndex("nomePai")));
            cliente.setNomeMae(c.getString(c.getColumnIndex("nomeMae")));
            cliente.setEstadoCivil(c.getInt(c.getColumnIndex("estadoCivil")));
            cliente.setCpf(c.getString(c.getColumnIndex("cpf")));
            cliente.setIdentidade(c.getString(c.getColumnIndex("identidade")));
            cliente.setRegiao(c.getString(c.getColumnIndex("regiao")));
            cliente.setPontos(c.getInt(c.getColumnIndex("pontos")));
           

            clientes.add(cliente);
        }

        return clientes;
    }

    //Fechar o banco
    public void fechar(){
        if(conn != null) {
            conn.close();
        }
    }


}
