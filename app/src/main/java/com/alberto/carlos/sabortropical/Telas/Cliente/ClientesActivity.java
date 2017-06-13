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

import com.alberto.carlos.sabortropical.BancoDeDados.DatabaseCliente;
import com.alberto.carlos.sabortropical.Dao.ClienteDao;
import com.alberto.carlos.sabortropical.Entidades.Cliente;
import com.alberto.carlos.sabortropical.R;

import java.util.List;

//classe principal
public class ClientesActivity extends AppCompatActivity {

    private ListView listaClientes;
    private DatabaseCliente databaseCliente;
    private SQLiteDatabase conn;
    private long idCliente;
    Cliente clienteSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        listaClientes = (ListView) findViewById(R.id.lista_de_clientes);

        registerForContextMenu(listaClientes);

        try {
            databaseCliente = new DatabaseCliente(ClientesActivity.this);
            conn = databaseCliente.getReadableDatabase();
            ClienteDao dao = new ClienteDao(conn);
            List<Cliente> clientes = dao.listarClientes();
            ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>
                    (ClientesActivity.this, android.R.layout.simple_list_item_1, clientes);

            listaClientes.setAdapter(adapter);

        }
        catch (SQLException e){

            Toast.makeText(ClientesActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

        }

        // OnCLickListiner For List Items
        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

                Intent editar = new Intent(ClientesActivity.this, ClientesEditDelActivity.class);
                clienteSelecionado = (Cliente) listaClientes.getItemAtPosition(position);
                editar.putExtra("clienteSelecionado", clienteSelecionado);
                startActivity(editar);
            }
        });

    }

    @Override
    //cria o menu
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_clientes, menu);

        return true;

    }

    @Override
    //quando um item do menu for selecionado
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_novoCliente){

             Intent it = new Intent(this,ClientesCadActivity.class);
             startActivity(it);

        }

        return super.onOptionsItemSelected(item);

    }


}
