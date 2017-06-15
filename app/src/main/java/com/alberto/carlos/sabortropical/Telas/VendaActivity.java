package com.alberto.carlos.sabortropical.Telas;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.ClienteDao;
import com.alberto.carlos.sabortropical.Dao.ProdutoDao;
import com.alberto.carlos.sabortropical.Entidades.Cliente;
import com.alberto.carlos.sabortropical.Entidades.Produto;
import com.alberto.carlos.sabortropical.R;

import java.util.List;

public class VendaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_cad);

        final EditText nome = (EditText) findViewById(R.id.campo_nome);

        Database databaseCliente;
        SQLiteDatabase conn;
        databaseCliente = new Database(VendaActivity.this);
        conn = databaseCliente.getReadableDatabase();
        ClienteDao dao = new ClienteDao(conn);
        final List<Cliente> clientes = dao.listarClientes();
        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>
                (VendaActivity.this, android.R.layout.simple_spinner_dropdown_item, clientes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner cliente = (Spinner) findViewById(R.id.campo_cliente);
        cliente.setAdapter(adapter);

        Database databaseProduto;
        SQLiteDatabase conn2;
        databaseProduto = new Database(VendaActivity.this);
        conn2 = databaseProduto.getReadableDatabase();
        ProdutoDao dao2 = new ProdutoDao(conn2);
        final List<Produto> produtos = dao2.listarProduto();
        ArrayAdapter<Produto> adapter2 = new ArrayAdapter<Produto>
                (VendaActivity.this, android.R.layout.simple_spinner_dropdown_item, produtos);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner produto = (Spinner) findViewById(R.id.campo_produto);
        produto.setAdapter(adapter2);

        final EditText dataVenda = (EditText) findViewById(R.id.campo_dataVenda);

        final EditText dataVencimento = (EditText) findViewById(R.id.campo_dataVencimento);


    }

}
