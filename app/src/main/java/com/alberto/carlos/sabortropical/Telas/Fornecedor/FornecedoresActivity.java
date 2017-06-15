package com.alberto.carlos.sabortropical.Telas.Fornecedor;

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
import com.alberto.carlos.sabortropical.Dao.FornecedorDao;
import com.alberto.carlos.sabortropical.Entidades.Fornecedor;
import com.alberto.carlos.sabortropical.R;

import java.util.List;

//classe principal
public class FornecedoresActivity extends AppCompatActivity {

    private ListView listaFornecedores;
    Fornecedor fornecedorSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedores);

        listaFornecedores = (ListView) findViewById(R.id.lista_de_fornecedores);

        registerForContextMenu(listaFornecedores);

        try {
            Fornecedor fornecedor = new Fornecedor();
            List<Fornecedor> fornecedores = fornecedor.Visualizar(FornecedoresActivity.this);
            ArrayAdapter<Fornecedor> adapter = new ArrayAdapter<Fornecedor>
                    (FornecedoresActivity.this, android.R.layout.simple_list_item_1, fornecedores);

            listaFornecedores.setAdapter(adapter);

        }
        catch (SQLException e){

            Toast.makeText(FornecedoresActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

        }

        // OnCLickListiner For List Items
        listaFornecedores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

                Intent editar = new Intent(FornecedoresActivity.this, FornecedoresEditDelActivity.class);
                fornecedorSelecionado = (Fornecedor) listaFornecedores.getItemAtPosition(position);
                editar.putExtra("fornecedorSelecionado", fornecedorSelecionado);
                startActivity(editar);
            }
        });

    }

    @Override
    //cria o menu
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_fornecedores, menu);

        return true;

    }

    @Override
    //quando um item do menu for selecionado
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_novoFornecedor){

             Intent it = new Intent(this,FornecedoresCadActivity.class);
             startActivity(it);

        }

        return super.onOptionsItemSelected(item);

    }


}
