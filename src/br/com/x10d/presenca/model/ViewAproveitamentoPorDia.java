package br.com.x10d.presenca.model;

import java.io.Serializable;
import java.util.List;

public class ViewAproveitamentoPorDia implements Serializable{
	
	private Long dia1;
	private Long dia2;
	private Long dia3;
	private Long dia4;
	private Long dia5;
	
	private List<ViewAproveitamentoPorDia> lista;

	public Long getDia1() {
		return dia1;
	}

	public void setDia1(Long dia1) {
		this.dia1 = dia1;
	}

	public Long getDia2() {
		return dia2;
	}

	public void setDia2(Long dia2) {
		this.dia2 = dia2;
	}

	public Long getDia3() {
		return dia3;
	}

	public void setDia3(Long dia3) {
		this.dia3 = dia3;
	}

	public Long getDia4() {
		return dia4;
	}

	public void setDia4(Long dia4) {
		this.dia4 = dia4;
	}

	public Long getDia5() {
		return dia5;
	}

	public void setDia5(Long dia5) {
		this.dia5 = dia5;
	}

	public List<ViewAproveitamentoPorDia> getLista() {
		return lista;
	}

	public void setLista(List<ViewAproveitamentoPorDia> lista) {
		this.lista = lista;
	}
	
}