package br.com.uniararas.actvity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import br.com.uniararas.beans.Aluno;
import br.com.uniararas.resources.WebServiceCall;

import com.google.gson.Gson;
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
public class MenuActivity extends Activity {
	
	public static Aluno aluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		TextView textNome = (TextView) findViewById(R.id.textView10);
		TextView textRa = (TextView) findViewById(R.id.textView11);
		TextView textEmail = (TextView) findViewById(R.id.textView1);
		

		Intent intent = getIntent();
		String alunoJson = intent.getStringExtra("aluno");
		Gson gson = new Gson();
		aluno = gson.fromJson( alunoJson , Aluno.class);
		textNome.setText(aluno.nomealuno);
		textRa.setText(aluno.ra);
		textEmail.setText(aluno.email);
					
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
	
	public void onClickConsultarNotas(View view) {
		Intent in = new Intent(getApplicationContext(), ConsultaCursoActivity.class);
		in.putExtra("aluno", new Gson().toJson(aluno));
		in.putExtra("consulta", "notas");
		startActivity(in);

	}

	public void onClickConsultarProtocolos(View view) {
		Intent in = new Intent(getApplicationContext(), ConsultaProtocoloClickActivity.class);
		in.putExtra("aluno", new Gson().toJson(aluno));
		in.putExtra("consulta", "historico");
		startActivity(in);

	}

	public void onClickConsultarHistorico(View view) {
		Intent in = new Intent(getApplicationContext(), ConsultaCursoActivity.class);
		in.putExtra("aluno", new Gson().toJson(aluno));
		in.putExtra("consulta", "historico");
		startActivity(in);

	}

	public void onClickConsultarHorarios(View view) {
		Intent in = new Intent(getApplicationContext(), ConsultaCursoActivity.class);
		in.putExtra("aluno", new Gson().toJson(aluno));
		in.putExtra("consulta", "horario");
		startActivity(in);

	}
	
	public void onClickConsultarFaltas(View view) {
		Intent in = new Intent(getApplicationContext(), ConsultaCursoActivity.class);
		in.putExtra("aluno", new Gson().toJson(aluno));
		in.putExtra("consulta", "faltas");
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
