package com.multgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

public class Util extends AppCompatActivity {


    public String url = "";

    public String TAG = "sistema";

    public static int randInt(int min, int max) {
        Random rn = new Random();
        int range = max - min + 1;
        int randomNum =  rn.nextInt(range) + min;
        return randomNum;
    }

    public String[] cidades(String estado){
        String[] vazio = {};
        String[] pb = {"Água Branca","Aguiar","Alagoa Grande","Alagoa Nova","Alagoinha","Alcantil","Algodão de Jandaíra","Alhandra","Amparo","Aparecida","Araçagi","Arara","Araruna","Areia","Areia de Baraúnas","Areial","Aroeiras","Assunção","Baía da Traição","Bananeiras","Baraúna","Barra de Santa Rosa","Barra de Santana","Barra de São Miguel","Bayeux","Belém","Belém do Brejo do Cruz","Bernardino Batista","Boa Ventura","Boa Vista","Bom Jesus","Bom Sucesso","Bonito de Santa Fé","Boqueirão","Borborema","Brejo do Cruz","Brejo dos Santos","Caaporã","Cabaceiras","Cabedelo","Cachoeira dos Índios","Cacimba de Areia","Cacimba de Dentro","Cacimbas","Caiçara","Cajazeiras","Cajazeirinhas","Caldas Brandão","Camalaú","Campina Grande","Capim","Caraúbas","Carrapateira","Casserengue","Catingueira","Catolé do Rocha","Caturité","Conceição","Condado","Conde","Congo","Coremas","Coxixola","Cruz do Espírito Santo","Cubati","Cuité","Cuité de Mamanguape","Cuitegi","Curral de Cima","Curral Velho","Damião","Desterro","Diamante","Dona Inês","Duas Estradas","Emas","Esperança","Fagundes","Frei Martinho","Gado Bravo","Guarabira","Gurinhém","Gurjão","Ibiara","Igaracy","Imaculada","Ingá","Itabaiana","Itaporanga","Itapororoca","Itatuba","Jacaraú","Jericó","João Pessoa","Joca Claudino","Juarez Távora","Juazeirinho","Junco do Seridó","Juripiranga","Juru","Lagoa","Lagoa de Dentro","Lagoa Seca","Lastro","Livramento","Logradouro","Lucena","Mãe Dágua","Malta","Mamanguape","Manaíra","Marcação","Mari","Marizópolis","Massaranduba","Mataraca","Matinhas","Mato Grosso","Maturéia","Mogeiro","Montadas","Monte Horebe","Monteiro","Mulungu","Natuba","Nazarezinho","Nova Floresta","Nova Olinda","Nova Palmeira","Olho Dágua","Olivedos","Ouro Velho","Parari","Passagem","Patos","Paulista","Pedra Branca","Pedra Lavrada","Pedro Régis","Piancó","Picuí","Pilar","Pilõezinhos","Pirpirituba","Pitimbu","Pocinhos","Poço Dantas","Poço de José de Moura","Pombal","Prata","Princesa Isabel","Puxinanã","Queimadas","Quixabá","Remígio","Riachão","Riachão do Bacamarte","Riachão do Poço","Riacho de Santo Antônio","Riacho dos Cavalos","Rio Tinto","Salgadinho","Salgado de São Félix","Santa Cecília","Santa Cruz","Santa Helena","Santa Inês","Santa Luzia","Santa Rita","Santa Teresinha","Santana de Mangueira","Santana dos Garrotes","Santo André","São Bentinho","São Bento","São Domingos de Pombal","São Domingos do Cariri","São Francisco","São João do Cariri","São João do Rio do Peixe","São João do Tigre","São José da Lagoa Tapada","São José de Caiana","São José de Espinharas","São José de Piranhas","São José de Princesa","São José do Bonfim","São José do Brejo do Cruz","São José do Sabugi","São José dos Cordeiros","São José dos Ramos","São Mamede","São Miguel de Taipu","São Sebastião de Lagoa de Roça","São Sebastião do Umbuzeiro","Sapé","Seridó","Serra Branca","Serra da Raiz","Serra Grande","Serra Redonda","Serraria","Sertãozinho","Sobrado","Solânea","Soledade","Sossêgo","Sousa","Sumé","Tacima","Taperoá","Tavares","Teixeira","Tenório","Triunfo","Uiraúna","Umbuzeiro","Várzea","Vertente do Lerio","Vieirópolis","Vista Serrana","Zabelê"};
        String[] pe = {"Abreu e Lima","Afogados da Ingazeira","Afrânio","Agrestina","Agua Preta","Aguas Belas","Alagoinha","Aliança","Altinho","Amaraji","Angelim","Araçoiaba","Araripina","Arcoverde","Barra de Guabiraba","Barreiros","Belém de Maria","Belem de São Francisco","Belo Jardim","Betânia","Bezerros","Bodocó","Bom Conselho","Bom Jardim","Bonito","Brejão","Brejinho","Brejo da Madre de Deus","Buenos Aires","Buíque","Cabo de Santo Agostinho","Cabrobó","Cachoeirinha","Caetés","Calçados","Calumbi","Camaragibe","Camocim de São Félix","Camutanga","Canhotinho","Capoeira","Carnaíba","Carnaubeira da Penha","Carpina","Caruaru","Casinhas","Catende","Cedro","Chã de Alegria","Chã Grande","Condado","Correntes","Cortes","Craíbas","Cumaru","Cupira","Custódia","Dormentes","Escada","Exu","Feira Nova","Fernando de Noronha","Ferreiros","Flores","Floresta","Frei Miguelinho","Gameleira","Garanhuns","Girau do Ponciano","Glória do Goitá","Goiana","Granito","Gravatá","Iati","Ibimirim","Ibirajuba","Igarassu","Igarassu","Iguaraci","Ilha de Itamaracá","Inajá","Ingazeira","Ipojuca","Ipubi","Itacurubá","Itaíba","Itambé","Itapetim","Itapissuma","Itaquitinga","Jaboatão dos Guararapes","Jaqueira","Jataúba","Jatobá","João Alfredo","Joaquim Nabuco","Jucati","Jupi","Jurema","Lagoa de Itaenga","Lagoa do Carro","Lagoa do Ouro","Lagoa dos Gatos","Lagoa Grande","Lajedo","Limoeiro","Macaparana","Machados","Manari","Maraial","Mirandiba","Moreilandia","Moreno","Nazaré da Mata","Olinda","Orobó","Orocó","Ouricuri","Palmares","Palmeirina","Panelas","Paranatama","Parnamirim","Passira","Paudalho","Paulista","Pedra","Pesqueira","Petrolandia","Petrolina","Pilões","Poção","Poção","Pombos","Primavera","Quipapá","Quixabá","Recife","Riacho das Almas","Ribeirão","Rio Formoso","Sairé","Salgadinho","Salgueiro","Saloá","Sanharó","Santa Cruz","Santa Cruz da Baixa Verde","Santa Cruz do Capibaribe","Santa Filomena","Santa Maria da Boa Vista","Santa Maria do Cambucá","Santa Terezinha","São Benedito do Sul","São Bento do Una","São Caetano","São João","São Joaquim do Monte","São José da Coroa Grande","São José do Belmonte","São José do Egito","São Vicente Férrer","Serra Talhada","Serrita","Sertânia","Sirinhaém","Solidão","Surubim","Tabira","Tacaimbó","Tacaratu","Tamandaré","Taquaritinga do Norte","Terezinha","Terra Nova","Timbauba","Toritama","Tracunhaém","Trindade","Triunfo","Tupanatinga","Tuparetama","Venturosa","Verdejante","Vertentes","Vicência","Vitória de Santo Antão","Xexeu"};
        String[] rn = {"Acari","Açu","Afonso Bezerra","Água Nova","Alexandria","Almino Afonso","Alto do Rodrigues","Angicos","Antônio Martins","Apodi","Areia Branca","Arês","Baía Formosa","Baraúna","Barcelona","Bento Fernandes","Boa Saúde","Bodó","Bom Jesus","Brejinho","Caiçara do Norte","Caiçara do Rio do Vento","Caicó","Campo Grande","Campo Redondo","Canguaretama","Caraúbas","Carnaúba dos Dantas","Carnaubais","Ceará-Mirim","Cerro Corá","Coronel Ezequiel","Coronel João Pessoa","Cruzeta","Currais Novos","Doutor Severiano","Encanto","Equador","Espírito Santo","Extremoz","Felipe Guerra","Fernando Pedroza","Florânia","Francisco Dantas","Frutuoso Gomes","Galinhos","Goianinha","Governador Dix-Sept Rosado","Grossos","Guamaré","Ielmo Marinho","Ipanguaçu","Ipueira","Itajá","Itaú","Jaçanã","Jandaíra","Janduís","Japi","Jardim de Angicos","Jardim de Piranhas","Jardim do Seridó","João Câmara","João Dias","José da Penha","Jucurutu","Jundiá","Lagoa Danta","Lagoa de Pedras","Lagoa de Velhos","Lagoa Nova","Lagoa Salgada","Lajes","Lajes Pintadas","Lucrécia","Luís Gomes","Macaíba","Macau","Major Sales","Marcelino Vieira","Martins","Maxaranguape","Messias Targino","Montanhas","Monte Alegre","Monte das Gameleiras","Mossoró","Natal","Nísia Floresta","Nova Cruz","Olho-dÁgua do Borges","Ouro Branco","Paraná","Paraú","Parazinho","Parelhas","Parnamirim","Passa-e-Fica","Passagem","Patu","Pau dos Ferros","Pedra Grande","Pedra Preta","Pedro Avelino","Pedro Velho","Pendências","Pilões","Poço Branco","Portalegre","Porto do Mangue","Pureza","Rafael Fernandes","Rafael Godeiro","Riacho da Cruz","Riacho de Santana","Riachuelo","Rio do Fogo","Rodolfo Fernandes","Ruy Barbosa","Santa Cruz","Santa Maria","Santana do Matos","Santana do Seridó","Santo Antônio","São Bento do Norte","São Bento do Trairi","São Fernando","São Francisco do Oeste","São Gonçalo do Amarante","São João do Sabugi","São José de Mipibu","São José do Campestre","São José do Seridó","São Miguel","São Miguel do Gostoso","São Paulo do Potengi","São Pedro","São Rafael","São Tomé","São Vicente","Senador Elói de Souza","Senador Georgino Avelino","Serra Caiada (Presidente Juscelino)","Serra de São Bento","Serra do Mel","Serra Negra do Norte","Serrinha","Serrinha dos Pintos","Severiano Melo","Sítio Novo","Taboleiro Grande","Taipu","Tangará","Tenente Ananias","Tenente Laurentino Cruz","Tibau","Tibau do Sul","Timbaúba dos Batistas","Touros","Triunfo Potiguar","Umarizal","Upanema","Várzea","Venha-Ver","Vera Cruz","Viçosa","Vila Flor"};

        switch(estado){
            case "Rio Grande do Norte":return rn;
            case "Paraíba":return pb;
            case "Pernambuco":return pe;
            default: return vazio;
        }
    }

