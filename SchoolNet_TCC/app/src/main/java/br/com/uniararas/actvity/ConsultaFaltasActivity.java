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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.services.ConsultaFaltasService;
import br.com.uniararas.util.Constantes;
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
public class ConsultaFaltasActivity extends ListActivity {

	private ConsultaFaltasService consultaFaltasService = new ConsultaFaltasService();
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
		

		Intent intent = getIntent();
		String anoletivo = intent.getStringExtra("anoletivo");
		String semestre = intent.getStringExtra("semestre");
		cod_curso = intent.getStringExtra("cod_curso");
		cod_fac = intent.getStringExtra("cod_fac");
		ano_ingresso = intent.getStringExtra("ano_ingresso");
		cursoNome = intent.getStringExtra("descricao_curso");
		nomeCurso.setText(cursoNome);
		textAnoSemestre.setText(semestre + "º Semestre do ano de " + anoletivo);
		
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
	         private ArrayList<HashMap<String, String>> listaMaterias;
	         
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
	    			listaMaterias = new ArrayList<HashMap<String,String>>();
	    			listaMaterias = consultaFaltasService.obterFaltas(cod_fac,cod_curso,ano_ingresso,anoletivo,semestre);
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
	        	ListAdapter adapter = new SimpleAdapter(context, listaMaterias,
						R.layout.list_item_faltas,
						new String[] { Constantes.TAG_NOME_MATERIA, Constantes.TAG_NUMERO_FALTAS, Constantes.TAG_NUMERO_FALTAS_LIMITE }, new int[] {
								R.id.materia, R.id.numerofaltas, R.id.limitefaltas});
		
				setListAdapter(adapter);
	        }
	 
	    }
}
