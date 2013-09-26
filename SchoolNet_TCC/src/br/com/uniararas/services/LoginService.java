package br.com.uniararas.services;

import br.com.uniararas.beans.Aluno;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.util.Constantes;


public class LoginService {
	
	public String[] autenticarUsuario(Aluno aluno) throws Exception{
		try{
			WebServiceCall webServiceCall = WebServiceCall.getInstance();
			String resposta[] = webServiceCall.autenticacao(aluno, Constantes.URL_AUTENTICAR);
			
			if(!resposta[0].equals("200"))
				throw new Exception("Erro ao efetuar login.");
			
			return resposta;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}
}
