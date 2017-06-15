package com.alberto.carlos.sabortropical.Telas.Relatorio;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alberto.carlos.sabortropical.Entidades.Pedido;
import com.alberto.carlos.sabortropical.Entidades.Relatorio;
import com.alberto.carlos.sabortropical.R;

import java.util.List;

//classe principal
public class RelatorioDetalhadoActivity extends AppCompatActivity {

    private ListView listaRelatorios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatoriodetalhado);

        listaRelatorios = (ListView) findViewById(R.id.lista_de_relatorios);

        registerForContextMenu(listaRelatorios);

        try {
            Relatorio relatorio = (Relatorio) getIntent().getSerializableExtra("relatorioSelecionado");
            List<Pedido> pedidos = relatorio.gerar(RelatorioDetalhadoActivity.this);
            ArrayAdapter<Pedido> adapter = new ArrayAdapter<Pedido>
                    (RelatorioDetalhadoActivity.this, android.R.layout.simple_list_item_1, pedidos);

            listaRelatorios.setAdapter(adapter);

        }
        catch (SQLException e){

            Toast.makeText(RelatorioDetalhadoActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

        }

    }


}
