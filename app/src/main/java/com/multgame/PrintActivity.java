package com.multgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PrintActivity extends AppCompatActivity {

    String Myprefs = "escolhas";
    SharedPreferences.Editor editor;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        editor = getSharedPreferences(Myprefs, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(Myprefs, MODE_PRIVATE);

        Button nova_doacao = findViewById(R.id.nova_doacao);
        Button imprimir = findViewById(R.id.print);

        imprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PrintActivity.this, "Imprimir", Toast.LENGTH_SHORT).show();
            }
        });

        nova_doacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), SelectActivity.class);
                clearAllSharedPreferences();
                finish();
                startActivity(i);
            }
        });
    }

    private void clearAllSharedPreferences() {
        SharedPreferences settings = getSharedPreferences(Myprefs, Context.MODE_PRIVATE);
        settings.edit().clear().apply();

        Intent i = new Intent(getBaseContext(), SelectActivity.class);
        finish();
        startActivity(i);
    }
}
