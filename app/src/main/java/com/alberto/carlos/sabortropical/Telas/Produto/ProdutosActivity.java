package com.alberto.carlos.sabortropical.Telas.Produto;

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
import com.alberto.carlos.sabortropical.Dao.ProdutoDao;
import com.alberto.carlos.sabortropical.Entidades.Produto;
import com.alberto.carlos.sabortropical.R;

import java.util.List;

//classe principal
public class ProdutosActivity extends AppCompatActivity {

    private ListView listaProdutos;
    Produto produtoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        listaProdutos = (ListView) findViewById(R.id.lista_de_produtos);

        registerForContextMenu(listaProdutos);

        try {
            Produto produto = new Produto();
            List<Produto> produtos = produto.Visualizar(ProdutosActivity.this);
            ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>
                    (ProdutosActivity.this, android.R.layout.simple_list_item_1, produtos);

            listaProdutos.setAdapter(adapter);

        }
        catch (SQLException e){

            Toast.makeText(ProdutosActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

        }

        // OnCLickListiner For List Items
        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

                Intent editar = new Intent(ProdutosActivity.this, ProdutosEditDelActivity.class);
                produtoSelecionado = (Produto) listaProdutos.getItemAtPosition(position);
                editar.putExtra("produtoSelecionado", produtoSelecionado);
                startActivity(editar);
            }
        });

    }

    @Override
    //cria o menu
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_produtos, menu);

        return true;

    }

    @Override
    //quando um item do menu for selecionado
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_novoProduto){

             Intent it = new Intent(this,ProdutosCadActivity.class);
             startActivity(it);

        }

        return super.onOptionsItemSelected(item);

    }


}
