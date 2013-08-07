package br.com.uniararas.actvity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.uniararas.beans.Aluno;
import br.com.uniararas.resources.JSONParser;

public class LoginActivity extends Activity {

	EditText ra;
	EditText senha;
	String result[];
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        ra = (EditText)findViewById(R.id.editText1);
        senha = (EditText)findViewById(R.id.editText2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
	public boolean onClickLogin(View view) {
		
		if(!testConnection()){
			toast("Você Precisa estar conectado a internet antes de continuar");
			return false;
		}
		
		if(ra.getText().toString().isEmpty()){
			ra.setError("Por Favor digite seu RA");
			return false;
		}
		
		if(senha.getText().toString().isEmpty()){
			senha.setError("Por Favor digite sua SENHA");
			return false;
		}
		
		Aluno aluno = new Aluno();
		aluno.ra = ra.getText().toString();
		aluno.senha = senha.getText().toString();
		
		JSONParser jsonparser = new JSONParser();
		
		result = jsonparser.post(aluno);
		CharSequence text;
		int duration = Toast.LENGTH_SHORT;
		
		if(!result.equals(null)){
			
			if(result[0].equals("200")){
				Intent in = new Intent(getApplicationContext(), MenuActivity.class);
				in.putExtra("aluno", result[1]);
				startActivity(in);
				return true;

			}
		}
		text="Erro ao efetuar login!";
		Context context = getApplicationContext();
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();getApplicationContext();
		return false;		
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
    
}