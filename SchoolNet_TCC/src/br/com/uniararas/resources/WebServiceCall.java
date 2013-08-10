package br.com.uniararas.resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import android.util.Log;
import br.com.uniararas.util.Constantes;

import com.google.gson.Gson;

public class WebServiceCall {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	public String[] result;
	private static final int JSON_CONNECTION_TIMEOUT = 3000;
	private static final int JSON_SOCKET_TIMEOUT = 5000;
	
	private void setTimeOut(HttpParams params) {
		HttpConnectionParams.setConnectionTimeout(params,
				JSON_CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, JSON_SOCKET_TIMEOUT);
	}

	public final String[] post(Object obj,String urlLocal) throws Exception {
		String[] result = new String[2];
		try {
			String url = Constantes.URL_PADRAO + urlLocal;

			Gson gson = new Gson();
			BasicHttpParams httpParameters = new BasicHttpParams();
			setTimeOut(httpParameters);
			DefaultHttpClient httpclient = new DefaultHttpClient(httpParameters);

			HttpPost httpPost = new HttpPost(new URI(url));
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setHeader("Authorization",	Constantes.AUTHORIZATION);
			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
			StringEntity sEntity = new StringEntity(gson.toJson(obj), "UTF-8");
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
			
		} catch (ConnectTimeoutException e) {
			throw new ConnectTimeoutException("Obteve o tempo limite de conexão.");

		} catch (Exception e) {
			throw new ConnectTimeoutException("Falha ao acessar o Web Service.");
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

	private void exibeHeaders(Header[] headers) {
		for (Header header : headers) {
			Log.d("Key : ", header.getName() + header.getValue());
		}
	}
}
