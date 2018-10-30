package br.com.x10d.presenca.webservice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Reflexao {

	public <E> List<E> getLista(Class<E> classe, JSONArray jSONArray) throws Exception {

		List<E> lista = new ArrayList<E>();

		for (int i = 0; i < jSONArray.length(); i++) {

			JSONObject jSONObject = jSONArray.getJSONObject(i);

			Object objectInstance = classe.getConstructor().newInstance();

			for (Field field : classe.getDeclaredFields()) {

				field.setAccessible(true);

					if (field.getType() == int.class) {
						field.setInt(objectInstance, jSONObject.getInt(field.getName()));
					} 
					if (field.getType() == long.class) {
						
						long numero = jSONObject.getLong(field.getName());
						field.setLong(objectInstance, numero);
					} 
					if (field.getType() == double.class) {
						
						double numero = jSONObject.getDouble(field.getName());
						field.setDouble(objectInstance, numero);
					} 
					
					if (field.getType() == String.class) {
						field.set(objectInstance, jSONObject.getString(field.getName()));
					}
				
			}
			lista.add((E) objectInstance);
		}
		return lista;
	}

}
