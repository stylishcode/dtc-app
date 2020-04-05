package projeto.business;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;

import projeto.dtc.dao.HttpDAO;
import projeto.dtc.model.Ponto;
import projeto.util.Util;

public class CalcularDistancias extends HttpDAO{
	
	private final String URL_ESTIMATES_ROTA = "https://maps.googleapis.com/maps/api/distancematrix/json";
	
	private final String LANG = "pt_BR";
	
	private final String UNITS = "metric";
	//	private final String MODE = "driving";
		
	@SuppressWarnings("unchecked")
	public String estimarTrajetoFinal(String key, String mode, List<Ponto> origem, List<Ponto> destino) throws ClientProtocolException, IOException, InterruptedException {
		
		DecimalFormat df = new DecimalFormat("0.##");
		
		StringBuilder sbResultado = new StringBuilder();
		
		for (int i = 0; i < origem.size(); i++) {
			
			int indexMenorDistancia = 0;
			int menorDistancia = 9999999;
			
			List<String> origemDestino = new ArrayList<String>();
						
			for (int j = 0; j < destino.size(); j++) {
				
				Map<String, String> parametros = new HashMap<String, String>();
				
				parametros.put("origins", origem.get(i).latitude + "," + origem.get(i).longitude);
				parametros.put("destinations", destino.get(j).latitude + "," + destino.get(j).longitude);
				
				parametros.put("units", UNITS);
				parametros.put("mode", mode);
				parametros.put("language", LANG);
				parametros.put("key", key);
				
				Map<String, Object> retorno = Util.getMap(get(URL_ESTIMATES_ROTA, getHeaders(), parametros)); 
								
				int distancia = Integer.valueOf(df.format(((Map<String,Object>)(Map<String,Object>)((ArrayList<Map>)((Map<String,Object>)((ArrayList<Map>)retorno.get("rows")).get(0)).get("elements")).get(0).get("distance")).get("value")));
				
				if (distancia < menorDistancia) {
					menorDistancia = distancia;
					indexMenorDistancia = j;
				}
				
				StringBuilder sb = new StringBuilder();
				
				sb.append(origem.get(i).index);
				sb.append(";");
				sb.append(destino.get(j).index);
				sb.append(";");
				sb.append(distancia);
				sb.append(";");
				sb.append(((Map<String,Object>)(Map<String,Object>)((ArrayList<Map>)((Map<String,Object>)((ArrayList<Map>)retorno.get("rows")).get(0)).get("elements")).get(0).get("distance")).get("text"));
				sb.append(";");
				sb.append(df.format(((Map<String,Object>)(Map<String,Object>)((ArrayList<Map>)((Map<String,Object>)((ArrayList<Map>)retorno.get("rows")).get(0)).get("elements")).get(0).get("duration")).get("value")));
				sb.append(";");
				sb.append(((Map<String,Object>)(Map<String,Object>)((ArrayList<Map>)((Map<String,Object>)((ArrayList<Map>)retorno.get("rows")).get(0)).get("elements")).get(0).get("duration")).get("text"));
				sb.append(";");
				sb.append(origem.get(i).latitude);
				sb.append(";");
				sb.append(origem.get(i).longitude);
				sb.append(";");
				sb.append(destino.get(j).latitude);
				sb.append(";");
				sb.append(destino.get(j).longitude);
				sb.append(";");
				sb.append(destino.get(j).utmX);
				sb.append(";");
				sb.append(destino.get(j).utmY);
				sb.append(";");
				sb.append(((ArrayList<Map>)retorno.get("origin_addresses")).get(0));
				sb.append(";");
				sb.append(((ArrayList<Map>)retorno.get("destination_addresses")).get(0));
				
				origemDestino.add(sb.toString());
				
			}
			
			origemDestino.set(indexMenorDistancia, origemDestino.get(indexMenorDistancia)+";X"); 
			
			origemDestino.forEach(p->{
				sbResultado.append(p);///.append("/n");				
			});
					
		}
		
		return sbResultado.toString();
						
	}
	
	private Map<String, String> getHeaders() {
		Map<String, String> headers = new HashMap<>();
		// Definicao content type
		headers.put("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
		return headers;
	}

}
