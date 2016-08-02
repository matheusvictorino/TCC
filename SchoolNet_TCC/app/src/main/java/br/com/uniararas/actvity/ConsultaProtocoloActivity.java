package br.com.uniararas.actvity;
/**
 *   Copyright 2013 Gerson Donscoi Junior, Leandro Motta M. Oliveira
 *
 *   Este arquivo é parte do programa SchoolNet Mobile
 *
 *
 *   SchoolNet Mobile é um software livre; você pode redistribuí-lo e/ou
 *
 *   modificá-lo dentro dos termos da Licença Pública Geral GNU como
 *
 *   publicada pela Fundação do Software Livre (FSF); na versão 2 da
 *
 *   Licença, ou (na sua opinião) qualquer versão.
 *
 *
 *
 *   Este programa é distribuído na esperança de que possa ser  útil,
 *
 *   mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 *
 *   MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
 *
 *   Licença Pública Geral GNU para maiores detalhes.
 *
 *
 *
 *   Você deve ter recebido uma cópia da Licença Pública Geral GNU
 *
 *   junto com este programa, se não, escreva para a Fundação do Software
 *
 *   Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
import java.util.ArrayList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import br.com.uniararas.adapters.ProtocoloAdapter;
import br.com.uniararas.beans.Protocolo;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.services.ConsultaProtocoloService;
import br.com.uniararas.util.Constantes;

public class ConsultaProtocoloActivity extends ListActivity {

    private ConsultaProtocoloService consultaProcoloService = new ConsultaProtocoloService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        TextView textNome = (TextView) findViewById(R.id.textView10);
        TextView textRa = (TextView) findViewById(R.id.textView11);
        TextView textEmail = (TextView) findViewById(R.id.textView1);
        TextView textCurso = (TextView) findViewById(R.id.ano_semestre);

        textNome.setText(MenuActivity.aluno.nomealuno);
        textRa.setText(MenuActivity.aluno.ra);
        textEmail.setText(MenuActivity.aluno.email);
        textCurso.setText("");

        Intent intent = getIntent();

        try{
            ChamadaWebService chamadaWebService = new ChamadaWebService(this);
            chamadaWebService.execute(0,0,0);
        }catch(Exception e){
            Intent intentErro = new Intent(getApplicationContext(), ErroActivity.class);
            intentErro.putExtra("msgErro",  e.getMessage());
            startActivity(intentErro);
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.curso, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent in = new Intent(getApplicationContext(), SobreActivity.class);
                startActivity(in);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onClickLogout(View view) {
        WebServiceCall webServiceCall = WebServiceCall.getInstance();
        webServiceCall.destroyInstance();
        Intent in = new Intent(getApplicationContext(), LoginActivity.class);
        in.putExtra("finish", true);
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
        return true;
    }

    public class ChamadaWebService extends AsyncTask<Integer, String, String> {

        private ProgressDialog progress;
        private Context context;
        ArrayList<Protocolo> listaProtocolos;

        public ChamadaWebService(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(context);
            progress.setMessage("Aguarde...");
            progress.show();
        }

        @Override
        protected String doInBackground(Integer... paramss) {
            try{
                listaProtocolos = new ArrayList<Protocolo>();
                listaProtocolos = consultaProcoloService.obterProtocolos();
                return "SUCESSO";
            }catch(Exception e){
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (!result.equals("SUCESSO")){
                Intent intentErro = new Intent(getApplicationContext(), ErroActivity.class);
                intentErro.putExtra("msgErro",  result);
                startActivity(intentErro);
                finish();
            }

            progress.dismiss();

            ProtocoloAdapter adapter = new ProtocoloAdapter(context, listaProtocolos);

            ListView listView = (ListView) findViewById(android.R.id.list);
            listView.setAdapter(adapter);
        }
    }
}
