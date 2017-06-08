package com.alberto.carlos.sabortropical.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.alberto.carlos.sabortropical.R;


//classe principal
public class MainActivity extends AppCompatActivity {

    @Override
    //cria o layout
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    //cria o menu
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

    }

    @Override
    //quando um item do menu for selecionado
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_cliente){

            Intent it = new Intent(this,ClientesActivity.class);
            startActivity(it);

        }

        return super.onOptionsItemSelected(item);

    }



}
