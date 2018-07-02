package br.com.x10d.presenca.validacao;

import android.widget.EditText;

public class ValidaTelefone implements Validador {

    public static final String DEVE_TER_DEZ_OU_ONZE_DIGITOS = "Telefone deve ter entre 10 a 11 digitos";
    
    private final EditText campoTelefoneComDdd;
    private final ValidaCampoVazio validacaoPadrao;
    //private final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();

    public ValidaTelefone(EditText textInputTelefoneComDdd) {
    	
        
        this.campoTelefoneComDdd = textInputTelefoneComDdd;
        this.validacaoPadrao = new ValidaCampoVazio(textInputTelefoneComDdd);
    }

    private boolean validaEntreDezOuOnzeDigitos(String telefoneComDdd){
        int digitos = telefoneComDdd.length();
        if(digitos < 10 || digitos > 11){
        	
        	campoTelefoneComDdd.setError(DEVE_TER_DEZ_OU_ONZE_DIGITOS);
            return false;
        }
        return true;
    }

    @Override
    public boolean estaValido(){
    	
        if(!validacaoPadrao.estaValido()) return false;
        
        String telefoneComDdd = campoTelefoneComDdd.getText().toString();
        
        String telefoneComDddSemFormatacao = removeFormatacao(telefoneComDdd);
        
        if(!validaEntreDezOuOnzeDigitos(telefoneComDddSemFormatacao)) return false;
        
        adicionaFormatacao(telefoneComDddSemFormatacao);
        
        return true;
    }

    private void adicionaFormatacao(String telefoneComDdd) {
        String telefoneComDddFormatado = formata(telefoneComDdd);
        campoTelefoneComDdd.setText(telefoneComDddFormatado);
    }

    private String formata(String telefoneComDdd) {
    	
        return telefoneComDdd.replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3");
    }

    public String removeFormatacao(String telefoneComDdd) {
    	
        return telefoneComDdd.replace("(", "")
        					 .replace(")", "")
        					 .replace(" ", "")
        					 .replace("-", "");
    }


}
