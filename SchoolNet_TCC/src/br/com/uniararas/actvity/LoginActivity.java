package br.com.uniararas.actvity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.uniararas.beans.Aluno;
import br.com.uniararas.services.LoginService;

public class LoginActivity extends Activity {

	EditText ra;
	EditText senha;
	String result[];
	private TextView textCarregando;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        ra = (EditText)findViewById(R.id.editText1);
        senha = (EditText)findViewById(R.id.editText2);
        
        textCarregando = (TextView)findViewById(R.id.textView1);
    }

    public class ChamadaWebService extends AsyncTask<Integer, String, String> {
 
        @Override
        protected void onPreExecute() {
        	textCarregando.setVisibility(TextView.VISIBLE);
        	ra.setEnabled(false);
        	senha.setEnabled(false);
        }
 
        @Override
        protected String doInBackground(Integer... paramss) {
        	
        	Aluno aluno = new Aluno();
    		aluno.ra = ra.getText().toString(); 
    		
    		try{
    			aluno.setSenha(senha.getText().toString());
    			LoginService loginService = new LoginService();
    			result = loginService.autenticarUsuario(aluno);
    			if(result != null){
    				Intent in = new Intent(getApplicationContext(), MenuActivity.class);
    				in.putExtra("aluno", result[1]);
    				startActivity(in);
    				return "SUCESSO";
    			}else {
    				return "Ocorreu um erro.";	
    			}
    		}catch(Exception e){
    			return e.getMessage();
    		}
        }
 
        @Override
        protected void onPostExecute(String result) {
        	if (!result.equals("SUCESSO"))
        		toast(result);
        	textCarregando.setVisibility(TextView.INVISIBLE);
        	ra.setEnabled(true);
        	senha.setEnabled(true);
    		
        }
 
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }*/
    
	public boolean onClickLogin(View view) {
		if(!testConnection()){
			toast("Você Precisa estar conectado a internet antes de continuar");
			return false;
		}
		
		if(ra.getText().toString().isEmpty()){
			ra.setError(Html.fromHtml("<font color='red'>Por Favor digite seu RA</font>"));
			return false;
		}
		
		if(senha.getText().toString().isEmpty()){
			senha.setError(Html.fromHtml("<font color='red'>Por Favor digite sua SENHA</font>"));
			return false;
		}
		
		ChamadaWebService chamadaWebService = new ChamadaWebService();
		chamadaWebService.execute(0,0,0);
		
		return true;
	}
	
	private boolean testConnection() { 
        boolean conection = false;
        try
        {
            ConnectivityManager cm = (ConnectivityManager)
            getSystemService(Context.CONNECTIVITY_SERVICE); 
            if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) { 
            	conection = true; 
            } 
        }catch (Exception e) {
            trace(e.getMessage());
        }
        return conection;
    }
	
    public void toast (String msg){
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
    } 
 
    private void trace (String msg){
        Log.d ("teste", msg);
        toast (msg);
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
    
}
