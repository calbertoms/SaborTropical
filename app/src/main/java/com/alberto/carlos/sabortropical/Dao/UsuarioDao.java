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

//classe de persistencia do usuario
public class UsuarioDao {
    //nome do banco
    private static final String CATEGORIA = "Sabor Tropical";
    //Nome da tabela
    private static final String NOME_TABELA_PESSOA = "pessoas";
    //Nome da tabela
    private static final String NOME_TABELA_USUARIO = "usuarios";

    //objeto de conexão com banco
    private SQLiteDatabase conn;

    public UsuarioDao(SQLiteDatabase conn) {

        this.conn = conn;

    }

    //metodo inserir usuario
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
        valuesUsuario.put("id", id);
        valuesUsuario.put("email", usuario.getEmail());
        valuesUsuario.put("senha", usuario.getSenha());
        valuesUsuario.put("dataAdmissao", usuario.getDataAdmissao());
        valuesUsuario.put("nivelAcesso", usuario.getNivel());

        inserirUsuario(valuesUsuario);
        return id;

    }

    //metodo inseri pessoa no banco
    private Long inserirPessoa(ContentValues values) {
        Long id = conn.insert(NOME_TABELA_PESSOA, null, values);
        return id;
    }


    //metodo inseri usuario no banco
    private void inserirUsuario(ContentValues values) {
        conn.insert(NOME_TABELA_USUARIO, null, values);
    }

    //metodo atualiza usuario
    public int atualizar(Usuario usuario) {

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

        String id = String.valueOf(usuario.getId());
        String where = "id=?";
        String[] whereArgs = new String[] { id };
        int countPessoa = atualizarPessoa(valuesPessoa, where, whereArgs);

        ContentValues valuesUsuario = new ContentValues();

        valuesUsuario.put("email", usuario.getEmail());
        valuesUsuario.put("senha", usuario.getSenha());
        valuesUsuario.put("dataAdmissao", usuario.getDataAdmissao());
        valuesUsuario.put("nivelAcesso", usuario.getNivel());
        int countUsuario = atualizarUsuario(valuesUsuario, where, whereArgs);


        int count = countPessoa + countUsuario;
        return count;

    }

    //metodo atualiza pessoa
    private int atualizarPessoa(ContentValues values, String where, String[] whereArgs) {

        int count = conn.update(NOME_TABELA_PESSOA, values, where, whereArgs);
        Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
        return count;

    }

    //metodo atualiza usuario
    private int atualizarUsuario(ContentValues values, String where, String[] whereArgs) {

        int count = conn.update(NOME_TABELA_USUARIO, values, where, whereArgs);
        Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
        return count;

    }


    //metodo deleta usuario
    public void deletar(Long id) {

        String where = "id=?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[] { _id };
        deletarUsuario(where, whereArgs);
        deletarPessoa(where, whereArgs);

    }

    //metodo deleta usuario
    private void deletarUsuario(String where, String[] whereArgs) {

        conn.delete(NOME_TABELA_USUARIO, where, whereArgs);
        Log.i(CATEGORIA, "Deletou registro");

    }

    //metodo deleta pessoa
    private void deletarPessoa(String where, String[] whereArgs) {

        conn.delete(NOME_TABELA_PESSOA, where, whereArgs);
        Log.i(CATEGORIA, "Deletou registro");
    }


    //Retorna o cursor com todos os usuarios
    public Cursor getCursor() throws SQLException {

        Cursor cursor =  conn.rawQuery("SELECT t1.id,t1.nome,t1.sobreNome,t1.dataNascimento,t1.corPele,t1.corOlhos,t1.sexo,t1.nomePai,t1.nomeMae,t1.estadoCivil,t1.cpf,t1.identidade,t2.email,t2.senha,t2.dataAdmissao,t2.nivelAcesso FROM pessoas t1 INNER JOIN usuarios t2 ON (t1.id = t2.id)", null);
        return cursor;

    }

    //metodo verifica existencia de usuario
    public Boolean existeUsuario(String email, String senha) throws SQLException{

        String[] whereArgs = new String[] { email, senha };
        Cursor cursor =  conn.rawQuery("SELECT email,senha FROM usuarios WHERE email = ? AND senha = ?", whereArgs);
        if ((cursor == null) || (cursor.getCount() < 1)) {
            return false;
        }
        else{
            return true;
        }

    }

    //metodo retorna uma lista de usuario
    public List<Usuario> listarUsuarios() throws SQLException{

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
            usuario.setDataNascimento(c.getString(c.getColumnIndex("dataNascimento")));
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
            usuario.setNivel(c.getInt(c.getColumnIndex("nivelAcesso")));

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
