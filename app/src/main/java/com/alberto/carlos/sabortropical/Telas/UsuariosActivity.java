package com.alberto.carlos.sabortropical.Telas;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.UsuarioDao;
import com.alberto.carlos.sabortropical.Entidades.Usuario;
import com.alberto.carlos.sabortropical.R;

import java.util.List;

public class UsuariosActivity extends AppCompatActivity {

    private ListView listaUsuarios;
    private Database database;
    private SQLiteDatabase conn;
    private long idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        listaUsuarios = (ListView) findViewById(R.id.lista_de_usuarios);

        registerForContextMenu(listaUsuarios);

        try {
            database = new Database(UsuariosActivity.this);
            conn = database.getReadableDatabase();
            UsuarioDao dao = new UsuarioDao(conn);
            Usuario usuario = new Usuario();
            usuario.setNome("Carlos");
            usuario.setSobreNome("Alberto");
            usuario.setDataNascimento("10/10/1970");
            usuario.setCorPele(1);
            usuario.setCorOlhos(1);
            usuario.setSexo(1);
            usuario.setNomePai("João");
            usuario.setNomeMae("Maria");
            usuario.setEstadoCivil(1);
            usuario.setCpf("06712833291");
            usuario.setIdentidade("76625182");
            usuario.setEmail("calbertoms@gmail.com");
            usuario.setSenha("123456");
            usuario.setDataAdmissao("09/06/2017");
            usuario.setNivel(1);
            idUsuario = dao.inserir(usuario);
            dao.fechar();
            conn = database.getReadableDatabase();
            UsuarioDao dao2 = new UsuarioDao(conn);
            List<Usuario> usuarios = dao2.listarUsuarios();
            ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>
                    (UsuariosActivity.this, android.R.layout.simple_list_item_1, usuarios);

            listaUsuarios.setAdapter(adapter);
            Toast.makeText(UsuariosActivity.this,"Criado com sucesso. Usuario: " + idUsuario,Toast.LENGTH_LONG).show();

        }
        catch (SQLException e){

            Toast.makeText(UsuariosActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //this.carregarLista();
    }

    /*private void carregarLista() {
        database = new Database(UsuariosActivity.this);
        conn = database.getReadableDatabase();
        UsuarioDao dao = new UsuarioDao(conn);
        List<Usuario> usuarios = dao.listarUsuarios();
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>
                (UsuariosActivity.this, android.R.layout.simple_list_item_1, usuarios);

        listaUsuarios.setAdapter(adapter);

    }*/

}
