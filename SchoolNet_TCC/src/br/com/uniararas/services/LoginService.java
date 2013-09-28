package br.com.uniararas.services;

import org.json.JSONObject;

import com.google.gson.JsonObject;

import br.com.uniararas.beans.Aluno;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.util.Constantes;


public class LoginService {
	
	public String[] autenticarUsuario(Aluno aluno) throws Exception{
		try{
			WebServiceCall webServiceCall = WebServiceCall.getInstance();
			String resposta[] = webServiceCall.autenticacao(aluno, Constantes.URL_AUTENTICAR);
			
			JSONObject resp = new JSONObject(resposta[1].trim());
			
//			if(!resposta[0].equals("200"))
			if(resp.get("autenticado").equals("N"))
				throw new Exception(resp.get("mensagem").toString());
			
			return resposta;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}
}
