package com.alberto.carlos.sabortropical.Telas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alberto.carlos.sabortropical.BancoDeDados.DatabaseUsuario;
import com.alberto.carlos.sabortropical.Dao.UsuarioDao;
import com.alberto.carlos.sabortropical.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    //declaração de variáveis
    private EditText edEmail;
    private EditText edSenha;
    private DatabaseUsuario databaseUsuario;
    private SQLiteDatabase conn;

    @Override
    //cria o layout ao iniciar a classe
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //cria a tela
        setContentView(R.layout.activity_login);
        //referancia da tela para a classe o campo de texto de email
        edEmail = (EditText) findViewById(R.id.email);
        //referancia da tela para a classe o campo de texto de senha
        edSenha = (EditText) findViewById(R.id.password);
    }



    //clique do botão entrar
    public void login(View view){

        //declaração de variavel
        boolean existe;
        //pega retorno de verificaçao de campos vazios na tela
        boolean verificar = testarCampoVazio();

        //se for false, todos os campos foram preenchidos
        if (!verificar) {

            //cria minha base de dados, passando como referencia minha tela
            databaseUsuario = new DatabaseUsuario(LoginActivity.this);
            //pede permissão para ler do banco
            conn = databaseUsuario.getReadableDatabase();
            //instancia a classe de persistência do usuário, passando o objeto de conexão do banco no construtor
            UsuarioDao dao = new UsuarioDao(conn);
            //executa metodo de verificação de email ou senha, caso existe retorna true
            existe = dao.existeUsuario(edEmail.getText().toString(), edSenha.getText().toString());

            //se existir o usuario
            if (existe) {

                //cria objeto de navegação nas tela, passando minha classe com referencia e a classe para onde ir no construtor
                Intent it = new Intent(this, MainActivity.class);
                //ativa a nevegação para outra tela
                startActivity(it);
                //mostra mensagem na tela
                Toast.makeText(LoginActivity.this, "Bem vindo.", Toast.LENGTH_SHORT).show();

                //caso não
            } else {
                //mostra mensagem na tela
                Toast.makeText(LoginActivity.this, "Email ou Senha incorretos.", Toast.LENGTH_LONG).show();

            }

        }

     }

     //clique botão sair
     public void sair(View view){
        //mostra mensagem na tela
         Toast.makeText(LoginActivity.this,"Até Logo.",Toast.LENGTH_LONG).show();
         //fecha tela
         finish();

     }

     //funçao para verificação se os campos estão vazios ou não, retorna true caso esteja vazio
    private boolean testarCampoVazio() {

        //verifica se o campo email está vazio
        if(TextUtils.isEmpty(edEmail.getText().toString())) {
            //mostra mensagem na tela
            Toast.makeText(LoginActivity.this, "Campo email esta vazio.", Toast.LENGTH_SHORT).show();
            return true;
        }
        //verifica se o campo senha está vazio
        if(TextUtils.isEmpty(edSenha.getText().toString())) {
            //mostra mensagem na tela
            Toast.makeText(LoginActivity.this, "Campo Senha esta vazio.", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;

    }

}

