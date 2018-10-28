package br.com.x10d.presenca.model;

import java.io.Serializable;
import java.util.List;

public class ViewFrequenciaEvento implements Serializable{
	
	private Long id;
	
	private String nome;
	private String evento;
	private String congregacao;
	private String cargo;
	private Long presenca_31_10;
	private Long presenca_01_11;
	private Long primeiro_periodo_02_11;
	private Long segundo_periodo_02_11;
	private Long presenca_03_11;
	
	private List<ViewFrequenciaEvento> lista;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getPresenca_31_10() {
		return presenca_31_10;
	}

	public void setPresenca_31_10(Long presenca_31_10) {
		this.presenca_31_10 = presenca_31_10;
	}

	public Long getPresenca_01_11() {
		return presenca_01_11;
	}

	public void setPresenca_01_11(Long presenca_01_11) {
		this.presenca_01_11 = presenca_01_11;
	}

	public Long getPrimeiro_periodo_02_11() {
		return primeiro_periodo_02_11;
	}

	public void setPrimeiro_periodo_02_11(Long primeiro_periodo_02_11) {
		this.primeiro_periodo_02_11 = primeiro_periodo_02_11;
	}

	public Long getSegundo_periodo_02_11() {
		return segundo_periodo_02_11;
	}

	public void setSegundo_periodo_02_11(Long segundo__periodo_02_11) {
		this.segundo_periodo_02_11 = segundo__periodo_02_11;
	}

	public Long getPresenca_03_11() {
		return presenca_03_11;
	}

	public void setPresenca_03_11(Long presenca_03_11) {
		this.presenca_03_11 = presenca_03_11;
	}

	public List<ViewFrequenciaEvento> getLista() {
		return lista;
	}

	public void setLista(List<ViewFrequenciaEvento> lista) {
		this.lista = lista;
	}
	
}