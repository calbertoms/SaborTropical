package com.alberto.carlos.sabortropical.Telas.Fornecedor;

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
import com.alberto.carlos.sabortropical.Dao.FornecedorDao;
import com.alberto.carlos.sabortropical.Entidades.Endereco;
import com.alberto.carlos.sabortropical.Entidades.Fornecedor;
import com.alberto.carlos.sabortropical.R;

public class FornecedoresCadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedores_cad);

        final EditText contrato = (EditText) findViewById(R.id.campo_contrato);

        final EditText nome = (EditText) findViewById(R.id.campo_nome);

        final String[] strStatus = new String[]{"Ativo","Desativo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, strStatus);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner status = (Spinner) findViewById(R.id.campo_status);
        status.setAdapter(adapter);

        final EditText responsavel = (EditText) findViewById(R.id.campo_responsavel);

        final EditText cnpj = (EditText) findViewById(R.id.campo_cnpj);

        final EditText inscEstadual = (EditText) findViewById(R.id.campo_inscEstadual);

        final EditText credito = (EditText) findViewById(R.id.campo_credito);

        final EditText banco = (EditText) findViewById(R.id.campo_banco);

        final EditText agencia = (EditText) findViewById(R.id.campo_agencia);

        final EditText contaCorrente = (EditText) findViewById(R.id.campo_contaCorrente);

        final String[] strTipoPagamento = new String[]{"Dinheiro","Cartão","Cheque"};
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, strTipoPagamento);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner tipoPagamento = (Spinner) findViewById(R.id.campo_tipoPagamento);
        tipoPagamento.setAdapter(adapter);

        final EditText logradouro = (EditText) findViewById(R.id.campo_logradouro);

        final EditText numero = (EditText) findViewById(R.id.campo_numero);

        final EditText bairro = (EditText) findViewById(R.id.campo_bairro);

        final EditText cidade = (EditText) findViewById(R.id.campo_cidade);

        final EditText uf = (EditText) findViewById(R.id.campo_uf);

        final EditText pais = (EditText) findViewById(R.id.campo_pais);

        final EditText pontoReferencia = (EditText) findViewById(R.id.campo_pontoReferencia);

        final EditText cep = (EditText) findViewById(R.id.campo_cep);

        Button buttonSalvar = (Button) findViewById(R.id.botao_salvar);


        buttonSalvar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                boolean verificador = testarCampoVazio();
                long idFornecedor;

                if(!verificador) {

                    try {
                        Fornecedor fornecedor = new Fornecedor();
                        Endereco endereco = new Endereco();
                        fornecedor.setContrato(Integer.parseInt(contrato.getText().toString()));
                        fornecedor.setNome(nome.getText().toString());
                        fornecedor.setStatus(status.getSelectedItemPosition());
                        fornecedor.setNomeResponsavel(responsavel.getText().toString());
                        fornecedor.setCnpj(cnpj.getText().toString());
                        fornecedor.setInscEstadual(inscEstadual.getText().toString());
                        fornecedor.setCredito(Float.parseFloat(credito.getText().toString()));
                        fornecedor.setBanco(banco.getText().toString());
                        fornecedor.setAgencia(agencia.getText().toString());
                        fornecedor.setContaCorrente(contaCorrente.getText().toString());
                        fornecedor.setTipoPagamento(tipoPagamento.getSelectedItemPosition());
                        fornecedor.setDesempenho(0);
                        endereco.setLogradouro(logradouro.getText().toString());
                        endereco.setNumero(Integer.parseInt(numero.getText().toString()));
                        endereco.setBairro(bairro.getText().toString());
                        endereco.setCidade(cidade.getText().toString());
                        endereco.setUf(uf.getText().toString());
                        endereco.setPais(pais.getText().toString());
                        endereco.setPontoreferencia(pontoReferencia.getText().toString());
                        endereco.setCep(cep.getText().toString());
                        fornecedor.setEndereco(endereco);
                        idFornecedor = fornecedor.Cadastrar(FornecedoresCadActivity.this);

                        Toast.makeText(FornecedoresCadActivity.this, "Fornecedor Nº " + idFornecedor + " cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                        Intent it = new Intent(FornecedoresCadActivity.this,FornecedoresActivity.class);
                        startActivity(it);


                    }
                    catch (SQLException e){

                        Toast.makeText(FornecedoresCadActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

                    }


                }

            }


            private boolean testarCampoVazio() {

                if(TextUtils.isEmpty(contrato.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Contrato esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(nome.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Nome esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(responsavel.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Responsavel esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(cnpj.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Cnpj esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(inscEstadual.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Inscrição Estadual esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(credito.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Crédito esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(banco.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Banco esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(logradouro.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Logradouro esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(numero.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Numero esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(bairro.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Bairro esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(cidade.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Cidade esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(uf.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Uf esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(pais.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo País esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(pontoReferencia.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Ponto de Referencia esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(cep.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Cep esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;

            }


        });

    }

}
