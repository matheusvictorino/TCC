package br.com.uniararas.actvity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import br.com.uniararas.util.Constantes;
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
public class InformacoesActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_informacoes);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.informacoes, menu);
		return true;
	}

	 public boolean onClickSobre(View view) {
		 Intent in = new Intent(getApplicationContext(), SobreActivity.class);
     	startActivity(in);
     	return true;
	 }
	 
	 public boolean onClickLocalizacao(View view) {
		 Intent in = new Intent(getApplicationContext(), LocalizacaoActivity.class);
     	startActivity(in);
     	return true;
	 }
	 
	 public boolean onClickHistoria(View view) {
		 Intent in = new Intent(getApplicationContext(), HistoriaActivity.class);
     	startActivity(in);
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
		
		public boolean onClickFB(View view) {
			Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(Constantes.LINK_FACEBOOK_SI));
	        startActivity(i);
	     	return true;
		 }
}
