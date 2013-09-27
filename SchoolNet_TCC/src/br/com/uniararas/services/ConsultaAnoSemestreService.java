package br.com.uniararas.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;

import br.com.uniararas.beans.AnoSemestre;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.util.Constantes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ConsultaAnoSemestreService {
	
	public ArrayList<HashMap<String,String>> obterAnoSemestre() throws Exception {
		ArrayList<HashMap<String,String>> listaAnoSemestre = null;
		try {
			WebServiceCall webServiceCall = WebServiceCall.getInstance();
			String[] resposta = webServiceCall.get("","", Constantes.URL_OBTER_ANO_SEMESTRE);
			
			if(!resposta[0].equals("200"))
				throw new Exception("Erro ao obter faltas.");
			
			java.lang.reflect.Type listType = new TypeToken<List<AnoSemestre>>() {}.getType();
			List<AnoSemestre> anoSemestre = new Gson().fromJson( resposta[1].trim() , listType);
			
			listaAnoSemestre = new ArrayList<HashMap<String,String>>();
			
			for (AnoSemestre anosemestre : anoSemestre){
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put(Constantes.TAG_ANO, anosemestre.anolevito);
				map.put(Constantes.TAG_SEMESTRE, anosemestre.semestre);

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