    public String siglaEstado(String nomeEstado){
        switch(nomeEstado){
            case "Rio Grande do Norte":return "rn";
            case "Paraíba":return "pb";
            case "Pernambuco":return "pe";
            default: return "";
        }
    }


    public String converteTextoUrlComTracos(String url){
        //url=url.replaceAll(" ", "%20");
        url=url.toLowerCase();
        url=url.replaceAll(" ", "-");
        url=url.replaceAll("ç", "c");
        url= Normalizer.normalize(url, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        return url;
    }

    public String mensagemCurta() {
        return "Recurso em desenvolvimento";
    }


    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name

            if (ipAddr.equals("")) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }

    }

    public static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }



    public Double calculaDistancia(Double lat1, Double lon1, Double lat2, Double lon2) {
        //double lat1=-7.2161685;
        //double lon1=-35.87775320000003;
        //double lat2=-7.234097588807344;
        //double lon2=-35.86600416339934;

        // K= Kilometros N-Milhas
        char unit='K';

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return dist;
    }

    private double deg2rad(double deg) {return (deg * Math.PI / 180.0);}

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
/*::  Função para converter Radianos  para graus decimais
/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }



    public double arredonda(double value, int scale) {
        BigDecimal bd1 = new BigDecimal(value);
        BigDecimal bd2;

        if (casasDecimais(value)>scale) {
            bd2 = bd1.setScale(scale, BigDecimal.ROUND_HALF_UP);
        } else {
            bd2 = bd1.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
        }
        return bd2.doubleValue();
    }


    public Integer casasDecimais(Double numero){
        String[] splitter = numero.toString().split("\\.");
        //splitter[0].length();   // Before Decimal Count
        Integer decimais=splitter[1].length();
        return decimais;
    }


////////////////////////////////////////////////////// mascara /////

    public static int tamanhoMaximo(String mascara) {
        String novaString = removeCaracteresEspeciais(mascara);
        return novaString.length();
    }


    public static String removeCaracteresEspeciais(String str) {
        String[] caracteresEspeciais = {"\\.", ",", "-", ":", "\\(", "\\)", "ª", "\\|", "\\\\", "°", "\\/"};

        for (int i = 0; i < caracteresEspeciais.length; i++) {
            str = str.replaceAll(caracteresEspeciais[i], "");
        }

        return str;
    }


    public String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

            return currentTimeStamp;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

    public static class MascaraMonetaria implements TextWatcher {

        final EditText campo;

        public MascaraMonetaria(EditText campo) {
            super();
            this.campo = campo;
        }

        private boolean isUpdating = false;
        private NumberFormat nf = NumberFormat.getCurrencyInstance();

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int after) {
            if (isUpdating) {
                isUpdating = false;
                return;
            }
            isUpdating = true;
            String str = s.toString();
            boolean hasMask = ((str.indexOf("R$ ") > -1 || str.indexOf("$") > -1) &&
                    (str.indexOf(".") > -1 || str.indexOf(",") > -1));
            if (hasMask) {
                str = str.replaceAll("[R$]", "").replaceAll("[,]", "")
                        .replaceAll("[.]", "");
            }

            try {
                str = nf.format(Double.parseDouble(str) / 100);
                campo.setText(str);
                campo.setSelection(campo.getText().length());
            } catch (NumberFormatException e) {
                s = "";
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

}
