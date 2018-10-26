package br.com.x10d.presenca.model;

import java.util.Date;
import java.util.List;

public class Chamada {

	private Integer id;
	private String codigoBarras;
	private Date data;
	private String nomePalestra;	
	private String mensagem;	
	private List<Chamada> chamadas;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getNomePalestra() {
		return nomePalestra;
	}
	public void setNomePalestra(String nomePalestra) {
		this.nomePalestra = nomePalestra;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public List<Chamada> getChamadas() {
		return chamadas;
	}
	public void setChamadas(List<Chamada> chamadas) {
		this.chamadas = chamadas;
	}
	
}
