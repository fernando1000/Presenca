package br.com.x10d.presenca.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.x10d.presenca.model.Membro;

public class ListaComTabelasModel {
	
	public static List<String> devolveListaComTabelasModel() {

		List<String> lista = new ArrayList<String>();
					 lista.add(Membro.class.getSimpleName());

		return lista;
	}

}
