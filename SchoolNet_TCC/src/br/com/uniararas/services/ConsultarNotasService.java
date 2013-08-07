package br.com.uniararas.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.uniararas.util.Constantes;

public class ConsultarNotasService {

	public ArrayList<HashMap<String,String>> obterNotas() throws JSONException {
		JSONArray materias = null;
		ArrayList<HashMap<String,String>> listaMaterias = null;
		JSONObject json = null;
		
		try {
			//TODO: metodo que irá buscar dados no webService
			json = new JSONObject("{\"materias\":[{\"nomemateria\":\"SIF034-Engenharia de Software IV \",\"primeiranota\":\"10\",\"notaspa\":\"10\",\"segundanota\":\"10\",\"mediafinal\":\"10\" },{\"nomemateria\":\"SIF043-Gerência de Projetos\",\"primeiranota\":\"10\",\"notaspa\":\"10\",\"segundanota\":\"10\",\"mediafinal\":\"10\" },{\"nomemateria\":\"SIF040-Projeto de Sistemas I\",\"primeiranota\":\"10\",\"notaspa\":\"10\",\"segundanota\":\"10\",\"mediafinal\":\"10\" },{\"nomemateria\":\"SIF039-Redes de Computadores II\",\"primeiranota\":\"10\",\"notaspa\":\"10\",\"segundanota\":\"10\",\"mediafinal\":\"10\" },{\"nomemateria\":\"SIF044-Tópicos Avançados em SI I\",\"primeiranota\":\"10\",\"notaspa\":\"10\",\"segundanota\":\"10\",\"mediafinal\":\"10\" },{\"nomemateria\":\"SIF070-Tópicos Especiais em Sistemas de Informação\",\"primeiranota\":\"10\",\"notaspa\":\"10\",\"segundanota\":\"10\",\"mediafinal\":\"10\" },{\"nomemateria\":\"SIF068-Tópicos em Linguagem de Programação\",\"primeiranota\":\"10\",\"notaspa\":\"10\",\"segundanota\":\"10\",\"mediafinal\":\"10\" }]}");
			materias = json.getJSONArray(Constantes.TAG_MATERIAS);
			listaMaterias = new ArrayList<HashMap<String,String>>();
			
			for(int i = 0; i < materias.length(); i++){
				JSONObject c = materias.getJSONObject(i);
				
				String nomeMateria = c.getString(Constantes.TAG_NOME_MATERIA);
				String primeiraNota = c.getString(Constantes.TAG_PRIMEIRA_NOTA);
				String notaSPA = c.getString(Constantes.TAG_NOTA_SPA);
				String segundaNota = c.getString(Constantes.TAG_SEGUNDA_NOTA);
				String mediaFinal = c.getString(Constantes.TAG_MEDIA_FINAL);
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put(Constantes.TAG_NOME_MATERIA, nomeMateria);
				map.put(Constantes.TAG_PRIMEIRA_NOTA, primeiraNota);
				map.put(Constantes.TAG_NOTA_SPA, notaSPA);
				map.put(Constantes.TAG_SEGUNDA_NOTA, segundaNota);
				map.put(Constantes.TAG_MEDIA_FINAL, mediaFinal);

				listaMaterias.add(map);
			}
		} catch (JSONException e) {
			throw new JSONException("Não foi possivel fazer a converção para JSon");	
		}
		return listaMaterias;
	}
}
