package br.com.uniararas.services;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.uniararas.beans.GrupoHorario;
import br.com.uniararas.beans.Horario;
import br.com.uniararas.beans.Protocolo;
import br.com.uniararas.beans.ProtocoloClick;
import br.com.uniararas.resources.WebServiceCall;
import br.com.uniararas.util.Constantes;
/**
 * Created by theuv on 28/02/2016.
 */
public class ConsultaProtocoloService {

    public ArrayList<Protocolo> obterProtocolos() throws Exception {
        ArrayList<Protocolo> protocoloArray = new ArrayList<Protocolo>();

        try {
//            String url = Constantes.URL_OBTER_PROTOCOLO +
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
                Protocolo protocolo = new Gson().fromJson(mainObject.getString(key), Protocolo.class);

                protocoloArray.add(protocolo);
            }
        }catch (JSONException e) {
            throw new JSONException("Não foi possivel exibir os dados.");
        } catch (Exception e) {
            throw new JSONException(e.getMessage());
        }

        return protocoloArray;
    }

    public String moc() {
        return "{\n" +
                "\t\"0\" : {\n" +
                "\t\t\"nome\" : \"Nome 1\",\n" +
                "\t\t\"status\" : \"Status 1\",\n" +
                "\t\t\"setor\" : \"Setor 1\",\n" +
                "\t\t\"data\" : \"Data 1\",\n" +
                "\t\t\"resposta\" : \"Resposta 1\"\n" +
                "\t},\n" +
                "\t\"1\" : {\n" +
                "\t\t\"nome\" : \"Nome 2\",\n" +
                "\t\t\"status\" : \"Status 2\",\n" +
                "\t\t\"setor\" : \"Setor 2\",\n" +
                "\t\t\"data\" : \"Data 2\",\n" +
                "\t\t\"resposta\" : \"Resposta \"\n" +
                "\t},\n" +
                "\t\"2\" : {\n" +
                "\t\t\"nome\" : \"Nome 3\",\n" +
                "\t\t\"status\" : \"Status 3\",\n" +
                "\t\t\"setor\" : \"Setor 3\",\n" +
                "\t\t\"data\" : \"Data 3\",\n" +
                "\t\t\"resposta\" : \"Resposta 3\"\n" +
                "\t}\n" +
                "\n" +
                "}";
    }
}
