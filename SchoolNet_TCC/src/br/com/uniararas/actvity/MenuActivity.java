package br.com.uniararas.actvity;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends Activity {
	
	private String aluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		TextView textNome = (TextView) findViewById(R.id.textView10);
		TextView textRa = (TextView) findViewById(R.id.textView11);
		TextView textCurso = (TextView) findViewById(R.id.textView1);
		textCurso.setText("Sistemas de Informação");

		Intent intent = getIntent();
		aluno = intent.getStringExtra("aluno");
		String nome = null;
		String ra=null;
		
		try{
			JSONArray jArray = new JSONArray(aluno);
			JSONObject json_data = new JSONObject();
			for(int i=0;i<jArray.length();i++){
				json_data = jArray.getJSONObject(i);
				ra = json_data.getString("RA");
				nome = json_data.getString("NOME");
			}
			
			textNome.setText(nome);
			textRa.setText(ra);
					
			}catch(Exception e){
			Log.e("FUDEU:", e.getMessage());
			Log.e("Valor:", aluno);

			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	public void onClickConsultarNotas(View view) {
		Intent in = new Intent(getApplicationContext(), ConsultaNotasActivity.class);
		startActivity(in);

	}
	
	public void onClickConsultarFaltas(View view) {
		Intent in = new Intent(getApplicationContext(), ConsultaFaltasActivity.class);
		startActivity(in);

	}

}
