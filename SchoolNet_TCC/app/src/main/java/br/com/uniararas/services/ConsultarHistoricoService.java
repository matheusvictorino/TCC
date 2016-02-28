package br.com.uniararas.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.util.Constantes;
/**
 * Created by theuv on 28/02/2016.
 */
public class ConsultarHistoricoService {

    public ArrayList<HashMap<String,String>> obterHistorico(String cod_fac, String cod_curso, String ano_ingresso,String ano ,String semestre) throws Exception {
        ArrayList<HashMap<String,String>> listaHistorico = new ArrayList<HashMap<String,String>>();
        try {
            String url = Constantes.URL_OBTER_HISTORICO +
                    "?cod_fac=" +
                    cod_fac + "&cod_curso=" +
                    cod_curso + "&ano_ingresso=" +
                    ano_ingresso + "&anoletivo=" +
                    ano + "&semestre=" + semestre;

            WebServiceCall webServiceCall = WebServiceCall.getInstance();
            String[] resposta = webServiceCall.get("teste");

            if(!resposta[0].equals("200"))
                throw new Exception("Erro ao obter histórico.");

            JSONObject mainObject = new JSONObject(resposta[1].toString().trim());

            @SuppressWarnings("unchecked")
            Iterator<String> keys = mainObject.keys();
            while(keys.hasNext()){
                String key = keys.next();
                JSONObject value = mainObject.getJSONObject(key);
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("materia", value.getString("materia"));
                map.put("media", value.getString("media"));

                map.put("frequencia", value.getString("frequencia"));
                map.put("situacao", value.getString("situacao"));
                listaHistorico.add(map);
            }

        }catch (JSONException e) {
            throw new JSONException("Não foi possivel exibir os dados.");
        } catch (Exception e) {
            throw new JSONException(e.getMessage());
        }
        return listaHistorico;
    }
}
