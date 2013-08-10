package br.com.uniararas.actvity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import br.com.uniararas.beans.Aluno;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MenuActivity extends Activity {
	
	public static List<Aluno> aluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		TextView textNome = (TextView) findViewById(R.id.textView10);
		TextView textRa = (TextView) findViewById(R.id.textView11);
		TextView textCurso = (TextView) findViewById(R.id.textView1);
		

		Intent intent = getIntent();
		String alunoJson = intent.getStringExtra("aluno");
		
		java.lang.reflect.Type listType = new TypeToken<List<Aluno>>() {}.getType();
		aluno = new Gson().fromJson( alunoJson.trim() , listType);
			
		textNome.setText(aluno.get(0).getNome());
		textRa.setText(aluno.get(0).getRa());
		textCurso.setText("Sistemas de Informação");
					
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	public void onClickConsultarNotas(View view) {
		Intent in = new Intent(getApplicationContext(), ConsultaNotasActivity.class);
		in.putExtra("aluno", new Gson().toJson(aluno.get(0)));
		startActivity(in);

	}
	
	public void onClickConsultarFaltas(View view) {
		Intent in = new Intent(getApplicationContext(), ConsultaFaltasActivity.class);
		in.putExtra("aluno", new Gson().toJson(aluno.get(0)));
		startActivity(in);

	}

}
