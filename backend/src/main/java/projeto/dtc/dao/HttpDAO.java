package projeto.dtc.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public abstract class HttpDAO {

	protected String get(String urlPath, Map<String,String> headers, Map<String,String> queryParameters) throws ClientProtocolException, IOException {
		return request(urlPath, headers, queryParameters);
	}

	@SuppressWarnings("unchecked")
	protected String request(String urlPath, Map<String, String> headers, Object data)
			throws ClientProtocolException, IOException {

		HttpResponse httpResponse = null;
		HttpClient httpClient = HttpClients.custom().build();

		HttpGet httpGet = new HttpGet(urlPath);
		putHeaders(httpGet, headers);
		putQueryParameters(httpGet, (Map<String, String>) data);
		httpResponse = httpClient.execute(httpGet);

		
		int responseCode = httpResponse.getStatusLine().getStatusCode();

		if (responseCode == HttpStatus.SC_OK) {
			return EntityUtils.toString(httpResponse.getEntity());
		}else if (responseCode == HttpStatus.SC_UNPROCESSABLE_ENTITY) {
			return "{message:\"latitude, longitude inválidas\"}";
		}else if (responseCode == HttpStatus.SC_BAD_REQUEST) {
			return "{message:\"requisição inválida\"}";
		}

		return "ERRO";

	}

	protected void putBody(HttpPost httpPost, Map<String, Object> body) {
		try {
			httpPost.setEntity(new StringEntity(getJson(body)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	protected void putQueryParameters(HttpGet httpGet, Map<String, String> queryParameters) {
		URIBuilder uriBuilder = new URIBuilder(httpGet.getURI());
		if (queryParameters != null && !queryParameters.isEmpty()) {
			queryParameters.forEach((k, v) -> {
				uriBuilder.addParameter(k, v);
			});
		}
		try {
			httpGet.setURI(uriBuilder.build());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void putHeaders(HttpUriRequest uriRequest, Map<String, String> headers) {
		if (headers != null && !headers.isEmpty()) {
			headers.forEach((k, v) -> {
				uriRequest.addHeader(k, v);
			});
		}

	}

	private String getJson(Map<String, Object> map) {
		return (new Gson()).toJson(map);
	}

	//public abstract Map<String, String> getHeaders();

}