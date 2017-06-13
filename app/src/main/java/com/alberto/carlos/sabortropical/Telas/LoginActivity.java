package com.alberto.carlos.sabortropical.Telas;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.alberto.carlos.sabortropical.BancoDeDados.Database;
import com.alberto.carlos.sabortropical.Dao.UsuarioDao;
import com.alberto.carlos.sabortropical.R;
import com.alberto.carlos.sabortropical.Telas.Usuario.UsuariosActivity;
import com.alberto.carlos.sabortropical.Telas.Usuario.UsuariosCadActivity;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    private EditText edEmail;
    private EditText edSenha;
    private Database database;
    private SQLiteDatabase conn;

    @Override
    //cria o layout
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edEmail = (EditText) findViewById(R.id.email);
        edSenha = (EditText) findViewById(R.id.password);
    }



    //clique do botão entrar
    public void login(View view){

        boolean existe;
        boolean verificar = testarCampoVazio();

        if (!verificar) {

            database = new Database(LoginActivity.this);
            conn = database.getReadableDatabase();
            UsuarioDao dao = new UsuarioDao(conn);
            existe = dao.existeUsuario(edEmail.getText().toString(), edSenha.getText().toString());

            if (existe) {

                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
                Toast.makeText(LoginActivity.this, "Bem vindo.", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(LoginActivity.this, "Email ou Senha incorretos.", Toast.LENGTH_LONG).show();

            }

        }

     }

     //clique botão sair
     public void sair(View view){

         Toast.makeText(LoginActivity.this,"Até Logo.",Toast.LENGTH_LONG).show();
         finish();

     }

    private boolean testarCampoVazio() {

        if(TextUtils.isEmpty(edEmail.getText().toString())) {
            Toast.makeText(LoginActivity.this, "Campo email esta vazio.", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(TextUtils.isEmpty(edSenha.getText().toString())) {
            Toast.makeText(LoginActivity.this, "Campo Senha esta vazio.", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;

    }

}

