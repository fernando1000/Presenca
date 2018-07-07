package br.com.x10d.presenca.validacao;

import android.widget.EditText;

public class ValidaEmail implements Validador{

    private final EditText etEmail;
    private final ValidaCampoVazio validaCampoVazio;

    public ValidaEmail(EditText etEmail) {
        
        this.etEmail = etEmail;
        this.validaCampoVazio = new ValidaCampoVazio(etEmail);
    }

    @Override
    public boolean estaValido(){
    	
        if(!validaCampoVazio.estaValido()) {
        	return false;
        }
        
        String email = etEmail.getText().toString();
        
        if(!emailEstaValido(email)) {
        	
        	return false;
        }
        
        return true;
    }
    
    private boolean emailEstaValido(String email){
    	
        if(email.matches(".+@.+\\..+")){
            return true;
        }
        etEmail.setError("E-mail inválido");
        return false;
    }

}
