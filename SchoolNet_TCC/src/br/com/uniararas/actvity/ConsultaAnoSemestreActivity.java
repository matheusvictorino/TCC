package br.com.uniararas.actvity;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.services.ConsultaAnoSemestreService;
import br.com.uniararas.util.Constantes;

public class ConsultaAnoSemestreActivity extends ListActivity {

	private ConsultaAnoSemestreService consultaAnoSemestreService = new ConsultaAnoSemestreService();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		
		TextView textNome = (TextView) findViewById(R.id.textView10);
		TextView textRa = (TextView) findViewById(R.id.textView11);
		TextView textCurso = (TextView) findViewById(R.id.textView1);
			
		textNome.setText(MenuActivity.aluno.nomealuno);
		textRa.setText(MenuActivity.aluno.ra);
		textCurso.setText(MenuActivity.aluno.email);
		
		Intent intent = getIntent();
		final String tipoConsulta = intent.getStringExtra("consulta");
		
		try{
			ArrayList<HashMap<String, String>> listaAnoLetivo = consultaAnoSemestreService.obterAnoSemestre();
			
			ListAdapter adapter = new SimpleAdapter(this, listaAnoLetivo,
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
	                if(tipoConsulta.equals("notas")){
	                	in = new Intent(getApplicationContext(), ConsultaNotasActivity.class);
	                }else{
	                	in = new Intent(getApplicationContext(), ConsultaFaltasActivity.class);
	                }
	        		in.putExtra("aluno", new Gson().toJson(MenuActivity.aluno));
	        		in.putExtra("anoletivo", anoletivo);
	        		in.putExtra("semestre", semestre);
	        		startActivity(in);
	            }
	        });
		}catch(Exception e){
			Toast toast = Toast.makeText(context, e.getMessage(), duration);
			toast.show();getApplicationContext();
		}
	}
	
    public boolean onClickLogout(View view) {
    	WebServiceCall webServiceCall = WebServiceCall.getInstance();
    	webServiceCall.destroyInstance();
    	Intent in = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(in);
		return true;
    }
}
