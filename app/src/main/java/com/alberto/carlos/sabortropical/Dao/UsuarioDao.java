package com.alberto.carlos.sabortropical.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alberto.carlos.sabortropical.Entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuporteE6530 on 08/06/2017.
 */

public class UsuarioDao {

    private static final String CATEGORIA = "Sabor Tropical";
    //Nome do Banco
    private static final String NOME_BANCO = "sabor_tropical";
    //Nome da tabela
    private static final String NOME_TABELA_PESSOA = "pessoas";
    //Nome da tabela
    private static final String NOME_TABELA_USUARIO = "usuarios";

    protected SQLiteDatabase db;

    public static String[] colunas = new String[] {"id", "nome", "telefone", "email", "rua", "numero",
            "bairro", "cep", "complemento", "cidade", "estado"};

    public UsuarioDao(Context ctx) {
        //Abre o banco de dados
        db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
    }

    public UsuarioDao() {
        // TODO Auto-generated constructor stub
    }

    //Salva um cliente ou atualiza
    public Long salvar(Usuario usuario) {
        Long id = usuario.getId();
        if(id != null) {
            id = (long) atualizar(usuario);
        } else {
            //Insere um novo
            id = inserir(usuario);
        }

        return id;
    }

    public Long inserir(Usuario usuario) {

        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("sobreNome", usuario.getSobreNome());
        values.put("dataNascimento", usuario.getDataNascimento());
        values.put("corPele", usuario.getCorPele());
        values.put("corOlhos", usuario.getCorOlhos());
        values.put("sexo", usuario.getSexo());
        values.put("nomePai", usuario.getNomePai());
        values.put("nomeMae", usuario.getNomeMae());
        values.put("estadoCivil", usuario.getEstadoCivil());
        values.put("cpf", usuario.getCpf());
        values.put("identidade", usuario.getIdentidade());

        Long id = inserirPessoa(values);

        values = null;
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());
        values.put("dataAdmissao", usuario.getDataAdmissao());
        values.put("nivelAcesso", usuario.getNivel());

        inserirUsuario(values);
        return id;
    }

    private Long inserirPessoa(ContentValues values) {
        Long id = db.insert(NOME_TABELA_PESSOA, null, values);
        return id;
    }

    private void inserirUsuario(ContentValues values) {
        db.insert(NOME_TABELA_USUARIO, null, values);
    }

    public int atualizar(Usuario usuario) {

        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("sobreNome", usuario.getSobreNome());
        values.put("dataNascimento", usuario.getDataNascimento());
        values.put("corPele", usuario.getCorPele());
        values.put("corOlhos", usuario.getCorOlhos());
        values.put("sexo", usuario.getSexo());
        values.put("nomePai", usuario.getNomePai());
        values.put("nomeMae", usuario.getNomeMae());
        values.put("estadoCivil", usuario.getEstadoCivil());
        values.put("cpf", usuario.getCpf());
        values.put("identidade", usuario.getIdentidade());

        String id = String.valueOf(usuario.getId());
        String where = "id=?";
        String[] whereArgs = new String[] { id };
        int countPessoa = atualizarPessoa(values, where, whereArgs);

        values = null;
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());
        values.put("dataAdmissao", usuario.getDataAdmissao());
        values.put("nivelAcesso", usuario.getNivel());
        int countUsuario = atualizarUsuario(values, where, whereArgs);


        int count = countPessoa + countUsuario;
        return count;

    }

    private int atualizarPessoa(ContentValues values, String where, String[] whereArgs) {

        int count = db.update(NOME_TABELA_PESSOA, values, where, whereArgs);
        Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
        return count;

    }

    private int atualizarUsuario(ContentValues values, String where, String[] whereArgs) {

        int count = db.update(NOME_TABELA_USUARIO, values, where, whereArgs);
        Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
        return count;

    }



    public int deletar(Long id) {

        String where = "id=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[] { _id };
        int count = deletarUsuario(where, whereArgs);
        return count;

    }

    private int deletarUsuario(String where, String[] whereArgs) {

        int count = db.delete(NOME_TABELA_USUARIO, where, whereArgs);
        Log.i(CATEGORIA, "Deletou [" + count + "] registros");

        return count;
    }

    private int deletarPessoa(String where, String[] whereArgs) {

        int count = db.delete(NOME_TABELA_PESSOA, where, whereArgs);
        Log.i(CATEGORIA, "Deletou [" + count + "] registros");

        return count;
    }


    //Retorna o cursor com todos os carros
    public Cursor getCursor() {

        try {
            //select * from clientes
//			return db.rawQuery("SELECT * FROM clientes", null);
            Cursor c = db.query(NOME_TABELA_PESSOA, colunas, null, null, null, null, null);
            return c;
        } catch (SQLException e) {
            Log.i(CATEGORIA, "Erro ao buscar os clientes: " + e.toString());
            return null;
        }

    }

    /*public List<Cliente> listarClientes(){

        List<Cliente> clientes = new ArrayList<Cliente>();

        Cursor c = getCursor();
        if(c == null){
            return null;
        }

//		"nome", "telefone", "email", "rua", "numero",
//		"bairro", "cep", "complemento", "cidade", "estado
        while(c.moveToNext()) {
            //Recupera os indices das colunas
            Cliente cliente = new Cliente();
            cliente.setId(c.getLong(c.getColumnIndex("id")));
            cliente.setNome(c.getString(c.getColumnIndex("nome")));
            cliente.setTelefone(c.getString(c.getColumnIndex("telefone")));
            cliente.setEmail(c.getString(c.getColumnIndex("email")));
            cliente.setRua(c.getString(c.getColumnIndex("rua")));
            cliente.setNumero(c.getInt(c.getColumnIndex("numero")));
            cliente.setBairro(c.getString(c.getColumnIndex("bairro")));
            cliente.setCep(c.getString(c.getColumnIndex("cep")));
            cliente.setComplemento(c.getString(c.getColumnIndex("complemento")));
            cliente.setCidade(c.getString(c.getColumnIndex("cidade")));
            cliente.setEstado(c.getString(c.getColumnIndex("estado")));

            clientes.add(cliente);
        }

        return clientes;
    }*/

    //Fechar o banco
    public void fechar(){
        if(db != null) {
            db.close();
        }
    }


}
