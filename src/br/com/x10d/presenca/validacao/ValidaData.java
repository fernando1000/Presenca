package br.com.x10d.presenca.validacao;

import android.widget.EditText;

public class ValidaData implements Validador {

    private final EditText etData;
    private final ValidaCampoVazio validaCampoVazio;

    public ValidaData(EditText etData) {
    	 
        this.etData = etData;
        this.validaCampoVazio = new ValidaCampoVazio(etData);
    }

    @Override
    public boolean estaValido(){
    	
        if(!validaCampoVazio.estaValido()) {        	
        	return false;
        } 
        
        String data = etData.getText().toString();
        
        String dataSemFormatacao = removeFormatacao(data);
        
        if(!validaOitoDigitos(dataSemFormatacao)) {
        	
        	return false;
        } 
        
        adicionaFormatacao(dataSemFormatacao);
        
        return true;
    }

    private void adicionaFormatacao(String data) {
    	
        String dataFormatada = formata(data);
        
        etData.setText(dataFormatada);
    }

    private String formata(String telefoneComDdd) {
    	
        return telefoneComDdd.replaceAll("([0-9]{2})([0-9]{2})([0-9]{4})", "$1/$2/$3");
    }

    public String removeFormatacao(String data) {
    	
        return data.replaceAll("/", "");
    }

    private boolean validaOitoDigitos(String data){
    	
        int digitos = data.length();
        
        if(digitos != 8){
        	
        	etData.setError("Deve ter 8 digitos");
            return false;
        }
        return true;
    }


}
