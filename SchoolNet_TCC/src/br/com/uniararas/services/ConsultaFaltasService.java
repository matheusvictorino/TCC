package br.com.uniararas.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.uniararas.util.Constantes;

public class ConsultaFaltasService {
	
	public ArrayList<HashMap<String,String>> obterFaltas() throws JSONException {
		JSONArray materias = null;
		ArrayList<HashMap<String,String>> listaMaterias = null;
		JSONObject json = null;
		
		try {
			//TODO: metodo que irá buscar dados no webService
			json = new JSONObject("{\"materias\":[ {\"nomemateria\":\"SIF034-Engenharia de Software IV \",\"numerofaltas\":\"10\",\"numerofaltaslimite\":\"10\" },{\"nomemateria\":\"SIF043-Gerência de Projetos\",\"numerofaltas\":\"10\",\"numerofaltaslimite\":\"10\" },{\"nomemateria\":\"SIF040-Projeto de Sistemas I \",\"numerofaltas\":\"10\",\"numerofaltaslimite\":\"10\" },{\"nomemateria\":\"SIF039-Redes de Computadores II \",\"numerofaltas\":\"10\",\"numerofaltaslimite\":\"10\" },{\"nomemateria\":\"SIF044-Tópicos Avançados em SI I \",\"numerofaltas\":\"10\",\"numerofaltaslimite\":\"10\" },{\"nomemateria\":\"SIF070-Tópicos Especiais em Sistemas de Informação\",\"numerofaltas\":\"10\",\"numerofaltaslimite\":\"10\" },{\"nomemateria\":\"SIF068-Tópicos em Linguagem de Programação \",\"numerofaltas\":\"10\",\"numerofaltaslimite\":\"10\" }]}");
			materias = json.getJSONArray(Constantes.TAG_MATERIAS);
			listaMaterias = new ArrayList<HashMap<String,String>>();
			
			for(int i = 0; i < materias.length(); i++){
				JSONObject c = materias.getJSONObject(i);
				String nomeMateria = c.getString(Constantes.TAG_NOME_MATERIA);
				String numemeroFaltas = c.getString(Constantes.TAG_NUMERO_FALTAS);
				String numeroFaltasLimite = c.getString(Constantes.TAG_NUMERO_FALTAS_LIMITE);
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put(Constantes.TAG_NOME_MATERIA, nomeMateria);
				map.put(Constantes.TAG_NUMERO_FALTAS, numemeroFaltas);
				map.put(Constantes.TAG_NUMERO_FALTAS_LIMITE, numeroFaltasLimite);

				listaMaterias.add(map);
			}
		} catch (JSONException e) {
			throw new JSONException("Não foi possivel fazer a converção para JSon");
		}
		return listaMaterias;
	}

}
