package br.com.x10d.presenca.webservice;

public enum IpURL {

	//URL_SERVER_REST("http://192.168.15.10:9090");//localhost
	
	//URL_SERVER_REST("http://x10d.com.br:9090");
	
	URL_SERVER_REST("http://ec2-18-228-59-209.sa-east-1.compute.amazonaws.com:9090");
			
	private String valor;
	
	private IpURL(String valor){
		this.valor = valor;
	}
	public String getValor(){
		return valor;
	}
}
