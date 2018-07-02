package br.com.x10d.presenca.validacao;

import android.widget.EditText;

public class ValidaEmail implements Validador{

    private final EditText campoEmail;
    private final ValidaCampoVazio validadorPadrao;

    public ValidaEmail(EditText textInputEmail) {
        
        this.campoEmail = textInputEmail;
        this.validadorPadrao = new ValidaCampoVazio(textInputEmail);
    }

    private boolean validaPadrao(String email){
        if(email.matches(".+@.+\\..+")){
            return true;
        }
        campoEmail.setError("E-mail inválido");
        return false;
    }

    @Override
    public boolean estaValido(){
        if(!validadorPadrao.estaValido()) return false;
        String email = campoEmail.getText().toString();
        if(!validaPadrao(email)) return false;
        return true;
    }
}
