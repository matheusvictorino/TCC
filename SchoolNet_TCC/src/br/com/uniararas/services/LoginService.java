package br.com.uniararas.services;

import org.json.JSONObject;

import br.com.uniararas.beans.Aluno;
import br.com.uniararas.resources.WebServiceCall;
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
