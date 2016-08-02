package br.com.uniararas.services;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.uniararas.beans.GrupoHorario;
import br.com.uniararas.beans.Horario;
import br.com.uniararas.beans.ProtocoloClick;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.util.Constantes;
/**
 * Created by theuv on 28/02/2016.
 */
public class ConsultaProtocoloClickService {

    public ArrayList<ProtocoloClick> obterProtocolos() throws Exception {
        ArrayList<ProtocoloClick> protocoloClickArray = new ArrayList<ProtocoloClick>();

        try {
//            String url = Constantes.URL_OBTER_PROTOCOLO_CLICK +
//                    "?cod_fac=" +
//                    cod_fac + "&cod_curso=" +
//                    cod_curso + "&ano_ingresso=" +
//                    ano_ingresso + "&anoletivo=" +
//                    ano + "&semestre=" + semestre;
//
//            WebServiceCall webServiceCall = WebServiceCall.getInstance();
//            String[] resposta = webServiceCall.get(url);
//
//            if(!resposta[0].equals("200"))
//                throw new Exception("Erro ao obter horários de aula.");

//            JSONObject mainObject = new JSONObject(resposta[1].toString().trim());
            JSONObject mainObject = new JSONObject(this.moc());

            @SuppressWarnings("unchecked")
            Iterator<String> keys = mainObject.keys();
            while(keys.hasNext()){
                String key = keys.next();
                ProtocoloClick protocoloClick = new Gson().fromJson(mainObject.getString(key), ProtocoloClick.class);

                protocoloClickArray.add(protocoloClick);
            }
        }catch (JSONException e) {
            throw new JSONException("Não foi possivel exibir os dados.");
        } catch (Exception e) {
            throw new JSONException(e.getMessage());
        }

        return protocoloClickArray;
    }

    public String moc() {
        return "{\n" +
                "  \"0\": \n" +
                "    {\n" +
                "      \"protocolo\": \"111111\",\n" +
                "      \"status\": \"Indeferido\",\n" +
                "      \"data\": \"19/08/1994\"\n" +
                "    }\n" +
                "  ,\n" +
                "  \"1\": \n" +
                "    {\n" +
                "      \"protocolo\": \"222222\",\n" +
                "      \"status\": \"Aprovado\",\n" +
                "      \"data\": \"26/07/2016\"\n" +
                "    }\n" +
                "}";
    }
}
