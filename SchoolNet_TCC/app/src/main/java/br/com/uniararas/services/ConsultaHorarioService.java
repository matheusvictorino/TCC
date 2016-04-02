package br.com.uniararas.services;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.uniararas.beans.GrupoHorario;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.util.Constantes;
/**
 * Created by theuv on 28/02/2016.
 */
public class ConsultaHorarioService {

    public ArrayList<GrupoHorario> obterHorario(String cod_fac, String cod_curso, String ano_ingresso,int ano ,String semestre) throws Exception {
        ArrayList<GrupoHorario> listaHorarios = new ArrayList<GrupoHorario>();
        try {
            String url = Constantes.URL_OBTER_HORARIO_AULA +
                    "?cod_fac=" +
                    cod_fac + "&cod_curso=" +
                    cod_curso + "&ano_ingresso=" +
                    ano_ingresso + "&anoletivo=" +
                    ano + "&semestre=" + semestre;

            WebServiceCall webServiceCall = WebServiceCall.getInstance();
            String[] resposta = webServiceCall.get(url);

            if(!resposta[0].equals("200"))
                throw new Exception("Erro ao obter horários de aula.");

            JSONObject mainObject = new JSONObject(resposta[1].toString().trim());

            @SuppressWarnings("unchecked")
            Iterator<String> keys = mainObject.keys();
            while(keys.hasNext()){
                String key = keys.next();
                GrupoHorario grupoHorario = new Gson().fromJson(mainObject.getString(key), GrupoHorario.class);

                listaHorarios.add(grupoHorario);
            }

        }catch (JSONException e) {
            throw new JSONException("Não foi possivel exibir os dados.");
        } catch (Exception e) {
            throw new JSONException(e.getMessage());
        }

        return listaHorarios;
    }
}
