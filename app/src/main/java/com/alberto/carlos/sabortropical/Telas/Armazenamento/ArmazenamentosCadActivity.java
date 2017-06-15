package com.alberto.carlos.sabortropical.Telas.Armazenamento;

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
import com.alberto.carlos.sabortropical.Entidades.Armazenamento;
import com.alberto.carlos.sabortropical.R;

public class ArmazenamentosCadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armazenamentos_cad);

        final EditText nome = (EditText) findViewById(R.id.campo_nome);

        final EditText tamanhoExterno = (EditText) findViewById(R.id.campo_tamanhoExterno);

        final EditText areaUtil = (EditText) findViewById(R.id.campo_area);

        final String[] strEstadoConservacao = new String[]{"Bom","Regular","Ruim"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, strEstadoConservacao);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner estadoConservacao = (Spinner) findViewById(R.id.campo_estadoConservacao);
        estadoConservacao.setAdapter(adapter);

        final EditText cor = (EditText) findViewById(R.id.campo_cor);

        final EditText patrocinio = (EditText) findViewById(R.id.campo_patrocinio);

        final EditText dataFabricacao = (EditText) findViewById(R.id.campo_dataFabricacao);

        final EditText peso = (EditText) findViewById(R.id.campo_peso);

        final EditText dataValidade = (EditText) findViewById(R.id.campo_dataValidade);

        Button buttonSalvar = (Button) findViewById(R.id.botao_salvar);


        buttonSalvar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                boolean verificador = testarCampoVazio();
                Database databaseArmazenamento;
                SQLiteDatabase conn;
                long idArmazenamento;

                if(!verificador) {

                    try {
                        databaseArmazenamento = new Database(ArmazenamentosCadActivity.this);
                        conn = databaseArmazenamento.getWritableDatabase();
                        ArmazenamentoDao dao = new ArmazenamentoDao(conn);
                        Armazenamento armazenamento = new Armazenamento();
                        armazenamento.setNome(nome.getText().toString());
                        armazenamento.setTamanhoExterno(Integer.parseInt(tamanhoExterno.getText().toString()));
                        armazenamento.setAreaUtil(Float.parseFloat(areaUtil.getText().toString()));
                        armazenamento.setEstadoConservacao(estadoConservacao.getSelectedItemPosition());
                        armazenamento.setCor(cor.getText().toString());
                        armazenamento.setPatrocinio(patrocinio.getText().toString());
                        armazenamento.setDataFabricacao(dataFabricacao.getText().toString());
                        armazenamento.setPeso(Float.parseFloat(peso.getText().toString()));
                        armazenamento.setDataValidade(dataValidade.getText().toString());
                        idArmazenamento = dao.inserir(armazenamento);
                        dao.fechar();

                        Toast.makeText(ArmazenamentosCadActivity.this, "Armazenamento Nº " + idArmazenamento + " cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                        Intent it = new Intent(ArmazenamentosCadActivity.this,ArmazenamentosActivity.class);
                        startActivity(it);


                    }
                    catch (SQLException e){

                        Toast.makeText(ArmazenamentosCadActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

                    }


                }

            }


            private boolean testarCampoVazio() {

                if(TextUtils.isEmpty(nome.getText().toString())) {
                    Toast.makeText(ArmazenamentosCadActivity.this, "Campo Nome esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(tamanhoExterno.getText().toString())) {
                    Toast.makeText(ArmazenamentosCadActivity.this, "Campo Tamanho esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(areaUtil.getText().toString())) {
                    Toast.makeText(ArmazenamentosCadActivity.this, "Campo Area esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(cor.getText().toString())) {
                    Toast.makeText(ArmazenamentosCadActivity.this, "Campo Cor esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(patrocinio.getText().toString())) {
                    Toast.makeText(ArmazenamentosCadActivity.this, "Campo Patrocínio esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(dataFabricacao.getText().toString())) {
                    Toast.makeText(ArmazenamentosCadActivity.this, "Campo Data de Fabricação esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(peso.getText().toString())) {
                    Toast.makeText(ArmazenamentosCadActivity.this, "Campo Peso esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(dataValidade.getText().toString())) {
                    Toast.makeText(ArmazenamentosCadActivity.this, "Campo Data de Validade esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;

            }


        });

    }

}
