package com.multgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener  {

    MyRecyclerViewAdapter adapter;
    ArrayList<String> instituicoes;
    String Myprefs = "escolhas";
    SharedPreferences.Editor editor;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        editor = getSharedPreferences(Myprefs, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(Myprefs, MODE_PRIVATE);

        instituicoes = new ArrayList<>();
        instituicoes.add("APAE");
        instituicoes.add("Instituto São Vicente de Paulo");
        instituicoes.add("Casa do Menino em Santo Antônio");

        RecyclerView recyclerView = findViewById(R.id.rvSubcategorias);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, instituicoes);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent i = new Intent(ListActivity.this, SelectActivity.class);
        editor.putString("instituicao", instituicoes.get(position));
        editor.putString("item", String.valueOf(position));
        editor.apply();
        startActivity(i);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
