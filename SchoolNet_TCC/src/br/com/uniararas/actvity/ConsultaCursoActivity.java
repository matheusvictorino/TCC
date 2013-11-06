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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.services.ConsultaCursoService;
import br.com.uniararas.util.Constantes;

import com.google.gson.Gson;

public class ConsultaCursoActivity extends ListActivity {

	private ConsultaCursoService consultaCursoService = new ConsultaCursoService();
	private String consulta;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_notas);
		
		TextView textNome = (TextView) findViewById(R.id.textView10);
		TextView textRa = (TextView) findViewById(R.id.textView11);
		TextView textEmail = (TextView) findViewById(R.id.textView1);
		TextView textCurso = (TextView) findViewById(R.id.ano_semestre);
			
		textNome.setText(MenuActivity.aluno.nomealuno);
		textRa.setText(MenuActivity.aluno.ra);
		textEmail.setText(MenuActivity.aluno.email);
		textCurso.setText(Constantes.SELECIONE_CURSO);
		
		Intent intent = getIntent();
		consulta = intent.getStringExtra("consulta");
		
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
	         ArrayList<HashMap<String, String>> listaCurso ;
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
	    			listaCurso = new ArrayList<HashMap<String,String>>();
	    			listaCurso = consultaCursoService.obterCurso();
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
	        	ListAdapter adapter = new SimpleAdapter(context, listaCurso,
						R.layout.list_item_curso,
						new String[] { Constantes.TAG_CURSO,Constantes.TAG_COD_CURSO,Constantes.TAG_COD_FAC,Constantes.TAG_ANOR_INGRESSO}, new int[] {
								R.id.curso,R.id.cod_curso,R.id.cod_fac,R.id.ano_ingresso});
		
				setListAdapter(adapter);
				
				ListView lv = getListView();
				 
		        lv.setOnItemClickListener(new OnItemClickListener() {
		 
		            @Override
		            public void onItemClick(AdapterView<?> parent, View view,
		                    int position, long id) {
		                String cod_curso = ((TextView) view.findViewById(R.id.cod_curso)).getText().toString();
		                String cod_fac = ((TextView) view.findViewById(R.id.cod_fac)).getText().toString();
		                String ano_ingresso = ((TextView) view.findViewById(R.id.ano_ingresso)).getText().toString();
		                
		                Intent in = null;
	                	in = new Intent(getApplicationContext(), ConsultaAnoSemestreActivity.class);
		        		in.putExtra("aluno", new Gson().toJson(MenuActivity.aluno));
		        		in.putExtra("consulta", consulta);
		        		in.putExtra("cod_curso", cod_curso);
		        		in.putExtra("cod_fac", cod_fac);
		        		in.putExtra("ano_ingresso", ano_ingresso);
		        		startActivity(in);
		            }
		        });
	    		
	        }
	 
	    }

}
