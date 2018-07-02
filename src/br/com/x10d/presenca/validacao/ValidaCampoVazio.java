package br.com.x10d.presenca.validacao;

import android.widget.EditText;

public class ValidaCampoVazio implements Validador{

    private static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    private final EditText editText;

    public ValidaCampoVazio(EditText editText) {
    	
        this.editText = editText;
    }

    private boolean preencheuCampoObrigatorio() {
    	
        String texto = editText.getText().toString();
        
        if (texto.isEmpty()) {
        	
            editText.setError(CAMPO_OBRIGATORIO);
            
            return false;
        }
        return true;
    }

    @Override
    public boolean estaValido(){
    	
        if(!preencheuCampoObrigatorio()) {
        	
        	return false;
        } 
        
        editText.setError(null);
        
        return true;
    }

}
