package com.alberto.carlos.sabortropical.Telas.Usuario;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.UsuarioDao;
import com.alberto.carlos.sabortropical.Entidades.Usuario;
import com.alberto.carlos.sabortropical.R;
import com.alberto.carlos.sabortropical.utilitarios.Mask;

import java.text.SimpleDateFormat;

public class UsuariosCadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_cad);

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

        final EditText email = (EditText) findViewById(R.id.campo_email);

        final EditText senha = (EditText) findViewById(R.id.campo_senha);

        final String[] strNIvelAcesso = new String[]{"Usuário","Administrador"};
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, strNIvelAcesso);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner nivelAcesso = (Spinner) findViewById(R.id.campo_nivelAcesso);
        nivelAcesso.setAdapter(adapter);

        Button buttonSalvar = (Button) findViewById(R.id.botao_salvar);


        buttonSalvar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                boolean verificador = testarCampoVazio();
                Database database;
                SQLiteDatabase conn;
                long idUsuario;

                if(!verificador) {

                    try {
                        database = new Database(UsuariosCadActivity.this);
                        conn = database.getWritableDatabase();
                        UsuarioDao dao = new UsuarioDao(conn);
                        Usuario usuario = new Usuario();
                        usuario.setNome(nome.getText().toString());
                        usuario.setSobreNome(sobreNome.getText().toString());
                        usuario.setDataNascimento(dataNascimento.getText().toString());
                        usuario.setCorPele(corPele.getSelectedItemPosition());
                        usuario.setCorOlhos(corOlhos.getSelectedItemPosition());
                        usuario.setSexo(sexo.getSelectedItemPosition());
                        usuario.setNomePai(nomePai.getText().toString());
                        usuario.setNomeMae(nomeMae.getText().toString());
                        usuario.setEstadoCivil(estadoCivil.getSelectedItemPosition());
                        usuario.setCpf(cpf.getText().toString());
                        usuario.setIdentidade(identidade.getText().toString());
                        usuario.setEmail(email.getText().toString());
                        usuario.setSenha(senha.getText().toString());
                        usuario.setDataAdmissao(getDataAtual());
                        usuario.setNivel(nivelAcesso.getSelectedItemPosition());
                        idUsuario = dao.inserir(usuario);
                        dao.fechar();

                        Toast.makeText(UsuariosCadActivity.this, "Usuário Nº " + idUsuario + " cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                        Intent it = new Intent(UsuariosCadActivity.this,UsuariosActivity.class);
                        startActivity(it);


                    }
                    catch (SQLException e){

                        Toast.makeText(UsuariosCadActivity.this,"Erro: " + e.getMessage(),Toast.LENGTH_LONG).show();

                    }


                }

            }


            private boolean testarCampoVazio() {

                if(TextUtils.isEmpty(nome.getText().toString())) {
                    Toast.makeText(UsuariosCadActivity.this, "Campo Nome esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(sobreNome.getText().toString())) {
                    Toast.makeText(UsuariosCadActivity.this, "Campo Sobrenome esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(dataNascimento.getText().toString())) {
                    Toast.makeText(UsuariosCadActivity.this, "Campo Data de Nascimento esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(nomePai.getText().toString())) {
                    Toast.makeText(UsuariosCadActivity.this, "Campo nome do Pai esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(nomeMae.getText().toString())) {
                    Toast.makeText(UsuariosCadActivity.this, "Campo nome da Mãe esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(cpf.getText().toString())) {
                    Toast.makeText(UsuariosCadActivity.this, "Campo cpf esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(identidade.getText().toString())) {
                    Toast.makeText(UsuariosCadActivity.this, "Campo Identidade esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(email.getText().toString())) {
                    Toast.makeText(UsuariosCadActivity.this, "Campo email esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(senha.getText().toString())) {
                    Toast.makeText(UsuariosCadActivity.this, "Campo Senha esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;

            }

            private String getDataAtual(){

                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String data = sdf.format(date);

                return data;
            }


        });

    }

}
