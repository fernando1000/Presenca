package br.com.x10d.presenca.webservice;

import java.lang.reflect.Field;

import org.json.JSONObject;

public class DeJsonParaObjeto {

	public <T> Object  transforma(Class<T> classe, JSONObject jSONObject) throws Exception {

		 Object objectInstance = classe.getConstructor().newInstance();

		for (Field field : classe.getDeclaredFields()) {

			field.setAccessible(true);

			if (field.getType() == int.class) {
				field.setInt(objectInstance, jSONObject.getInt(field.getName()));
			}
			if (field.getType() == long.class) {
				field.setLong(objectInstance, jSONObject.getLong(field.getName()));
			}
			if (field.getType() == double.class) {
				field.setDouble(objectInstance, jSONObject.getDouble(field.getName()));
			}
			if (field.getType() == String.class) {
				field.set(objectInstance, jSONObject.getString(field.getName()));
			}
		}
		return objectInstance;
	}

}
