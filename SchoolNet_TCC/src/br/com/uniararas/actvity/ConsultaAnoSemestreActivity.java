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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.services.ConsultaAnoSemestreService;
import br.com.uniararas.util.Constantes;

import com.google.gson.Gson;

public class ConsultaAnoSemestreActivity extends ListActivity {

	private ConsultaAnoSemestreService consultaAnoSemestreService = new ConsultaAnoSemestreService();
	private String cod_curso;
	private String cod_fac;
	private String ano_ingresso;
	private String consulta;
	private String cursoNome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		
		TextView textNome = (TextView) findViewById(R.id.textView10);
		TextView textRa = (TextView) findViewById(R.id.textView11);
		TextView textEmai = (TextView) findViewById(R.id.textView1);
		TextView textCurso = (TextView) findViewById(R.id.ano_semestre);
		TextView nomeCurso = (TextView) findViewById(R.id.nome_curso);
		
		View barra_inf = (View) findViewById(R.id.barra_inf_curso);
		
		barra_inf.setVisibility(View.VISIBLE);
		textNome.setText(MenuActivity.aluno.nomealuno);
		textRa.setText(MenuActivity.aluno.ra);
		textEmai.setText(MenuActivity.aluno.email);
		textCurso.setText(Constantes.SELECIONE_ANO_SEMESTRE);
		
		Intent intent = getIntent();
		cod_curso = intent.getStringExtra("cod_curso");
		cod_fac = intent.getStringExtra("cod_fac");
		ano_ingresso = intent.getStringExtra("ano_ingresso");
		consulta = intent.getStringExtra("consulta");
		cursoNome = intent.getStringExtra("descricao_curso");
		nomeCurso.setText(cursoNome);
		
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
         ArrayList<HashMap<String, String>> listaAnoLetivo ;
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
				listaAnoLetivo = new ArrayList<HashMap<String,String>>();
	    		listaAnoLetivo = consultaAnoSemestreService.obterAnoSemestre(cod_fac,cod_curso,ano_ingresso);
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
        	ListAdapter adapter = new SimpleAdapter(context, listaAnoLetivo,
					R.layout.list_item_semestre,
					new String[] { Constantes.TAG_ANO, Constantes.TAG_SEMESTRE }, new int[] {
							R.id.anoletivo, R.id.semestre});
	
			setListAdapter(adapter);
			
			ListView lv = getListView();
			 
	        lv.setOnItemClickListener(new OnItemClickListener() {
	 
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view,
	                    int position, long id) {
	                String anoletivo = ((TextView) view.findViewById(R.id.anoletivo)).getText().toString();
	                String semestre = ((TextView) view.findViewById(R.id.semestre)).getText().toString();
	                Intent in = null;
	                if(consulta.equals("notas"))
	                	in = new Intent(getApplicationContext(), ConsultaNotasActivity.class);
	                else
	                	in = new Intent(getApplicationContext(), ConsultaFaltasActivity.class);
	        		in.putExtra("aluno", new Gson().toJson(MenuActivity.aluno));
	        		in.putExtra("anoletivo", anoletivo);
	        		in.putExtra("semestre", semestre);
	        		in.putExtra("cod_curso", cod_curso);
	        		in.putExtra("cod_fac", cod_fac);
	        		in.putExtra("ano_ingresso", ano_ingresso);
	        		in.putExtra("descricao_curso", cursoNome);
	        		startActivity(in);
	            }
	        });
    		
        }
 
    }
}
