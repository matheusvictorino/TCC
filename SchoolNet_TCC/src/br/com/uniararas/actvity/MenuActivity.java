package br.com.uniararas.actvity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import br.com.uniararas.beans.Aluno;
import br.com.uniararas.resources.WebServiceCall;

import com.google.gson.Gson;

public class MenuActivity extends Activity {
	
	public static Aluno aluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		TextView textNome = (TextView) findViewById(R.id.textView10);
		TextView textRa = (TextView) findViewById(R.id.textView11);
		TextView textCurso = (TextView) findViewById(R.id.textView1);
		

		Intent intent = getIntent();
		String alunoJson = intent.getStringExtra("aluno");
		Gson gson = new Gson();
		aluno = gson.fromJson( alunoJson , Aluno.class);
		textNome.setText(aluno.nomealuno);
		textRa.setText(aluno.ra);
		textCurso.setText(aluno.email);
					
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	public void onClickConsultarNotas(View view) {
		Intent in = new Intent(getApplicationContext(), ConsultaAnoSemestreActivity.class);
		in.putExtra("aluno", new Gson().toJson(aluno));
		in.putExtra("consulta", "notas");
		startActivity(in);

	}
	
	public void onClickConsultarFaltas(View view) {
		Intent in = new Intent(getApplicationContext(), ConsultaFaltasActivity.class);
		in.putExtra("aluno", new Gson().toJson(aluno));
		in.putExtra("anoletivo", "");
		in.putExtra("semestre", "");
		startActivity(in);

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
}
