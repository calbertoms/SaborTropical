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

import com.alberto.carlos.sabortropical.BancoDeDados.DatabaseCliente;
import com.alberto.carlos.sabortropical.Dao.ClienteDao;
import com.alberto.carlos.sabortropical.Entidades.Cliente;
import com.alberto.carlos.sabortropical.Entidades.Endereco;
import com.alberto.carlos.sabortropical.R;
import com.alberto.carlos.sabortropical.utilitarios.Mask;

public class FornecedoresCadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_cad);

        final EditText nome = (EditText) findViewById(R.id.campo_nome);

        final EditText sobreNome = (EditText) findViewById(R.id.campo_sobreNome);

        final EditText dataNascimento = (EditText) findViewById(R.id.campo_dataNascimento);

        final String[] strCorPele = new String[]{"Branco","Moreno","Negro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, strCorPele);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner corPele = (Spinner) findViewById(R.id.campo_corPele);
        corPele.setAdapter(adapter);

        final String[] strCorOlhos = new String[]{"Azul","Verde","Castanho Claro","Castanho Escuro"};
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, strCorOlhos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner corOlhos = (Spinner) findViewById(R.id.campo_corOlhos);
        corOlhos.setAdapter(adapter);

        final String[] strSexo = new String[]{"Feminino","Masculino","Outros"};
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, strSexo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner sexo = (Spinner) findViewById(R.id.campo_sexo);
        sexo.setAdapter(adapter);

        final EditText nomePai = (EditText) findViewById(R.id.campo_nomePai);

        final EditText nomeMae = (EditText) findViewById(R.id.campo_nomeMae);

        final String[] strEstadoCivil = new String[]{"Solteiro","Casado","Viúvo"};
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, strEstadoCivil);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner estadoCivil = (Spinner) findViewById(R.id.campo_estadoCivil);
        estadoCivil.setAdapter(adapter);

        final EditText cpf = (EditText) findViewById(R.id.campo_cpf);
        cpf.addTextChangedListener(Mask.insert("###.###.###-##", cpf));

        final EditText identidade = (EditText) findViewById(R.id.campo_identidade);
        identidade.addTextChangedListener(Mask.insert("#.###-###", identidade));

        final EditText logradouro = (EditText) findViewById(R.id.campo_logradouro);

        final EditText numero = (EditText) findViewById(R.id.campo_numero);

        final EditText bairro = (EditText) findViewById(R.id.campo_bairro);

        final EditText cidade = (EditText) findViewById(R.id.campo_cidade);

        final EditText uf = (EditText) findViewById(R.id.campo_uf);

        final EditText pais = (EditText) findViewById(R.id.campo_pais);

        final EditText pontoReferencia = (EditText) findViewById(R.id.campo_pontoReferencia);

        final EditText cep = (EditText) findViewById(R.id.campo_cep);

        final EditText regiao = (EditText) findViewById(R.id.campo_regiao);

        Button buttonSalvar = (Button) findViewById(R.id.botao_salvar);


        buttonSalvar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                boolean verificador = testarCampoVazio();
                DatabaseCliente databaseCliente;
                SQLiteDatabase conn;
                long idcliente;

                if(!verificador) {

                    try {
                        databaseCliente = new DatabaseCliente(FornecedoresCadActivity.this);
                        conn = databaseCliente.getWritableDatabase();
                        ClienteDao dao = new ClienteDao(conn);
                        Cliente cliente = new Cliente();
                        Endereco endereco = new Endereco();
                        cliente.setNome(nome.getText().toString());
                        cliente.setSobreNome(sobreNome.getText().toString());
                        cliente.setDataNascimento(dataNascimento.getText().toString());
                        cliente.setCorPele(corPele.getSelectedItemPosition());
                        cliente.setCorOlhos(corOlhos.getSelectedItemPosition());
                        cliente.setSexo(sexo.getSelectedItemPosition());
                        cliente.setNomePai(nomePai.getText().toString());
                        cliente.setNomeMae(nomeMae.getText().toString());
                        cliente.setEstadoCivil(estadoCivil.getSelectedItemPosition());
                        cliente.setCpf(cpf.getText().toString());
                        cliente.setIdentidade(identidade.getText().toString());
                        cliente.setRegiao(regiao.getText().toString());
                        cliente.setPontos(0);
                        endereco.setLogradouro(logradouro.getText().toString());
                        endereco.setNumero(Integer.parseInt(numero.getText().toString()));
                        endereco.setBairro(bairro.getText().toString());
                        endereco.setCidade(cidade.getText().toString());
                        endereco.setUf(uf.getText().toString());
                        endereco.setPais(pais.getText().toString());
                        endereco.setPontoreferencia(pontoReferencia.getText().toString());
                        endereco.setCep(cep.getText().toString());
                        cliente.setEndereco(endereco);
                        idcliente = dao.inserir(cliente);
                        dao.fechar();

                        Toast.makeText(FornecedoresCadActivity.this, "Cliente Nº " + idcliente + " cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                        Intent it = new Intent(FornecedoresCadActivity.this,FornecedoresActivity.class);
                        startActivity(it);


                    }
                    catch (SQLException e){

                        Toast.makeText(FornecedoresCadActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

                    }


                }

            }


            private boolean testarCampoVazio() {

                if(TextUtils.isEmpty(nome.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Nome esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(sobreNome.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Sobrenome esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(dataNascimento.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Data de Nascimento esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(nomePai.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo nome do Pai esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(nomeMae.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo nome da Mãe esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(cpf.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo cpf esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(identidade.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Identidade esta vazio.", Toast.LENGTH_SHORT).show();
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

                if(TextUtils.isEmpty(regiao.getText().toString())) {
                    Toast.makeText(FornecedoresCadActivity.this, "Campo Região esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;

            }


        });

    }

}
