package com.alberto.carlos.sabortropical.Telas.Relatorio;

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
import com.alberto.carlos.sabortropical.Dao.ProdutoDao;
import com.alberto.carlos.sabortropical.Dao.UsuarioDao;
import com.alberto.carlos.sabortropical.Entidades.Cliente;
import com.alberto.carlos.sabortropical.Entidades.Item;
import com.alberto.carlos.sabortropical.Entidades.Pedido;
import com.alberto.carlos.sabortropical.Entidades.Produto;
import com.alberto.carlos.sabortropical.Entidades.Relatorio;
import com.alberto.carlos.sabortropical.Entidades.Usuario;
import com.alberto.carlos.sabortropical.R;
import com.alberto.carlos.sabortropical.Telas.Cliente.ClientesActivity;
import com.alberto.carlos.sabortropical.Telas.Cliente.ClientesEditDelActivity;
import com.alberto.carlos.sabortropical.Telas.MainActivity;

import java.util.List;

public class RelatorioActivity extends AppCompatActivity {

    Relatorio relatorioSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);

        final EditText dataInicio = (EditText) findViewById(R.id.campo_dataInicial);

        final EditText dataFinal = (EditText) findViewById(R.id.campo_dataFinal);

        Button buttonCompras = (Button) findViewById(R.id.botao_compras);
        Button buttonVendas = (Button) findViewById(R.id.botao_vendas);

        buttonCompras.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                boolean verificador = testarCampoVazio();

                if(!verificador) {

                    try {

                        Relatorio relatorio = new Relatorio();
                        relatorio.setDataInicio(formataData(dataInicio.getText().toString()));
                        relatorio.setDataFim(formataData(dataFinal.getText().toString()));
                        relatorio.setTipo(0);

                        Intent gerar = new Intent(RelatorioActivity.this, RelatorioDetalhadoActivity.class);
                        relatorioSelecionado = (Relatorio) relatorio;
                        gerar.putExtra("relatorioSelecionado", relatorioSelecionado);
                        startActivity(gerar);


                    }
                    catch (Exception e){

                        Toast.makeText(RelatorioActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

                    }


                }

            }

            private boolean testarCampoVazio() {

                if(TextUtils.isEmpty(dataInicio.getText().toString())) {
                    Toast.makeText(RelatorioActivity.this, "Campo Data Inicial esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(dataFinal.getText().toString())) {
                    Toast.makeText(RelatorioActivity.this, "Campo Data Final esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }


                return false;

            }

            private String formataData(String p_Data){

                String data;

                data = p_Data.substring(6,10) + "-" + p_Data.substring(3,5) + "-" + p_Data.substring(0,2);


                return data;

            }


        });


        buttonVendas.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                boolean verificador = testarCampoVazio();

                if(!verificador) {

                    try {

                        Relatorio relatorio = new Relatorio();
                        relatorio.setDataInicio(formataData(dataInicio.getText().toString()));
                        relatorio.setDataFim(formataData(dataFinal.getText().toString()));
                        relatorio.setTipo(1);

                        Intent gerar = new Intent(RelatorioActivity.this, RelatorioDetalhadoActivity.class);
                        relatorioSelecionado = (Relatorio) relatorio;
                        gerar.putExtra("relatorioSelecionado", relatorioSelecionado);
                        startActivity(gerar);


                    }
                    catch (Exception e){

                        Toast.makeText(RelatorioActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

                    }


                }

            }

            private boolean testarCampoVazio() {

                if(TextUtils.isEmpty(dataInicio.getText().toString())) {
                    Toast.makeText(RelatorioActivity.this, "Campo Data Inicial esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(dataFinal.getText().toString())) {
                    Toast.makeText(RelatorioActivity.this, "Campo Data Final esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }


                return false;

            }

            private String formataData(String p_Data){

                String data;

                data = p_Data.substring(6,10) + "-" + p_Data.substring(3,5) + "-" + p_Data.substring(0,2);


                return data;

            }


        });



    }

}
