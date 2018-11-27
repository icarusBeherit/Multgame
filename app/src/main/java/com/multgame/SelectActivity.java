package com.multgame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;


public class SelectActivity extends AppCompatActivity implements View.OnClickListener {

    AlertDialog alertDialog1;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView imageView17;
    ImageView imageView18;
    ImageView imageView19;
    ImageView imageView20;
    ImageView imageView21;
    public String TAG = "sistema";
    String Myprefs = "escolhas";
    SharedPreferences.Editor editor;
    String item_selecionado;
    SharedPreferences prefs;
    Button submit;
    Button clear;
    String instituicao;
    double valor_campeao_hora;
    String item_instituicao;
    TextView doacao;
    Util util;

    String a1;
    String a2;
    String a3;
    String a4;
    String a5;

    String b1;
    String b2;
    String b3;
    String b4;

    String c1;
    String c2;
    String c3;
    String c4;

    String d1;
    String d2;
    String d3;
    String d4;

    String e1;
    String e2;
    String e3;
    String e4;
    private String current = "";
    double double_valor = 0.0;
    Locale mLocale;
    TextView valorCampeaoHora;
    Button buttonCh;
    BigDecimal parsed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        util = new Util();

        editor = getSharedPreferences(Myprefs, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(Myprefs, MODE_PRIVATE);
        instituicao = prefs.getString("instituicao", "");
        item_instituicao = prefs.getString("item_instituicao", "");

        submit = findViewById(R.id.submit);
        clear = findViewById(R.id.clear);
        doacao = findViewById(R.id.doacao);

        mLocale = new Locale("pt", "BR");

        String mystring = getResources().getString(R.string.app_name);

        getSupportActionBar().setTitle(mystring);
        getSupportActionBar().setSubtitle(instituicao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        buttonCh = findViewById(R.id.btCampeaoDaHora);

        buttonCh.setEnabled(false);

        buttonCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogCH();
            }
        });

        //Imageviews
        imageView1= findViewById(R.id.a1);
       imageView2= findViewById(R.id.a2);
       imageView3= findViewById(R.id.a3);
       imageView4= findViewById(R.id.a4);
       imageView5= findViewById(R.id.a5);
       imageView6= findViewById(R.id.b1);
       imageView7= findViewById(R.id.b2);
       imageView8= findViewById(R.id.b3);
       imageView9= findViewById(R.id.b4);
       imageView10= findViewById(R.id.c1);
       imageView11= findViewById(R.id.c2);
       imageView12= findViewById(R.id.c3);
       imageView13= findViewById(R.id.c4);
       imageView14= findViewById(R.id.d1);
       imageView15= findViewById(R.id.d2);
       imageView16= findViewById(R.id.d3);
       imageView17= findViewById(R.id.d4);
       imageView18= findViewById(R.id.e1);
       imageView19= findViewById(R.id.e2);
       imageView20= findViewById(R.id.e3);
       imageView21= findViewById(R.id.e4);
       //

       //Listeners
       imageView1.setOnClickListener(this);
       imageView2.setOnClickListener(this);
       imageView3.setOnClickListener(this);
       imageView4.setOnClickListener(this);
       imageView5.setOnClickListener(this);
       imageView6.setOnClickListener(this);
       imageView7.setOnClickListener(this);
       imageView8.setOnClickListener(this);
       imageView9.setOnClickListener(this);
       imageView10.setOnClickListener(this);
       imageView11.setOnClickListener(this);
       imageView12.setOnClickListener(this);
       imageView13.setOnClickListener(this);
       imageView14.setOnClickListener(this);
       imageView15.setOnClickListener(this);
       imageView16.setOnClickListener(this);
       imageView17.setOnClickListener(this);
       imageView18.setOnClickListener(this);
       imageView19.setOnClickListener(this);
       imageView20.setOnClickListener(this);
       imageView21.setOnClickListener(this);
       //

        valorCampeaoHora = findViewById(R.id.txValorCampeaoHora);

        checkSharedPreferences();
        calculaDoacao();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (checkFields()){
                    openDialog();
               }
               else
               {
                   if (a5.equals("")){
                       Toast.makeText(SelectActivity.this, "Você precisa selecionar uma fileira de bandeiras ou escolher o campeão da hora para continuar!", Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });
    }

    private BigDecimal parseToBigDecimal(String value, Locale locale) {
        String replaceable = String.format("[%s,.\\s]", NumberFormat.getCurrencyInstance(locale).getCurrency().getSymbol());

        String cleanString = value.replaceAll(replaceable, "");

        return new BigDecimal(cleanString).setScale(
                2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean checkFields(){

        a1 = prefs.getString("a1", "");
        a2 = prefs.getString("a2", "");
        a3 = prefs.getString("a3", "");
        a4 = prefs.getString("a4", "");
        a5 = prefs.getString("a5", "");

        if (!a5.equals("")){
            verificaCampeaoDaHora();
            buttonCh.setEnabled(true);
        }

        if (!a1.equals("") && !a2.equals("") && !a3.equals("") && !a4.equals("") && !a5.equals("")){
            return true;
        }

        b1 = prefs.getString("b1", "");
        b2 = prefs.getString("b2", "");
        b3 = prefs.getString("b3", "");
        b4 = prefs.getString("b4", "");

        if (!b1.equals("") && !b2.equals("") && !b3.equals("") && !b4.equals("")){
            return true;
        }

        c1 = prefs.getString("c1", "");
        c2 = prefs.getString("c2", "");
        c3 = prefs.getString("c3", "");
        c4 = prefs.getString("c4", "");

        if (!c1.equals("") && !c2.equals("") && !c3.equals("") && !c4.equals("")){
            return true;
        }

        d1 = prefs.getString("d1", "");
        d2 = prefs.getString("d2", "");
        d3 = prefs.getString("d3", "");
        d4 = prefs.getString("d4", "");

        if (!d1.equals("") && !d2.equals("")&& !d3.equals("") && !d4.equals("")){
            return true;
        }

        e1 = prefs.getString("e1", "");
        e2 = prefs.getString("e2", "");
        e3 = prefs.getString("e3", "");
        e4 = prefs.getString("e4", "");

        if (!e1.equals("") && !e2.equals("") && !e3.equals("") && !e4.equals("")){
            return true;
        }

        return false;
    }

    private boolean verificaCampeaoDaHora() {

        String valor_campeao = valorCampeaoHora.getText().toString();

        if (a5.equals("") && valor_campeao.equals("")){
            Toast.makeText(this, "Marque a opção 'adicionar CH' e adicione o valor para continuar", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (a5.equals("") || valor_campeao.equals("")){
            Toast.makeText(this, "Marque a opção 'adicionar CH' e adicione o valor para continuar", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    private void openDialog() {

        final View view = getLayoutInflater().inflate(R.layout.alert_dialog, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        final EditText nome = (EditText) view.findViewById(R.id.input_nome);
        final EditText telefone = (EditText) view.findViewById(R.id.input_telefone);

        telefone.addTextChangedListener(Mask.insert("(##)#####-####", telefone));

        alertDialog.setView(view);

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(SelectActivity.this, PrintActivity.class);
                        finish();
                        startActivity(i);

                    }
                });

        alertDialog.show();

    }

    private void openDialogCH() {

        final View view = getLayoutInflater().inflate(R.layout.campeao_hora, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        final EditText valor = (EditText) view.findViewById(R.id.input_valor);
        valor.requestFocus();

        ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        final Locale mLocale = new Locale("pt", "BR");

        valor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable == null) return;
                valor.removeTextChangedListener(this);

                parsed = parseToBigDecimal(editable.toString(), mLocale);
                String formatted = NumberFormat.getCurrencyInstance(mLocale).format(parsed);

                valor.setText(formatted);
                valor.setSelection(formatted.length());
                valor.addTextChangedListener(this);
            }

        });

        alertDialog.setView(view);

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(valor.getWindowToken(), 0);
                        setaValor();
                    }
                });

        alertDialog.show();

    }

    public void setaValor(){

        valor_campeao_hora = parsed.doubleValue();

        Locale mLocale = new Locale("pt", "BR");

        double resultado = valor_campeao_hora;

        String valorString = NumberFormat.getCurrencyInstance(mLocale).format(resultado);

        calculaDoacao();

        valorCampeaoHora.setText(valorString);

    }

    private void clearAllSharedPreferences() {
        SharedPreferences settings = getSharedPreferences(Myprefs, Context.MODE_PRIVATE);
        settings.edit().clear().apply();
        Intent i = new Intent(getBaseContext(), SelectActivity.class);
        finish();
        startActivity(i);
    }

    public void submitData(){
        new submitData().execute();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change:
                exibirInstituicoes();
            return true;

            case R.id.clear:
                clearAllSharedPreferences();
            return true;

            case android.R.id.home:
                finish();
            return true;

            default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void checkSharedPreferences() {

        a1 = prefs.getString("a1", "");
        a2 = prefs.getString("a2", "");
        a3 = prefs.getString("a3", "");
        a4 = prefs.getString("a4", "");
        a5 = prefs.getString("a5", "");

        b1 = prefs.getString("b1", "");
        b2 = prefs.getString("b2", "");
        b3 = prefs.getString("b3", "");
        b4 = prefs.getString("b4", "");

        c1 = prefs.getString("c1", "");
        c2 = prefs.getString("c2", "");
        c3 = prefs.getString("c3", "");
        c4 = prefs.getString("c4", "");

        d1 = prefs.getString("d1", "");
        d2 = prefs.getString("d2", "");
        d3 = prefs.getString("d3", "");
        d4 = prefs.getString("d4", "");

        e1 = prefs.getString("e1", "");
        e2 = prefs.getString("e2", "");
        e3 = prefs.getString("e3", "");
        e4 = prefs.getString("e4", "");

        if (!a1.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.a1);
            setaDrawable(mv,a1);
        }

        if (!a2.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.a2);
            setaDrawable(mv,a2);
        }

        if (!a3.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.a3);
            setaDrawable(mv,a3);
        }

        if (!a4.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.a4);
            setaDrawable(mv,a4);
        }

        if (!a5.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.a5);
            setaDrawable(mv,a5);
        }

        if (!b1.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.b1);
            setaDrawable(mv,b1);
        }

        if (!b2.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.b2);
            setaDrawable(mv,b2);
        }

        if (!b3.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.b3);
            setaDrawable(mv,b3);
        }

        if (!b4.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.b4);
            setaDrawable(mv,b4);
        }

        if (!c1.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.c1);
            setaDrawable(mv,c1);
        }

        if (!c2.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.c2);
            setaDrawable(mv,c2);
        }

        if (!c3.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.c3);
            setaDrawable(mv,c3);
        }

        if (!c4.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.c4);
            setaDrawable(mv,c4);
        }

        if (!d1.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.d1);
            setaDrawable(mv,d1);
        }

        if (!d2.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.d2);
            setaDrawable(mv,d2);
        }

        if (!d3.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.d3);
            setaDrawable(mv,d3);
        }

        if (!d4.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.d4);
            setaDrawable(mv,d4);
        }

        if (!e1.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.e1);
            setaDrawable(mv,e1);
        }

        if (!e2.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.e2);
            setaDrawable(mv,e2);
        }

        if (!e3.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.e3);
            setaDrawable(mv,e3);
        }

        if (!e4.equals("")){
            ImageView mv = (ImageView) findViewById(R.id.e4);
            setaDrawable(mv,e4);
        }

    }

    private int setaDrawable(ImageView imageView,String check) {

        switch (check){
            case "0":
                imageView.setImageResource(R.drawable.argentina0);
            break;
            case "1":
                imageView.setImageResource(R.drawable.brasil1);
            break;

            case "2":
                imageView.setImageResource(R.drawable.chile2);
            break;

            case "3":
                imageView.setImageResource(R.drawable.uruguai3);
            break;

            case "4":
                imageView.setImageResource(R.drawable.colombia4);
            break;

            case "5":
                imageView.setImageResource(R.drawable.italia5);
            break;

            case "6":
                imageView.setImageResource(R.drawable.inglaterra6);
            break;

            case "7":
                imageView.setImageResource(R.drawable.alemanha7);
            break;

            case "8":
                imageView.setImageResource(R.drawable.espanha8);
            break;

            case "9":
                imageView.setImageResource(R.drawable.franca9);
            break;
        }
        return 0;
    }

    public void exibirDetalhes(final int image_id){

        final String imagem_escolhida = getResources().getResourceEntryName(image_id);

        final String [] items = new String[] {" Argentina "," Brasil "," Chile "," Uruguai "," Colombia "," Itália "," Inglaterra "," Alemanha "," Espanha "," França "};
        final Integer[] icons = new Integer[] {R.drawable.argentina0, R.drawable.brasil1,R.drawable.chile2,R.drawable.uruguai3,R.drawable.colombia4,R.drawable.italia5,R.drawable.inglaterra6,R.drawable.alemanha7,R.drawable.espanha8,R.drawable.franca9};
        ListAdapter adapter = new ArrayAdapterWithIcon(this, items, icons);

        AlertDialog.Builder builder = new AlertDialog.Builder(SelectActivity.this);
        builder.setTitle("Selecione uma opção");
        builder.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {
                switch(item)
                {
                    case 0:
                        ImageView mv = (ImageView) findViewById(image_id);
                        mv.setImageResource(R.drawable.argentina0);
                        item_selecionado = String.valueOf(item);
                        break;
                    case 1:
                        ImageView mv1 = (ImageView) findViewById(image_id);
                        mv1.setImageResource(R.drawable.brasil1);
                        item_selecionado = String.valueOf(item);
                        break;
                    case 2:
                        ImageView mv2 = (ImageView) findViewById(image_id);
                        mv2.setImageResource(R.drawable.chile2);
                        item_selecionado = String.valueOf(item);
                        break;
                    case 3:
                        ImageView mv3 = (ImageView) findViewById(image_id);
                        mv3.setImageResource(R.drawable.uruguai3);
                        item_selecionado = String.valueOf(item);
                        break;
                    case 4:
                        ImageView mv4 = (ImageView) findViewById(image_id);
                        mv4.setImageResource(R.drawable.colombia4);
                        item_selecionado = String.valueOf(item);
                        break;
                    case 5:
                        ImageView mv5 = (ImageView) findViewById(image_id);
                        mv5.setImageResource(R.drawable.italia5);
                        item_selecionado = String.valueOf(item);
                        break;
                    case 6:
                        ImageView mv6 = (ImageView) findViewById(image_id);
                        mv6.setImageResource(R.drawable.inglaterra6);
                        item_selecionado = String.valueOf(item);
                        break;
                    case 7:
                        ImageView mv7 = (ImageView) findViewById(image_id);
                        mv7.setImageResource(R.drawable.alemanha7);
                        item_selecionado = String.valueOf(item);
                        break;
                    case 8:
                        ImageView mv8 = (ImageView) findViewById(image_id);
                        mv8.setImageResource(R.drawable.espanha8);
                        item_selecionado = String.valueOf(item);
                        break;
                    case 9:
                        ImageView mv9 = (ImageView) findViewById(image_id);
                        mv9.setImageResource(R.drawable.franca9);
                        item_selecionado = String.valueOf(item);
                        break;
                }
                alertDialog1.dismiss();
                Log.e("escolha","imagem escolhida: "+imagem_escolhida+", item"+item_selecionado);
                editor.putString(imagem_escolhida, item_selecionado);
                editor.apply();
                calculaDoacao();
            }
        });
        alertDialog1 = builder.create();
        alertDialog1.show();
    }

    private void calculaDoacao() {

        double total_doacao=0;

        a1 = prefs.getString("a1", "");
        a2 = prefs.getString("a2", "");
        a3 = prefs.getString("a3", "");
        a4 = prefs.getString("a4", "");
        a5 = prefs.getString("a5", "");

        if (!a5.equals("")){
            buttonCh.setEnabled(true);
            buttonCh.setTextColor(getApplication().getResources().getColor(R.color.white));
        }

        if(!a1.equals("") && !a2.equals("") && !a3.equals("") && !a4.equals("") && !a5.equals("")){
            total_doacao = total_doacao +1;
        }

        b1 = prefs.getString("b1", "");
        b2 = prefs.getString("b2", "");
        b3 = prefs.getString("b3", "");
        b4 = prefs.getString("b4", "");

        if(!b1.equals("") && !b2.equals("") && !b3.equals("") && !b4.equals("")){
            total_doacao = total_doacao +1;
        }

        c1 = prefs.getString("c1", "");
        c2 = prefs.getString("c2", "");
        c3 = prefs.getString("c3", "");
        c4 = prefs.getString("c4", "");


        if(!c1.equals("") && !c2.equals("") && !c3.equals("") && !c4.equals("")){
            total_doacao = total_doacao +1;
        }

        d1 = prefs.getString("d1", "");
        d2 = prefs.getString("d2", "");
        d3 = prefs.getString("d3", "");
        d4 = prefs.getString("d4", "");

        if(!d1.equals("") && !d2.equals("") && !d3.equals("") && !d4.equals("")){
            total_doacao = total_doacao +1;
        }

        e1 = prefs.getString("e1", "");
        e2 = prefs.getString("e2", "");
        e3 = prefs.getString("e3", "");
        e4 = prefs.getString("e4", "");

        if(!e1.equals("") && !e2.equals("") && !e3.equals("") && !e4.equals("")){
            total_doacao = total_doacao +1;
        }

        Locale mLocale = new Locale("pt", "BR");

        double resultado = valor_campeao_hora + total_doacao;

        String valorString = NumberFormat.getCurrencyInstance(mLocale).format(resultado);
        doacao.setText(valorString);

    }




    @Override
    public void onClick(View v) {
        exibirDetalhes(v.getId());
    }


    public void exibirInstituicoes(){
        final String [] items = new String[] {"APAE","Instituto São Vicente de Paulo"," Casa do Menino em Santo Antônio"};

        AlertDialog.Builder builder = new AlertDialog.Builder(SelectActivity.this);
        builder.setTitle("Selecione uma opção");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {
                String instituicao = items[item];
                editor.putString("instituicao", instituicao);
                editor.putString("item_instituicao", String.valueOf(item));
//                valor_atual = String.valueOf(item);
                getSupportActionBar().setSubtitle(instituicao);
                editor.apply();
                alertDialog1.dismiss();

            }
        });
        alertDialog1 = builder.create();
        alertDialog1.show();
    }

    public class submitData extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

        }

        protected String doInBackground(String... arg0) {

            HttpURLConnection conn = null;
            BufferedReader reader = null;

            try {

                URL url = new URL(util.url+"?acao=salvaDoacao");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("a1", a1);
                postDataParams.put("a2", a2);
                postDataParams.put("a3", a3);
                postDataParams.put("a4", a4);
                postDataParams.put("a5", a5);

                postDataParams.put("b1", b1);
                postDataParams.put("b2", b2);
                postDataParams.put("b3", b3);
                postDataParams.put("b4", b4);

                postDataParams.put("c1", c1);
                postDataParams.put("c2", c2);
                postDataParams.put("c3", c3);
                postDataParams.put("c4", c4);

                postDataParams.put("d1", d1);
                postDataParams.put("d2", d2);
                postDataParams.put("d3", d3);
                postDataParams.put("d4", d4);

                postDataParams.put("c1", c1);
                postDataParams.put("c2", c2);
                postDataParams.put("c3", c3);
                postDataParams.put("c4", c4);

                postDataParams.put("e1", e1);
                postDataParams.put("e2", e2);
                postDataParams.put("e3", e3);
                postDataParams.put("e4", e4);

                Log.e("doacao",""+postDataParams);

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(util.getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                //Get Response
                InputStream is = conn.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                return response.toString();

            } catch (Exception e) {
                //return new String("Exception: " + e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {

            if (result != null)
            {

                String id = "";
                String servico = "";
                String imagem = "";
                String categoria_id = "";
                String descricao = "";
                String valor = "";
                String cidade = "";

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray =  jsonObject.getJSONArray("resultado");

                    for(int i =0;i<jsonArray.length(); i++)
                    {
                        JSONObject productObject = jsonArray.getJSONObject(i);
                        id = productObject.getString("id");
                        String mensagem = productObject.getString("mensagem");

                        if (mensagem.equals("ok")){
                            Intent t = new Intent(getBaseContext(), SelectActivity.class);
                            finish();
                            startActivity(t);
                        }
                        else{
                            Toast.makeText(SelectActivity.this, "Houve um erro ao executar a doação! Tente novamente!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else
            {

            }
        }
    }
}
