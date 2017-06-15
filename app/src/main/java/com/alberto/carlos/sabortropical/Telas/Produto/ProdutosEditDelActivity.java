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

public class ProdutosEditDelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_edit_del);

        final Produto produtoDetalhado = (Produto) getIntent().getSerializableExtra("produtoSelecionado");

        final EditText nome = (EditText) findViewById(R.id.campo_nome);


        Fornecedor fornecedor = new Fornecedor();
        final List<Fornecedor> fornecedores = fornecedor.Visualizar(ProdutosEditDelActivity.this);
        ArrayAdapter<Fornecedor> adapter = new ArrayAdapter<Fornecedor>
                (ProdutosEditDelActivity.this, android.R.layout.simple_spinner_dropdown_item, fornecedores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner fornecedorSpinner = (Spinner) findViewById(R.id.campo_fornecedor);
        fornecedorSpinner.setAdapter(adapter);

        Armazenamento armazenamento = new Armazenamento();
        final List<Armazenamento> armazenamentos = armazenamento.Visualizar(ProdutosEditDelActivity.this);
        ArrayAdapter<Armazenamento> adapter2 = new ArrayAdapter<Armazenamento>
                (ProdutosEditDelActivity.this, android.R.layout.simple_spinner_dropdown_item, armazenamentos);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner armazenamentoSpinner = (Spinner) findViewById(R.id.campo_armazenamento);
        armazenamentoSpinner.setAdapter(adapter2);

        final EditText tipo = (EditText) findViewById(R.id.campo_tipo);

        final EditText categoria = (EditText) findViewById(R.id.campo_categoria);

        final EditText temperaturaArmazenamento = (EditText) findViewById(R.id.campo_temperaturaArmazenamento);

        final EditText temperaturaTolerancia = (EditText) findViewById(R.id.campo_temperaturaTolerancia);

        final EditText maximoEmpilhamento = (EditText) findViewById(R.id.campo_maximoEmpilhamento);

        final EditText dataFabricacao = (EditText) findViewById(R.id.campo_dataFabricacao);

        final EditText dataValidade = (EditText) findViewById(R.id.campo_dataValidade);

        final EditText precoCompra = (EditText) findViewById(R.id.campo_precoCompra);

        final EditText precoVenda = (EditText) findViewById(R.id.campo_precoVenda);

        Button buttonEditar = (Button) findViewById(R.id.botao_editar);
        Button buttonDeletar = (Button) findViewById(R.id.botao_deletar);


        nome.setText(produtoDetalhado.getNome());
        //fornecedor.setSelection((int) produtoDetalhado.getFornecedor().getId());
        //armazenamento.setSelection((int) produtoDetalhado.getArmazenamento().getId());
        tipo.setText(produtoDetalhado.getTipo());
        categoria.setText(produtoDetalhado.getCategoria());
        temperaturaArmazenamento.setText(Integer.toString(produtoDetalhado.getTemperaturaArmazenagem()));
        temperaturaTolerancia.setText(Integer.toString(produtoDetalhado.getTemperaturaTolerancia()));
        maximoEmpilhamento.setText(Integer.toString(produtoDetalhado.getMaximoEmpilhamento()));
        dataFabricacao.setText(produtoDetalhado.getDataFabricacao());
        dataValidade.setText(produtoDetalhado.getDataValidade());
        precoCompra.setText(Float.toString(produtoDetalhado.getPrecoCompra()));
        precoVenda.setText(Float.toString(produtoDetalhado.getPrecoVenda()));

        buttonEditar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                boolean verificador = testarCampoVazio();
                int qtdAtualizadas;

                if(!verificador) {

                    try {
                        Produto produto = new Produto();
                        produto.setId(produtoDetalhado.getId());
                        produto.setNome(nome.getText().toString());
                        produto.setArmazenamento(armazenamentos.get(armazenamentoSpinner.getSelectedItemPosition()));
                        produto.setFornecedor(fornecedores.get(fornecedorSpinner.getSelectedItemPosition()));
                        produto.setTipo(tipo.getText().toString());
                        produto.setCategoria(categoria.getText().toString());
                        produto.setTemperaturaArmazenagem(Integer.parseInt(temperaturaArmazenamento.getText().toString()));
                        produto.setTemperaturaTolerancia(Integer.parseInt(temperaturaTolerancia.getText().toString()));
                        produto.setMaximoEmpilhamento(Integer.parseInt(maximoEmpilhamento.getText().toString()));
                        produto.setDataFabricacao(dataFabricacao.getText().toString());
                        produto.setDataValidade(dataValidade.getText().toString());
                        produto.setPrecoCompra(Float.parseFloat(precoCompra.getText().toString()));
                        produto.setPrecoVenda(Float.parseFloat(precoVenda.getText().toString()));
                        qtdAtualizadas = produto.Editar(ProdutosEditDelActivity.this);

                        Toast.makeText(ProdutosEditDelActivity.this, "Foram atualizados " + qtdAtualizadas + " campos", Toast.LENGTH_SHORT).show();

                        Intent it = new Intent(ProdutosEditDelActivity.this,ProdutosActivity.class);
                        startActivity(it);


                    }
                    catch (SQLException e){

                        Toast.makeText(ProdutosEditDelActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

                    }


                }

            }


            private boolean testarCampoVazio() {

                if(TextUtils.isEmpty(nome.getText().toString())) {
                    Toast.makeText(ProdutosEditDelActivity.this, "Campo Nome esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(tipo.getText().toString())) {
                    Toast.makeText(ProdutosEditDelActivity.this, "Campo Tipo esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(categoria.getText().toString())) {
                    Toast.makeText(ProdutosEditDelActivity.this, "Campo Categoria esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(temperaturaArmazenamento.getText().toString())) {
                    Toast.makeText(ProdutosEditDelActivity.this, "Campo Temperatura Armazenamento esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(temperaturaTolerancia.getText().toString())) {
                    Toast.makeText(ProdutosEditDelActivity.this, "Campo Temperatura Tolerância esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(maximoEmpilhamento.getText().toString())) {
                    Toast.makeText(ProdutosEditDelActivity.this, "Campo Máximo Empilhamento esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(dataFabricacao.getText().toString())) {
                    Toast.makeText(ProdutosEditDelActivity.this, "Campo Data de Fabricação esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(dataValidade.getText().toString())) {
                    Toast.makeText(ProdutosEditDelActivity.this, "Campo Data de Validade esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(precoCompra.getText().toString())) {
                    Toast.makeText(ProdutosEditDelActivity.this, "Campo Preço de Compra esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(precoVenda.getText().toString())) {
                    Toast.makeText(ProdutosEditDelActivity.this, "Campo Preço de Venda esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;

            }


        });

        buttonDeletar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                try {
                    Produto produto = new Produto();
                    produto.Deletar(ProdutosEditDelActivity.this,produtoDetalhado.getId());

                    Toast.makeText(ProdutosEditDelActivity.this, "Produto deletado com sucesso.", Toast.LENGTH_SHORT).show();

                    Intent it = new Intent(ProdutosEditDelActivity.this,ProdutosActivity.class);
                    startActivity(it);


                }
                catch (SQLException e){

                    Toast.makeText(ProdutosEditDelActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

                }

            }


        });


    }

}
