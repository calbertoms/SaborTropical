package com.alberto.carlos.sabortropical.Telas.Produto;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.ArmazenamentoDao;
import com.alberto.carlos.sabortropical.Dao.FornecedorDao;
import com.alberto.carlos.sabortropical.Dao.ProdutoDao;
import com.alberto.carlos.sabortropical.Entidades.Armazenamento;
import com.alberto.carlos.sabortropical.Entidades.Fornecedor;
import com.alberto.carlos.sabortropical.Entidades.Produto;
import com.alberto.carlos.sabortropical.R;

import java.util.List;

public class ProdutosCadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_cad);

        final EditText nome = (EditText) findViewById(R.id.campo_nome);

        Database databaseFornecedor;
        SQLiteDatabase conn;
        databaseFornecedor = new Database(ProdutosCadActivity.this);
        conn = databaseFornecedor.getReadableDatabase();
        FornecedorDao dao = new FornecedorDao(conn);
        final List<Fornecedor> fornecedores = dao.listarFornecedor();
        ArrayAdapter<Fornecedor> adapter = new ArrayAdapter<Fornecedor>
                (ProdutosCadActivity.this, android.R.layout.simple_spinner_dropdown_item, fornecedores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner fornecedor = (Spinner) findViewById(R.id.campo_fornecedor);
        fornecedor.setAdapter(adapter);

        Database databaseArmazenamento;
        SQLiteDatabase conn2;
        databaseArmazenamento = new Database(ProdutosCadActivity.this);
        conn2 = databaseArmazenamento.getReadableDatabase();
        ArmazenamentoDao dao2 = new ArmazenamentoDao(conn2);
        final List<Armazenamento> armazenamentos = dao2.listarArmazenamento();
        ArrayAdapter<Armazenamento> adapter2 = new ArrayAdapter<Armazenamento>
                (ProdutosCadActivity.this, android.R.layout.simple_spinner_dropdown_item, armazenamentos);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner armazenamento = (Spinner) findViewById(R.id.campo_armazenamento);
        armazenamento.setAdapter(adapter2);

        final EditText tipo = (EditText) findViewById(R.id.campo_tipo);

        final EditText categoria = (EditText) findViewById(R.id.campo_categoria);

        final EditText temperaturaArmazenamento = (EditText) findViewById(R.id.campo_temperaturaArmazenamento);

        final EditText temperaturaTolerancia = (EditText) findViewById(R.id.campo_temperaturaTolerancia);

        final EditText maximoEmpilhamento = (EditText) findViewById(R.id.campo_maximoEmpilhamento);

        final EditText dataFabricacao = (EditText) findViewById(R.id.campo_dataFabricacao);

        final EditText dataValidade = (EditText) findViewById(R.id.campo_dataValidade);

        final EditText precoCompra = (EditText) findViewById(R.id.campo_precoCompra);

        final EditText precoVenda = (EditText) findViewById(R.id.campo_precoVenda);

        Button buttonSalvar = (Button) findViewById(R.id.botao_salvar);


        buttonSalvar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                boolean verificador = testarCampoVazio();
                long idProduto;

                if(!verificador) {

                    try {
                        Produto produto = new Produto();
                        produto.setNome(nome.getText().toString());
                        produto.setArmazenamento(armazenamentos.get(armazenamento.getSelectedItemPosition()));
                        produto.setFornecedor(fornecedores.get(fornecedor.getSelectedItemPosition()));
                        produto.setTipo(tipo.getText().toString());
                        produto.setCategoria(categoria.getText().toString());
                        produto.setTemperaturaArmazenagem(Integer.parseInt(temperaturaArmazenamento.getText().toString()));
                        produto.setTemperaturaTolerancia(Integer.parseInt(temperaturaTolerancia.getText().toString()));
                        produto.setMaximoEmpilhamento(Integer.parseInt(maximoEmpilhamento.getText().toString()));
                        produto.setDataFabricacao(dataFabricacao.getText().toString());
                        produto.setDataValidade(dataValidade.getText().toString());
                        produto.setPrecoCompra(Float.parseFloat(precoCompra.getText().toString()));
                        produto.setPrecoVenda(Float.parseFloat(precoVenda.getText().toString()));
                        idProduto = produto.Cadastrar(ProdutosCadActivity.this);

                        Toast.makeText(ProdutosCadActivity.this, "Produto Nº " + idProduto + " cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                        Intent it = new Intent(ProdutosCadActivity.this,ProdutosActivity.class);
                        startActivity(it);


                    }
                    catch (SQLException e){

                        Toast.makeText(ProdutosCadActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

                    }


                }

            }


            private boolean testarCampoVazio() {

                if(TextUtils.isEmpty(nome.getText().toString())) {
                    Toast.makeText(ProdutosCadActivity.this, "Campo Nome esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(tipo.getText().toString())) {
                    Toast.makeText(ProdutosCadActivity.this, "Campo Tipo esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(categoria.getText().toString())) {
                    Toast.makeText(ProdutosCadActivity.this, "Campo Categoria esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(temperaturaArmazenamento.getText().toString())) {
                    Toast.makeText(ProdutosCadActivity.this, "Campo Temperatura Armazenamento esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(temperaturaTolerancia.getText().toString())) {
                    Toast.makeText(ProdutosCadActivity.this, "Campo Temperatura Tolerância esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(maximoEmpilhamento.getText().toString())) {
                    Toast.makeText(ProdutosCadActivity.this, "Campo Máximo Empilhamento esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(dataFabricacao.getText().toString())) {
                    Toast.makeText(ProdutosCadActivity.this, "Campo Data de Fabricação esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(dataValidade.getText().toString())) {
                    Toast.makeText(ProdutosCadActivity.this, "Campo Data de Validade esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(precoCompra.getText().toString())) {
                    Toast.makeText(ProdutosCadActivity.this, "Campo Preço de Compra esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(precoVenda.getText().toString())) {
                    Toast.makeText(ProdutosCadActivity.this, "Campo Preço de Venda esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;

            }


        });

    }

}
