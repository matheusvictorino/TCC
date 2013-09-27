package br.com.uniararas.actvity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
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
		
		try{
			ArrayList<HashMap<String, String>> listaAnoLetivo = consultaAnoSemestreService.obterAnoSemestre();
			
			ListAdapter adapter = new SimpleAdapter(this, listaAnoLetivo,
					R.layout.list_item_semestre,
					new String[] { Constantes.TAG_ANO, Constantes.TAG_SEMESTRE }, new int[] {
							R.id.anoletivo, R.id.semestre});
	
			setListAdapter(adapter);
		}catch(Exception e){
			Toast toast = Toast.makeText(context, e.getMessage(), duration);
			toast.show();getApplicationContext();
		}
	}
}
