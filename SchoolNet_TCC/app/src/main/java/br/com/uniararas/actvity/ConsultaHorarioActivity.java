package br.com.uniararas.actvity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import br.com.uniararas.adapters.GrupoHorarioAdapter;
import br.com.uniararas.beans.GrupoHorario;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.services.ConsultaHorarioService;
import br.com.uniararas.util.Constantes;

public class ConsultaHorarioActivity extends ListActivity {

    private ConsultaHorarioService consultarHorarioService = new ConsultaHorarioService();
    private String cod_curso;
    private String cod_fac;
    private String ano_ingresso;
    private String cursoNome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        TextView textNome = (TextView) findViewById(R.id.textView10);
        TextView textRa = (TextView) findViewById(R.id.textView11);
        TextView textEmail = (TextView) findViewById(R.id.textView1);
        TextView textAnoSemestre = (TextView) findViewById(R.id.ano_semestre);
        TextView nomeCurso = (TextView) findViewById(R.id.nome_curso);

        View barra_inf = (View) findViewById(R.id.barra_inf_curso);

        barra_inf.setVisibility(View.VISIBLE);
        textNome.setText(MenuActivity.aluno.nomealuno);
        textRa.setText(MenuActivity.aluno.ra);
        textEmail.setText(MenuActivity.aluno.email);


        String anoletivo = "nhenhenhe";
        String semestre = "mimimi";
        nomeCurso.setText(cursoNome);

        try{
            ChamadaWebService chamadaWebService = new ChamadaWebService(this,anoletivo,semestre);
            chamadaWebService.execute(0,0,0);
        }catch(Exception e){
            Intent intentErro = new Intent(getApplicationContext(), ErroActivity.class);
            intentErro.putExtra("msgErro",  e.getMessage());
            startActivity(intentErro);
            finish();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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

    public class ChamadaWebService extends AsyncTask<Integer, String, String> {

        private ProgressDialog progress;
        private Context context;
        private String anoletivo;
        private String semestre;
        private ArrayList<GrupoHorario> listaHorarios;

        public ChamadaWebService(Context context,String anoletivo,String semestre) {
            this.context = context;
            this.anoletivo = anoletivo;
            this.semestre = semestre;
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
                listaHorarios = new ArrayList<GrupoHorario>();
                listaHorarios = consultarHorarioService.obterHorario(cod_fac,cod_curso,ano_ingresso,anoletivo,semestre);
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
            GrupoHorarioAdapter adapter = new GrupoHorarioAdapter(context, listaHorarios);

            ListView listView = (ListView) findViewById(android.R.id.list);
            listView.setAdapter(adapter);

//            ListAdapter adapter = new SimpleAdapter(context, listaHorarios,
//                    R.layout.list_item_horario_aula,
//                    new String[] { "dia", "aulas", }, new int[] {
//                    R.id.dia});

//            setListAdapter(adapter);
        }

    }
}