package br.com.x10d.presenca.webservice;

public enum IpURL {

	//URL_SERVER_REST("http://52.67.219.4:8080/X10Dapi"),
	URL_SERVER_REST("http://x10d.com.br:8080/X10Dapi"),
	//URL_SERVER_REST("http://192.168.252.49:8080/X10Dapi"),
	NOME_DO_PACOTE("br.com.x10d.app.model.");
			
	private String valor;
	
	private IpURL(String valor){
		this.valor = valor;
	}
	public String getValor(){
		return valor;
	}
}
