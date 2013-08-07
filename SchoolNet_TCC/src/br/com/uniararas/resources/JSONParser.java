package br.com.uniararas.resources;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import br.com.uniararas.beans.Aluno;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
    public String[] result;
    private static final int JSON_CONNECTION_TIMEOUT = 3000;
    private static final int JSON_SOCKET_TIMEOUT = 5000;
    private HttpParams httpParameters ;
    private DefaultHttpClient httpclient;
    
	// constructor
	public JSONParser() {

	}
    
    private void setTimeOut(HttpParams params){
    	HttpConnectionParams.setConnectionTimeout(params, JSON_CONNECTION_TIMEOUT);
    	HttpConnectionParams.setSoTimeout(params, JSON_SOCKET_TIMEOUT);
    }


	public JSONObject getJSONFromUrl(String url) {

		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();			

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}
	
	  public final String[] post(Aluno aluno) {
		     String[] result = new String[2];
		     try {
		    	 String url = "http://192.168.1.101/webservicetcc/aluno/";
		    	 
		    	 JSONObject jsonObj = new JSONObject();
		    	 jsonObj.put("ra", aluno.ra);
		    	 jsonObj.put("senha", aluno.senha);
		    	 
		    	 Log.v("Teste" ,jsonObj.toString());
	    	     BasicHttpParams httpParameters = new BasicHttpParams();
	    	     setTimeOut(httpParameters);
	    	     DefaultHttpClient httpclient = new DefaultHttpClient(httpParameters);
		     	
		         HttpPost httpPost = new HttpPost(new URI(url));
		         httpPost.setHeader("Content-type", "application/json");
		         httpPost.setHeader("Authorization","da39a3ee5e6b4b0d3255bfef95601890afd80709");
		         httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		         StringEntity sEntity = new StringEntity(jsonObj.toString(), "UTF-8");
		         httpPost.setEntity(sEntity);

		         HttpResponse response;
		         response = httpclient.execute(httpPost);
		         Header[] headers = response.getAllHeaders();
		         this.exibeHeaders(headers);
		         HttpEntity entity = response.getEntity();

		         if (entity != null) {
		             result[0] = String.valueOf(response.getStatusLine().getStatusCode());
		             InputStream instream = entity.getContent();
		             result[1] = toString(instream);
		             instream.close();
		         } 
		             return result;

		     } catch (Exception e) {
		         Log.e("NGVL", "Falha ao acessar Web service", e);
		    
				result[0] = "0";
		         result[1] = "Falha de rede!";
		         return null;
		     }
		    
		    }
	  
	    private String toString(InputStream is) throws IOException {

	        byte[] bytes = new byte[1024];
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        int lidos;
	        while ((lidos = is.read(bytes)) > 0) {
	            baos.write(bytes, 0, lidos);
	        }
	        return new String(baos.toByteArray());
	    }
	    

	    
	    private void exibeHeaders(Header[] headers){
	    	for (Header header : headers) {
	    		Log.d("Key : ", header.getName() + header.getValue());
	    	}
	    }

}
