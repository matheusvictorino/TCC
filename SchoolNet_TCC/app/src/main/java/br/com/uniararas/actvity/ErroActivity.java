package br.com.uniararas.actvity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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
public class ErroActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_erro);
		
		TextView textViewErro =  (TextView) findViewById(R.id.textView2);
		
		Intent intent = getIntent();
		String txtErro = intent.getStringExtra("msgErro");
		
		textViewErro.setText(txtErro);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.erro, menu);
		return true;
	}
	
	public void onClickMenu(View view) {
		finish();
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
}
