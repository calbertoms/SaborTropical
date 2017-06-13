package com.alberto.carlos.sabortropical.Telas.Cliente;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alberto.carlos.sabortropical.BancoDeDados.DatabaseUsuario;
import com.alberto.carlos.sabortropical.Dao.UsuarioDao;
import com.alberto.carlos.sabortropical.Entidades.Usuario;
import com.alberto.carlos.sabortropical.R;

import java.util.List;

//classe principal
public class ClientesActivity extends AppCompatActivity {

    private ListView listaUsuarios;
    private DatabaseUsuario databaseUsuario;
    private SQLiteDatabase conn;
    private long idUsuario;
    Usuario usuarioSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        listaUsuarios = (ListView) findViewById(R.id.lista_de_usuarios);

        registerForContextMenu(listaUsuarios);

        try {
            databaseUsuario = new DatabaseUsuario(ClientesActivity.this);
            conn = databaseUsuario.getReadableDatabase();
            UsuarioDao dao = new UsuarioDao(conn);
            List<Usuario> usuarios = dao.listarUsuarios();
            ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>
                    (ClientesActivity.this, android.R.layout.simple_list_item_1, usuarios);

            listaUsuarios.setAdapter(adapter);

        }
        catch (SQLException e){

            Toast.makeText(ClientesActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

        }

        // OnCLickListiner For List Items
        listaUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

                Intent editar = new Intent(ClientesActivity.this, ClientesEditDelActivity.class);
                usuarioSelecionado = (Usuario) listaUsuarios.getItemAtPosition(position);

                editar.putExtra("usuarioSelecionado", usuarioSelecionado);
                startActivity(editar);
            }
        });

    }

    @Override
    //cria o menu
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_usuarios, menu);

        return true;

    }

    @Override
    //quando um item do menu for selecionado
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_novoUsuario){

             Intent it = new Intent(this,ClientesCadActivity.class);
             startActivity(it);

        }

        return super.onOptionsItemSelected(item);

    }


}
