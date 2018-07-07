package br.com.x10d.presenca.validacao;

import android.util.Log;
import android.widget.EditText;

public class ValidaRG implements Validador {

    private final EditText etRG;
    private final ValidaCampoVazio validaCampoVazio;

    public ValidaRG(EditText etRG) {
    	 
        this.etRG = etRG;
        this.validaCampoVazio = new ValidaCampoVazio(etRG);
    }

    @Override
    public boolean estaValido(){
    	
        if(!validaCampoVazio.estaValido()) {        	
        	return false;
        } 
        
        String rg = etRG.getText().toString();
        
        String rgSemFormatacao = removeFormatacao(rg);
        
        if(!validaNoveDigitos(rgSemFormatacao)) {
        	
        	return false;
        } 
        
        adicionaFormatacao(rgSemFormatacao);
        
        return true;
    }

    private boolean validaNoveDigitos(String rg){
    	
        int digitos = rg.length();
        
        Log.i("tag","qtd digitos: "+digitos);
        
        if(digitos != 9){
        	
        	etRG.setError("RG deve ter 9 digitos");
            return false;
        }
        return true;
    }

    private void adicionaFormatacao(String rg) {
    	
        String rgFormatado = formata(rg);
        
        etRG.setText(rgFormatado);
    }

    private String formata(String rg) {
    	
        return rg.replaceAll("([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{1})", "$1 $2 $3-$4");
    }

    public String removeFormatacao(String rg) {
    	
        return rg.replaceAll(" ", "")
        		 .replace("-", "");
    }


}
