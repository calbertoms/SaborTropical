package com.alberto.carlos.sabortropical.Telas.Pedido;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.ClienteDao;
import com.alberto.carlos.sabortropical.Dao.FornecedorDao;
import com.alberto.carlos.sabortropical.Dao.ProdutoDao;
import com.alberto.carlos.sabortropical.Dao.UsuarioDao;
import com.alberto.carlos.sabortropical.Entidades.Armazenamento;
import com.alberto.carlos.sabortropical.Entidades.Cliente;
import com.alberto.carlos.sabortropical.Entidades.Fornecedor;
import com.alberto.carlos.sabortropical.Entidades.Item;
import com.alberto.carlos.sabortropical.Entidades.Pedido;
import com.alberto.carlos.sabortropical.Entidades.Produto;
import com.alberto.carlos.sabortropical.Entidades.Usuario;
import com.alberto.carlos.sabortropical.R;
import com.alberto.carlos.sabortropical.Telas.Armazenamento.ArmazenamentosActivity;
import com.alberto.carlos.sabortropical.Telas.Armazenamento.ArmazenamentosEditDelActivity;
import com.alberto.carlos.sabortropical.Telas.MainActivity;

import java.util.List;

public class CompraActivity extends AppCompatActivity {

    Produto produtoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        Database databaseUsuario;
        SQLiteDatabase conn;
        databaseUsuario = new Database(CompraActivity.this);
        conn = databaseUsuario.getReadableDatabase();
        UsuarioDao dao = new UsuarioDao(conn);
        final List<Usuario> usuarios = dao.listarUsuarios();
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>
                (CompraActivity.this, android.R.layout.simple_spinner_dropdown_item, usuarios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner usuario = (Spinner) findViewById(R.id.campo_usuario);
        usuario.setAdapter(adapter);

        Database databaseProduto;
        SQLiteDatabase conn2;
        databaseProduto = new Database(CompraActivity.this);
        conn2 = databaseProduto.getReadableDatabase();
        ProdutoDao dao2 = new ProdutoDao(conn2);
        final List<Produto> produtos = dao2.listarProduto();
        ArrayAdapter<Produto> adapter2 = new ArrayAdapter<Produto>
                (CompraActivity.this, android.R.layout.simple_spinner_dropdown_item, produtos);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner produto = (Spinner) findViewById(R.id.campo_produto);
        produto.setAdapter(adapter2);

        final EditText dataVenda = (EditText) findViewById(R.id.campo_dataVenda);

        final EditText dataVencimento = (EditText) findViewById(R.id.campo_dataVencimento);

        final EditText valorTotal = (EditText) findViewById(R.id.campo_precoTotal);
        valorTotal.setText(Float.toString(produtos.get(produto.getSelectedItemPosition()).getPrecoVenda()));

        final EditText desconto = (EditText) findViewById(R.id.campo_desconto);

        final String[] strCondPag = new String[]{"Dinheiro","Cartão","Cheque"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, strCondPag);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner condPag = (Spinner) findViewById(R.id.campo_condicaoPagamento);
        condPag.setAdapter(adapter3);

        final String[] strStatus= new String[]{"Sim","Não","Parcial"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, strStatus);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner status = (Spinner) findViewById(R.id.campo_status);
        status.setAdapter(adapter4);

        final EditText observacao = (EditText) findViewById(R.id.campo_observacao);

        Button buttonSalvar = (Button) findViewById(R.id.botao_salvar);

        produto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                produtoSelecionado = (Produto) produto.getItemAtPosition(position);
                valorTotal.setText(Float.toString(produtoSelecionado.getPrecoVenda()));
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        buttonSalvar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                boolean verificador = testarCampoVazio();
                long idPedido;

                if(!verificador) {

                    try {

                        Item item = new Item();
                        item.setProduto(produtos.get(produto.getSelectedItemPosition()));
                        item.setQuantidade(1);
                        item.setValorUnitario(produtos.get(produto.getSelectedItemPosition()).getPrecoVenda());
                        Pedido pedido = new Pedido();
                        pedido.setUsuario(usuarios.get(usuario.getSelectedItemPosition()));
                        pedido.setData(dataVenda.getText().toString());
                        pedido.setVencimento(dataVencimento.getText().toString());
                        pedido.setPrecoTotal(Float.parseFloat(valorTotal.getText().toString()));
                        pedido.setDesconto(Float.parseFloat(desconto.getText().toString()));
                        pedido.setCondPag(condPag.getSelectedItemPosition());
                        pedido.setStatus(status.getSelectedItemPosition());
                        pedido.setObservacao(observacao.getText().toString());
                        pedido.setItem(item);
                        idPedido = pedido.Comprar(CompraActivity.this);


                        Toast.makeText(CompraActivity.this, "Pedido Nº " + idPedido + " realizado com sucesso", Toast.LENGTH_SHORT).show();

                        Intent it = new Intent(CompraActivity.this,MainActivity.class);
                        startActivity(it);


                    }
                    catch (SQLException e){

                        Toast.makeText(CompraActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

                    }


                }

            }


            private boolean testarCampoVazio() {

                if(TextUtils.isEmpty(dataVenda.getText().toString())) {
                    Toast.makeText(CompraActivity.this, "Campo Data de Venda esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(dataVencimento.getText().toString())) {
                    Toast.makeText(CompraActivity.this, "Campo Data de Vencimento esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(valorTotal.getText().toString())) {
                    Toast.makeText(CompraActivity.this, "Campo Valor Total esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(desconto.getText().toString())) {
                    Toast.makeText(CompraActivity.this, "Campo Desconto esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(observacao.getText().toString())) {
                    Toast.makeText(CompraActivity.this, "Campo Observação esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }


                return false;

            }


        });


    }

}
