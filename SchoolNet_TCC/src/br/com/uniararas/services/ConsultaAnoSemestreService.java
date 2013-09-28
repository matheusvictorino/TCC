package br.com.uniararas.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.uniararas.beans.AnoSemestre;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.util.Constantes;

public class ConsultaAnoSemestreService {
	
	public ArrayList<HashMap<String,String>> obterAnoSemestre() throws Exception {
		ArrayList<HashMap<String,String>> listaAnoSemestre = new ArrayList<HashMap<String,String>>();
		ArrayList<AnoSemestre> anosSemestres = new ArrayList<AnoSemestre>();
		try {
			WebServiceCall webServiceCall = WebServiceCall.getInstance();
			String[] resposta = webServiceCall.get("","", Constantes.URL_OBTER_ANO_SEMESTRE);
			
			if(!resposta[0].equals("200"))
				throw new Exception("Erro ao obter faltas.");
			
			JSONObject mainObject = new JSONObject(resposta[1].trim());
			
			Iterator<String> keys = mainObject.keys();
			
			while(keys.hasNext()){
				String key = keys.next();
				JSONObject value = mainObject.getJSONObject(key);
				AnoSemestre a = new AnoSemestre();
				a.anolevito = value.getString("anolevito");
				a.semestre = value.getString("semestre");
				anosSemestres.add(a);
			}
			
			
			Collections.sort(anosSemestres);
			Collections.reverse(anosSemestres);
			for(AnoSemestre anosS : anosSemestres){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(Constantes.TAG_ANO, anosS.anolevito);
				map.put(Constantes.TAG_SEMESTRE, anosS.semestre);
				listaAnoSemestre.add(map);
			}
			
		} catch (JSONException e) {
			throw new JSONException("Não foi possivel exibir os dados.");
		} catch (Exception e) {
			throw new JSONException(e.getMessage());	
		}
		return listaAnoSemestre;
	}
}
