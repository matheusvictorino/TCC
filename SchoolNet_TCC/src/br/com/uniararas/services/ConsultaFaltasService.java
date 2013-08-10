package br.com.uniararas.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;

import br.com.uniararas.beans.Aluno;
import br.com.uniararas.beans.Materias;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.util.Constantes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ConsultaFaltasService {
	
	private WebServiceCall webServiceCall;
	
	public ConsultaFaltasService(){
		this.webServiceCall = new WebServiceCall();
	}
	
	public ArrayList<HashMap<String,String>> obterFaltas(Aluno aluno) throws Exception {
		ArrayList<HashMap<String,String>> listaMaterias = null;
		
		try {
			
			String[] resposta = webServiceCall.post(aluno, Constantes.URL_OBTER_FALTAS);
			
			if(!resposta[0].equals("200"))
				throw new Exception("Erro ao obter faltas.");
			
			java.lang.reflect.Type listType = new TypeToken<List<Materias>>() {}.getType();
			List<Materias> materias = new Gson().fromJson( resposta[1].trim() , listType);
			
			listaMaterias = new ArrayList<HashMap<String,String>>();
			
			for (Materias materia : materias){
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put(Constantes.TAG_NOME_MATERIA, materia.getNomemateria());
				map.put(Constantes.TAG_NUMERO_FALTAS, materia.getNumerofaltas());
				map.put(Constantes.TAG_NUMERO_FALTAS_LIMITE, materia.getNumerofaltaslimite());

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
