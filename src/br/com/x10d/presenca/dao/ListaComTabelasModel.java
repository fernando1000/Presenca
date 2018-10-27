package br.com.x10d.presenca.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.x10d.presenca.model.Chamada;
import br.com.x10d.presenca.model.Cadastro;

public class ListaComTabelasModel {
	
	public static List<String> devolveListaComTabelasModel() {

		List<String> lista = new ArrayList<String>();
					 lista.add(Cadastro.class.getSimpleName());
					 lista.add(Chamada.class.getSimpleName());

		return lista;
	}

}
