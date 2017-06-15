package com.alberto.carlos.sabortropical.Telas;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.ProdutoDao;
import com.alberto.carlos.sabortropical.Entidades.Produto;
import com.alberto.carlos.sabortropical.R;
import com.alberto.carlos.sabortropical.Telas.Armazenamento.ArmazenamentosActivity;
import com.alberto.carlos.sabortropical.Telas.Cliente.ClientesActivity;
import com.alberto.carlos.sabortropical.Telas.Fornecedor.FornecedoresActivity;
import com.alberto.carlos.sabortropical.Telas.Produto.ProdutosActivity;
import com.alberto.carlos.sabortropical.Telas.Usuario.UsuariosActivity;

import java.util.List;


//classe principal
public class MainActivity extends AppCompatActivity {

    private ListView listaProdutos;
    private Database Database;
    private SQLiteDatabase conn;

    @Override
    //cria o layout
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaProdutos = (ListView) findViewById(R.id.lista_de_produtos);

        registerForContextMenu(listaProdutos);

        try {
            Database = new Database(MainActivity.this);
            conn = Database.getReadableDatabase();
            ProdutoDao dao = new ProdutoDao(conn);
            List<Produto> produtos = dao.listarProduto();
            ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>
                    (MainActivity.this, android.R.layout.simple_list_item_1, produtos);

            listaProdutos.setAdapter(adapter);

        }
        catch (SQLException e){

            Toast.makeText(MainActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

        }



    }

    @Override
    //cria o menu
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

    }

    @Override
    //quando um item do menu for selecionado
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_cliente){

            Intent it = new Intent(this,ClientesActivity.class);
            startActivity(it);

        }
        else if (id == R.id.action_usuario){

            Intent it = new Intent(this,UsuariosActivity.class);
            startActivity(it);

        }
        else if (id == R.id.action_fornecedor){

            Intent it = new Intent(this, FornecedoresActivity.class);
            startActivity(it);

        }
        else if (id == R.id.action_armazenamento){

            Intent it = new Intent(this, ArmazenamentosActivity.class);
            startActivity(it);

        }
        else if (id == R.id.action_produto){

            Intent it = new Intent(this, ProdutosActivity.class);
            startActivity(it);

        }
        else if (id == R.id.action_sair){

            finish();
            Toast.makeText(MainActivity.this,"Até Logo.",Toast.LENGTH_LONG).show();

        }

        return super.onOptionsItemSelected(item);

    }


}
