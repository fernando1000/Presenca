package br.com.x10d.presenca.model;

public class ChaveValor {

	private int chave;
	private String valor;
	
	public ChaveValor(int chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public int getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return this.valor;
	}
}
