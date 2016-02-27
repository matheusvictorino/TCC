package br.com.uniararas.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

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
public class ConsultaFaltasService {
	
	public ArrayList<HashMap<String,String>> obterFaltas(String cod_fac, String cod_curso, String ano_ingresso,String ano ,String semestre) throws Exception {
		ArrayList<HashMap<String,String>> listaMaterias = new ArrayList<HashMap<String,String>>();
		try {
			String url = Constantes.URL_OBTER_FALTAS +
					"?cod_fac=" +
					cod_fac + "&cod_curso=" +
					cod_curso + "&ano_ingresso=" +
					ano_ingresso + "&anoletivo=" +
					ano + "&semestre=" + semestre;
			
			WebServiceCall webServiceCall = WebServiceCall.getInstance();
			String[] resposta = webServiceCall.get(url);
			
			if(!resposta[0].equals("200"))
				throw new Exception("Erro ao obter faltas.");
			
			JSONObject mainObject = new JSONObject(resposta[1].trim());
			
			@SuppressWarnings("unchecked")
			Iterator<String> keys = mainObject.keys();
			while(keys.hasNext()){
				String key = keys.next();
				JSONObject value = mainObject.getJSONObject(key);
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(Constantes.TAG_NOME_MATERIA, value.getString(Constantes.TAG_NOME_MATERIA));
				map.put(Constantes.TAG_NUMERO_FALTAS, value.getString(Constantes.TAG_NUMERO_FALTAS));
				map.put(Constantes.TAG_NUMERO_FALTAS_LIMITE, value.getString(Constantes.TAG_NUMERO_FALTAS_LIMITE));
				listaMaterias.add(map);
			}
			
		} catch (JSONException e) {
			throw new JSONException("Não foi possivel exibir os dados.");
		} catch (Exception e) {
			throw new JSONException(e.getMessage());	
		}
		return listaMaterias;
	}

}
