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
import br.com.uniararas.services.ConsultaFaltasService;
import br.com.uniararas.util.Constantes;

public class ConsultaFaltasActivity extends ListActivity {

	private ConsultaFaltasService consultaFaltasService = new ConsultaFaltasService();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		
		TextView textNome = (TextView) findViewById(R.id.textView10);
		TextView textRa = (TextView) findViewById(R.id.textView11);
		TextView textCurso = (TextView) findViewById(R.id.textView1);
			
		textNome.setText(MenuActivity.aluno.get(0).getNome());
		textRa.setText(MenuActivity.aluno.get(0).getRa());
		textCurso.setText("Sistemas de Informação");

		try{
			ArrayList<HashMap<String, String>> listaMaterias = consultaFaltasService.obterFaltas(MenuActivity.aluno.get(0));
			
			ListAdapter adapter = new SimpleAdapter(this, listaMaterias,
					R.layout.list_item_faltas,
					new String[] { Constantes.TAG_NOME_MATERIA, Constantes.TAG_NUMERO_FALTAS, Constantes.TAG_NUMERO_FALTAS_LIMITE }, new int[] {
							R.id.materia, R.id.numerofaltas, R.id.limitefaltas});
	
			setListAdapter(adapter);
		}catch(Exception e){
			Toast toast = Toast.makeText(context, e.getMessage(), duration);
			toast.show();getApplicationContext();
		}
	}
}
