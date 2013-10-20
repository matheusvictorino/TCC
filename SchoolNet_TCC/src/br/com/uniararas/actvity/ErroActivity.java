package br.com.uniararas.actvity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

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

}
