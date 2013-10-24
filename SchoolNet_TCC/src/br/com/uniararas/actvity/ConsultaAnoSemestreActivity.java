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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		
		TextView textNome = (TextView) findViewById(R.id.textView10);
		TextView textRa = (TextView) findViewById(R.id.textView11);
		TextView textCurso = (TextView) findViewById(R.id.textView1);
			
		textNome.setText(MenuActivity.aluno.nomealuno);
		textRa.setText(MenuActivity.aluno.ra);
		textCurso.setText(MenuActivity.aluno.descricao_curso);
		
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
	    		listaAnoLetivo = consultaAnoSemestreService.obterAnoSemestre();
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
                	in = new Intent(getApplicationContext(), ConsultaNotasActivity.class);
	        		in.putExtra("aluno", new Gson().toJson(MenuActivity.aluno));
	        		in.putExtra("anoletivo", anoletivo);
	        		in.putExtra("semestre", semestre);
	        		startActivity(in);
	            }
	        });
    		
        }
 
    }
}
