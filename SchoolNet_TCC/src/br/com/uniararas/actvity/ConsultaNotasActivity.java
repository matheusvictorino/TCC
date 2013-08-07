package br.com.uniararas.actvity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import br.com.uniararas.services.ConsultarNotasService;
import br.com.uniararas.util.Constantes;

public class ConsultaNotasActivity extends ListActivity {

	private ConsultarNotasService consultarNotasService = new ConsultarNotasService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		
		TextView textNome = (TextView) findViewById(R.id.textView10);
		TextView textRa = (TextView) findViewById(R.id.textView11);
		TextView textCurso = (TextView) findViewById(R.id.textView1);
		textCurso.setText("Sistemas de Informação");
		textNome.setText("Gerson Donscoi Junior");
		textRa.setText("62677");

		try {
		ArrayList<HashMap<String, String>> listaMaterias = consultarNotasService.obterNotas();
		
		ListAdapter adapter = new SimpleAdapter(this, listaMaterias,
				R.layout.list_item_notas,
				new String[] { Constantes.TAG_NOME_MATERIA, Constantes.TAG_PRIMEIRA_NOTA, Constantes.TAG_NOTA_SPA, Constantes.TAG_SEGUNDA_NOTA, Constantes.TAG_MEDIA_FINAL }, new int[] {
						R.id.materia, R.id.primeiranota, R.id.notaspa, R.id.segundanota, R.id.mediafinal });

		setListAdapter(adapter);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}