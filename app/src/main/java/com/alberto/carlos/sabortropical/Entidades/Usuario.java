package com.alberto.carlos.sabortropical.Entidades;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.ProdutoDao;
import com.alberto.carlos.sabortropical.Dao.UsuarioDao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SuporteE6530 on 08/06/2017.
 */

public class Usuario extends Pessoa implements Serializable {

    private String email;
    private String senha;
    private String dataAdmissao;
    private int nivel;
    private Database databaseUsuario;
    private SQLiteDatabase conn;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return this.getId() + " - " + this.getNome() + " - " + this.getSobreNome();
    }

    public long Cadastrar(Context context) throws SQLException {

        databaseUsuario = new Database(context);
        conn = databaseUsuario.getWritableDatabase();
        UsuarioDao dao = new UsuarioDao(conn);

        Long idUsuario= dao.inserir(this);
        dao.fechar();

        return idUsuario;
    }

    public List<Usuario> Visualizar(Context context) throws SQLException {

        List<Usuario> usuarios;

        databaseUsuario = new Database(context);
        conn = databaseUsuario.getReadableDatabase();
        UsuarioDao dao = new UsuarioDao(conn);
        usuarios = dao.listarUsuarios();
        dao.fechar();

        return usuarios;

    }

    public int Editar(Context context) throws SQLException {

        databaseUsuario = new Database(context);
        conn = databaseUsuario.getWritableDatabase();
        UsuarioDao dao = new UsuarioDao(conn);

        int qtdAtualizadas = dao.atualizar(this);
        dao.fechar();

        return qtdAtualizadas;

    }

    public void Deletar(Context context,long id_usuario) throws SQLException{

        databaseUsuario = new Database(context);
        conn = databaseUsuario.getWritableDatabase();
        UsuarioDao dao = new UsuarioDao(conn);
        dao.deletar(id_usuario);
        dao.fechar();

    }

    public boolean VerificaExistencia(Context context,String email, String senha){

        databaseUsuario = new Database(context);
        conn = databaseUsuario.getReadableDatabase();
        UsuarioDao dao = new UsuarioDao(conn);
        boolean existe = dao.existeUsuario(email,senha);
        dao.fechar();

        return existe;
    }

}
