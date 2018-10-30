package br.com.x10d.presenca.model;

import java.io.Serializable;
import java.util.List;

public class ViewPercentualPresenca implements Serializable{
	
	private long id;
	
	private String nome;
	private String evento;
	private String congregacao;
	private long total_presenca;
	private double percentual_presenca;
	
	private List<ViewPercentualPresenca> lista;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getCongregacao() {
		return congregacao;
	}

	public void setCongregacao(String congregacao) {
		this.congregacao = congregacao;
	}

	public long getTotal_presenca() {
		return total_presenca;
	}

	public void setTotal_presenca(long total_presenca) {
		this.total_presenca = total_presenca;
	}

	public double getPercentual_presenca() {
		return percentual_presenca;
	}

	public void setPercentual_presenca(double percentual_presenca) {
		this.percentual_presenca = percentual_presenca;
	}

	public List<ViewPercentualPresenca> getLista() {
		return lista;
	}

	public void setLista(List<ViewPercentualPresenca> lista) {
		this.lista = lista;
	}


	
}