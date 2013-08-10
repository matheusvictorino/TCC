package br.com.uniararas.services;

import br.com.uniararas.beans.Aluno;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.util.Constantes;


public class LoginService {

	private WebServiceCall webServiceCall;
	
	public LoginService(){
		this.webServiceCall = new WebServiceCall();
	}	
	
	public String[] autenticarUsuario(Aluno aluno) throws Exception{
		try{
			String resposta[] = webServiceCall.post(aluno, Constantes.URL_AUTENTICAR);
			
			if(!resposta[0].equals("200"))
				throw new Exception("Erro ao efetuar login.");
			
			return resposta;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}
}
