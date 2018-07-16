package br.com.x10d.presenca.webservice;

import org.json.JSONArray;
import org.json.JSONObject;

public class MontaJSONObjectGenerico {
		
	public JSONObject montaJSONObject(Object objeto) {

		JSONObject jSONObject_holder = new JSONObject();

		try {			
														   JSONArray jSONArray_listaComAClasseModelEscolhida = new JSONArray();				
															  		 jSONArray_listaComAClasseModelEscolhida.put(DevolveJsonObjectDeUmaClasse.devolve(objeto));				
			jSONObject_holder.put(objeto.getClass().getSimpleName(), jSONArray_listaComAClasseModelEscolhida);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return jSONObject_holder;
	}
	
}
