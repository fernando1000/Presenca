package br.com.x10d.presenca.model;

import java.util.ArrayList;
import java.util.List;

public class Congregacoes {
	
	public static final List<ChaveValor> pegaLista(){
	
		ArrayList<ChaveValor> lista = new ArrayList<ChaveValor>();
		lista.add(new ChaveValor(0, "Alabama"));
		lista.add(new ChaveValor(1, "Amador Bueno I"));
		lista.add(new ChaveValor(2, "Amador Bueno II"));
		lista.add(new ChaveValor(3, "Ambuitá"));
		lista.add(new ChaveValor(4, "Areião"));
		lista.add(new ChaveValor(5, "Bela Vista Alta"));
		lista.add(new ChaveValor(6, "Bela Vista Baixa I"));
		lista.add(new ChaveValor(7, "Bela Vista Baixa II"));
		lista.add(new ChaveValor(8, "Briquet I"));
		lista.add(new ChaveValor(9, "Briquet II"));	
		lista.add(new ChaveValor(10, "Cohab II"));
		lista.add(new ChaveValor(11, "Colinas de São José"));
		lista.add(new ChaveValor(12, "Engenheiro Cardoso"));
		lista.add(new ChaveValor(13, "Itapuã"));
		lista.add(new ChaveValor(14, "Jardim Ruth"));
		lista.add(new ChaveValor(15, "Nova Cotia"));
		lista.add(new ChaveValor(16, "Outros Ministérios"));
		lista.add(new ChaveValor(17, "Paulista"));
		lista.add(new ChaveValor(18, "Pedra Branca"));
		lista.add(new ChaveValor(19, "Rainha"));
		lista.add(new ChaveValor(20, "Recanto Paulistano"));
		lista.add(new ChaveValor(21, "Roselândia"));
		lista.add(new ChaveValor(22, "Rosemary"));
		lista.add(new ChaveValor(23, "Santa Cecília I"));
		lista.add(new ChaveValor(24, "Santa Cecília II"));
		lista.add(new ChaveValor(25, "Santa Rita"));
		lista.add(new ChaveValor(26, "São Carlos"));
		lista.add(new ChaveValor(27, "Sede"));
		lista.add(new ChaveValor(28, "Suburbano I"));
		lista.add(new ChaveValor(29, "Suburbano II"));
		lista.add(new ChaveValor(30, "Vitapolis"));

		return lista;
	}
}
