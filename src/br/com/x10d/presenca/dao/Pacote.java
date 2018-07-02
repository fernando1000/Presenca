package br.com.x10d.presenca.dao;

public enum Pacote {
	
    NOME_PACOTE("br.com.x10d.presenca.model.");

	private String nome;

	public String getNome() {
		return nome;
	}

	private Pacote(String nome) {
		this.nome = nome;
	}
}
