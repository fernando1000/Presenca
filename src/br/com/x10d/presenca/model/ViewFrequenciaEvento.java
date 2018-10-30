package br.com.x10d.presenca.model;

import java.io.Serializable;
import java.util.List;

public class ViewFrequenciaEvento implements Serializable{
	
	private long id;
	
	private String nome;
	private String evento;
	private String congregacao;
	private String cargo;
	private long presenca_31_10;
	private long presenca_01_11;
	private long primeiro_periodo_02_11;
	private long segundo_periodo_02_11;
	private long presenca_03_11;
	
	private List<ViewFrequenciaEvento> lista;

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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public long getPresenca_31_10() {
		return presenca_31_10;
	}

	public void setPresenca_31_10(long presenca_31_10) {
		this.presenca_31_10 = presenca_31_10;
	}

	public long getPresenca_01_11() {
		return presenca_01_11;
	}

	public void setPresenca_01_11(long presenca_01_11) {
		this.presenca_01_11 = presenca_01_11;
	}

	public long getPrimeiro_periodo_02_11() {
		return primeiro_periodo_02_11;
	}

	public void setPrimeiro_periodo_02_11(long primeiro_periodo_02_11) {
		this.primeiro_periodo_02_11 = primeiro_periodo_02_11;
	}

	public long getSegundo_periodo_02_11() {
		return segundo_periodo_02_11;
	}

	public void setSegundo_periodo_02_11(long segundo_periodo_02_11) {
		this.segundo_periodo_02_11 = segundo_periodo_02_11;
	}

	public long getPresenca_03_11() {
		return presenca_03_11;
	}

	public void setPresenca_03_11(long presenca_03_11) {
		this.presenca_03_11 = presenca_03_11;
	}

	public List<ViewFrequenciaEvento> getLista() {
		return lista;
	}

	public void setLista(List<ViewFrequenciaEvento> lista) {
		this.lista = lista;
	}

	
	
}