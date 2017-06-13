package com.alberto.carlos.sabortropical.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.alberto.carlos.sabortropical.Entidades.Cliente;
import com.alberto.carlos.sabortropical.Entidades.Endereco;

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
    private static final String NOME_TABELA_ENDERECO = "endereco";
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

        Long id_pessoa = inserirPessoa(valuesPessoa);

        ContentValues valuesEndereco = new ContentValues();
        valuesEndereco.put("logradouro", cliente.getEndereco().getLogradouro());
        valuesEndereco.put("numero", cliente.getEndereco().getNumero());
        valuesEndereco.put("bairro", cliente.getEndereco().getBairro());
        valuesEndereco.put("cidade", cliente.getEndereco().getCidade());
        valuesEndereco.put("uf", cliente.getEndereco().getUf());
        valuesEndereco.put("pais", cliente.getEndereco().getPais());
        valuesEndereco.put("pontoReferencia", cliente.getEndereco().getPontoreferencia());
        valuesEndereco.put("cep", cliente.getEndereco().getCep());

        Long id_endereco = inserirEndereco(valuesEndereco);

        ContentValues valuescliente = new ContentValues();
        valuescliente.put("id_pessoa", id_pessoa);
        valuescliente.put("id_endereco", id_endereco);
        valuescliente.put("regiao", cliente.getRegiao());
        valuescliente.put("pontos", cliente.getPontos());

        inserirCliente(valuescliente);
        return id_pessoa;

    }

    //metodo inseri pessoa no banco
    private Long inserirPessoa(ContentValues values) {
        Long id = conn.insert(NOME_TABELA_PESSOA, null, values);
        return id;
    }

    //metodo inseri pessoa no banco
    private Long inserirEndereco(ContentValues values) {
        Long id = conn.insert(NOME_TABELA_ENDERECO, null, values);
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

        String id_pessoa = String.valueOf(cliente.getId());
        String where = "id=?";
        String[] whereArgs = new String[] { id_pessoa };
        int countPessoa = atualizarPessoa(valuesPessoa, where, whereArgs);

        ContentValues valuesEndereco = new ContentValues();
        valuesEndereco.put("logradouro", cliente.getEndereco().getLogradouro());
        valuesEndereco.put("numero", cliente.getEndereco().getNumero());
        valuesEndereco.put("bairro", cliente.getEndereco().getBairro());
        valuesEndereco.put("cidade", cliente.getEndereco().getCidade());
        valuesEndereco.put("uf", cliente.getEndereco().getUf());
        valuesEndereco.put("pais", cliente.getEndereco().getPais());
        valuesEndereco.put("pontoReferencia", cliente.getEndereco().getPontoreferencia());
        valuesEndereco.put("cep", cliente.getEndereco().getCep());

        String id_endereco = String.valueOf(cliente.getEndereco().getId());
        where = "id=?";
        whereArgs = new String[] { id_endereco };
        int countEndereco = atualizarEndereco(valuesEndereco, where, whereArgs);

        ContentValues valuescliente = new ContentValues();

        valuescliente.put("regiao", cliente.getRegiao());
        valuescliente.put("pontos", cliente.getPontos());

        where = "id_pessoa=? AND id_endereco=?";
        whereArgs = new String[] { id_pessoa, id_endereco };
        int countcliente = atualizarcliente(valuescliente, where, whereArgs);


        int count = countPessoa + countcliente + countEndereco;
        return count;

    }

    //metodo atualiza pessoa
    private int atualizarPessoa(ContentValues values, String where, String[] whereArgs) {

        int count = conn.update(NOME_TABELA_PESSOA, values, where, whereArgs);
        Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
        return count;

    }

    //metodo atualiza endereco
    private int atualizarEndereco(ContentValues values, String where, String[] whereArgs) {

        int count = conn.update(NOME_TABELA_ENDERECO, values, where, whereArgs);
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
    public void deletar(Long id_Pessoa, Long id_Endereco) {

        String where = "id=?";
        String id_endereco = String.valueOf(id_Endereco);
        String[] whereArgs = new String[] { id_endereco };
        deletarEndereco(where, whereArgs);
        String id_pessoa = String.valueOf(id_Pessoa);
        where = "id_pessoa=?";
        whereArgs = new String[] { id_pessoa };
        deletarCliente(where, whereArgs);
        where = "id=?";
        deletarPessoa(where, whereArgs);

    }

    //metodo deleta pessoa
    private void deletarEndereco(String where, String[] whereArgs) {

        conn.delete(NOME_TABELA_ENDERECO, where, whereArgs);
        Log.i(CATEGORIA, "Deletou registro");
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
            Cursor cursor =  conn.rawQuery("SELECT t1.id,t1.nome,t1.sobreNome,t1.dataNascimento,t1.corPele,t1.corOlhos,t1.sexo,t1.nomePai,t1.nomeMae,t1.estadoCivil,t1.cpf,t1.identidade,t2.id_pessoa,t2.id_endereco,t2.regiao,t2.pontos,t3.logradouro,t3.numero,t3.bairro,t3.cidade,t3.uf,t3.pais,t3.pontoReferencia,t3.cep FROM pessoas t1 INNER JOIN clientes t2 ON (t1.id = t2.id_pessoa) INNER JOIN endereco t3 ON (t2.id_endereco = t3.id)", null);
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
            Endereco endereco = new Endereco();
            cliente.setId(c.getLong(c.getColumnIndex("id_pessoa")));
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
            endereco.setId(c.getLong(c.getColumnIndex("id_endereco")));
            endereco.setLogradouro(c.getString(c.getColumnIndex("logradouro")));
            endereco.setNumero(c.getInt(c.getColumnIndex("numero")));
            endereco.setBairro(c.getString(c.getColumnIndex("bairro")));
            endereco.setCidade(c.getString(c.getColumnIndex("cidade")));
            endereco.setUf(c.getString(c.getColumnIndex("uf")));
            endereco.setPais(c.getString(c.getColumnIndex("pais")));
            endereco.setPontoreferencia(c.getString(c.getColumnIndex("pontoReferencia")));
            endereco.setCep(c.getString(c.getColumnIndex("cep")));
            cliente.setEndereco(endereco);
           

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
