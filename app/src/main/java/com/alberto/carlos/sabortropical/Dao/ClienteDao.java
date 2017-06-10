package com.alberto.carlos.sabortropical.Dao;

import android.content.ContentValues;
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

public class ClienteDao {

    private static final String CATEGORIA = "Sabor Tropical";
    //Nome da tabela
    private static final String NOME_TABELA_PESSOA = "pessoas";
    //Nome da tabela
    private static final String NOME_TABELA_USUARIO = "usuarios";


    private SQLiteDatabase conn;

    public ClienteDao(SQLiteDatabase conn) {
        this.conn = conn;
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

        ContentValues valuesPessoa = new ContentValues();
        valuesPessoa.put("nome", usuario.getNome());
        valuesPessoa.put("sobreNome", usuario.getSobreNome());
        valuesPessoa.put("dataNascimento", usuario.getDataNascimento());
        valuesPessoa.put("corPele", usuario.getCorPele());
        valuesPessoa.put("corOlhos", usuario.getCorOlhos());
        valuesPessoa.put("sexo", usuario.getSexo());
        valuesPessoa.put("nomePai", usuario.getNomePai());
        valuesPessoa.put("nomeMae", usuario.getNomeMae());
        valuesPessoa.put("estadoCivil", usuario.getEstadoCivil());
        valuesPessoa.put("cpf", usuario.getCpf());
        valuesPessoa.put("identidade", usuario.getIdentidade());

        Long id = inserirPessoa(valuesPessoa);

        ContentValues valuesUsuario = new ContentValues();
        valuesUsuario.put("email", usuario.getEmail());
        valuesUsuario.put("senha", usuario.getSenha());
        valuesUsuario.put("dataAdmissao", usuario.getDataAdmissao());
        valuesUsuario.put("nivelAcesso", usuario.getNivel());

        inserirUsuario(valuesUsuario);
        return id;

    }

    private Long inserirPessoa(ContentValues values) {
        Long id = conn.insert(NOME_TABELA_PESSOA, null, values);
        return id;
    }

    private void inserirUsuario(ContentValues values) {
        conn.insert(NOME_TABELA_USUARIO, null, values);
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

        int count = conn.update(NOME_TABELA_PESSOA, values, where, whereArgs);
        Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
        return count;

    }

    private int atualizarUsuario(ContentValues values, String where, String[] whereArgs) {

        int count = conn.update(NOME_TABELA_USUARIO, values, where, whereArgs);
        Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
        return count;

    }



    public int deletar(Long id) {

        String where = "id=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[] { _id };
        int countUsuario = deletarUsuario(where, whereArgs);
        int countPessoa = deletarPessoa(where, whereArgs);
        int count = countPessoa + countUsuario;

        return count;

    }

    private int deletarUsuario(String where, String[] whereArgs) {

        int count = conn.delete(NOME_TABELA_USUARIO, where, whereArgs);
        Log.i(CATEGORIA, "Deletou [" + count + "] registros");

        return count;
    }

    private int deletarPessoa(String where, String[] whereArgs) {

        int count = conn.delete(NOME_TABELA_PESSOA, where, whereArgs);
        Log.i(CATEGORIA, "Deletou [" + count + "] registros");

        return count;
    }


    //Retorna o cursor com todos os carros
    public Cursor getCursor() {

        try {
 			 Cursor cursor =  conn.rawQuery("SELECT id,nome,sobreNome FROM pessoas", null);
            return cursor;
        } catch (SQLException e) {
            Log.d(CATEGORIA, "Erro ao buscar os usuarios: " + e.toString());
            return null;
        }

    }

    public List<Usuario> listarUsuarios(){

        List<Usuario> usuarios = new ArrayList<>();

        Cursor c = getCursor();
        if(c == null){
            return null;
        }

        while(c.moveToNext()) {
            //Recupera os indices das colunas
            Usuario usuario = new Usuario();
            usuario.setId(c.getLong(c.getColumnIndex("id")));
            usuario.setNome(c.getString(c.getColumnIndex("nome")));
            usuario.setSobreNome(c.getString(c.getColumnIndex("sobreNome")));
            /*usuario.setDataNascimento(c.getString(c.getColumnIndex("dataNascimento")));
            usuario.setCorPele(c.getInt(c.getColumnIndex("corPele")));
            usuario.setCorOlhos(c.getInt(c.getColumnIndex("corOlhos")));
            usuario.setSexo(c.getInt(c.getColumnIndex("sexo")));
            usuario.setNomePai(c.getString(c.getColumnIndex("nomePai")));
            usuario.setNomeMae(c.getString(c.getColumnIndex("nomeMae")));
            usuario.setEstadoCivil(c.getInt(c.getColumnIndex("estadoCivil")));
            usuario.setCpf(c.getString(c.getColumnIndex("cpf")));
            usuario.setIdentidade(c.getString(c.getColumnIndex("identidade")));
            usuario.setEmail(c.getString(c.getColumnIndex("email")));
            usuario.setSenha(c.getString(c.getColumnIndex("senha")));
            usuario.setDataAdmissao(c.getString(c.getColumnIndex("dataAdmissao")));
            usuario.setNivel(c.getInt(c.getColumnIndex("nivelAcesso")));*/

            usuarios.add(usuario);
        }

        return usuarios;
    }

    //Fechar o banco
    public void fechar(){
        if(conn != null) {
            conn.close();
        }
    }


}
