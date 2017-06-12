package com.alberto.carlos.sabortropical.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alberto.carlos.sabortropical.Entidades.Usuario;
import com.alberto.carlos.sabortropical.R;
import com.alberto.carlos.sabortropical.utilitarios.FormularioHelperDetallharUsuario;
import com.alberto.carlos.sabortropical.utilitarios.Mask;

public class UsuariosEditDelActivity extends AppCompatActivity {

    private FormularioHelperDetallharUsuario helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_edit_del);
        helper = new FormularioHelperDetallharUsuario(this);

        Usuario usuarioDetalhado = (Usuario) getIntent().getSerializableExtra("usuarioSelecionado");
        helper.colocaUsuarioNoFormulario(usuarioDetalhado);

        Button buttonEditar = (Button) findViewById(R.id.botao_editar);
        Button buttonDeletar = (Button) findViewById(R.id.botao_deletar);

    }

}
