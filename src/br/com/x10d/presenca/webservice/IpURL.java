package br.com.x10d.presenca.webservice;

public enum IpURL {

	//URL_SERVER_REST("http://192.168.252.49:9090");//localhost
	URL_SERVER_REST("http://x10d.com.br:9090");
			
	private String valor;
	
	private IpURL(String valor){
		this.valor = valor;
	}
	public String getValor(){
		return valor;
	}
}
