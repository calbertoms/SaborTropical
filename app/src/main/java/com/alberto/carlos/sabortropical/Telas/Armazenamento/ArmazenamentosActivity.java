package com.alberto.carlos.sabortropical.Telas.Armazenamento;

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

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.ArmazenamentoDao;
import com.alberto.carlos.sabortropical.Entidades.Armazenamento;
import com.alberto.carlos.sabortropical.R;

import java.util.List;

//classe principal
public class ArmazenamentosActivity extends AppCompatActivity {

    private ListView listaArmazenamentos;
    private Database database;
    private SQLiteDatabase conn;
    Armazenamento armazenamentoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armazenamentos);

        listaArmazenamentos = (ListView) findViewById(R.id.lista_de_armazenamentos);

        registerForContextMenu(listaArmazenamentos);

        try {
            database = new Database(ArmazenamentosActivity.this);
            conn = database.getReadableDatabase();
            ArmazenamentoDao dao = new ArmazenamentoDao(conn);
            List<Armazenamento> armazenamentos = dao.listarArmazenamento();
            ArrayAdapter<Armazenamento> adapter = new ArrayAdapter<Armazenamento>
                    (ArmazenamentosActivity.this, android.R.layout.simple_list_item_1, armazenamentos);

            listaArmazenamentos.setAdapter(adapter);

        }
        catch (SQLException e){

            Toast.makeText(ArmazenamentosActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

        }

        // OnCLickListiner For List Items
        listaArmazenamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

                Intent editar = new Intent(ArmazenamentosActivity.this, ArmazenamentosEditDelActivity.class);
                armazenamentoSelecionado = (Armazenamento) listaArmazenamentos.getItemAtPosition(position);
                editar.putExtra("armazenamentoSelecionado", armazenamentoSelecionado);
                startActivity(editar);
            }
        });

    }

    @Override
    //cria o menu
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_armazenamentos, menu);

        return true;

    }

    @Override
    //quando um item do menu for selecionado
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_novoArmazenamento){

             Intent it = new Intent(this,ArmazenamentosCadActivity.class);
             startActivity(it);

        }

        return super.onOptionsItemSelected(item);

    }


}
