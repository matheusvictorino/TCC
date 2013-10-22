package br.com.uniararas.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.util.Constantes;

public class ConsultarNotasService {

	public ArrayList<HashMap<String,String>> obterNotas(String ano , String semestre) throws Exception {
		ArrayList<HashMap<String,String>> listaMaterias = new ArrayList<HashMap<String,String>>();
		try {
			WebServiceCall webServiceCall = WebServiceCall.getInstance();
			String[] resposta = webServiceCall.get(ano,semestre, Constantes.URL_OBTER_NOTAS);
			
			if(!resposta[0].equals("200"))
				throw new Exception("Erro ao obter notas.");
			
//			java.lang.reflect.Type listType = new TypeToken<List<Materias>>() {}.getType();
//			List<Materias> materias = new Gson().fromJson( resposta[1].trim() , listType);
//			
//			listaMaterias = new ArrayList<HashMap<String,String>>();
//			
//			for (Materias materia : materias){
//				
//				HashMap<String, String> map = new HashMap<String, String>();
//				
//				map.put(Constantes.TAG_NOME_MATERIA, materia.nomemateria);
//				map.put(Constantes.TAG_PRIMEIRA_NOTA, materia.primeiranota);
//				map.put(Constantes.TAG_NOTA_SPA, materia.notaspa);
//				map.put(Constantes.TAG_SEGUNDA_NOTA,materia.segundanota);
//				map.put(Constantes.TAG_MEDIA_FINAL, materia.getMediafinal());
//
//				listaMaterias.add(map);
//			}
			
			JSONObject mainObject = new JSONObject(resposta[1].trim());
			
			@SuppressWarnings("unchecked")
			Iterator<String> keys = mainObject.keys();
			String spa = "";
			while(keys.hasNext()){
				String key = keys.next();
				JSONObject value = mainObject.getJSONObject(key);
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(Constantes.TAG_NOME_MATERIA, value.getString(Constantes.TAG_NOME_MATERIA));
				map.put(Constantes.TAG_PRIMEIRA_NOTA, value.getString(Constantes.TAG_PRIMEIRA_NOTA));
				if(value.getString(Constantes.TAG_NOTA_SPA).equals("null") || value.getString(Constantes.TAG_NOTA_SPA).equals(""))
					spa = "-";
				else
					spa = value.getString(Constantes.TAG_NOTA_SPA);
				map.put(Constantes.TAG_NOTA_SPA,spa );
				map.put(Constantes.TAG_SEGUNDA_NOTA, value.getString(Constantes.TAG_SEGUNDA_NOTA));
				map.put(Constantes.TAG_MEDIA_FINAL, "-");
				listaMaterias.add(map);
			}

		}catch (JSONException e) {
				throw new JSONException("Não foi possivel exibir os dados.");
		} catch (Exception e) {
			throw new JSONException(e.getMessage());	
		}
		return listaMaterias;
	}
}
