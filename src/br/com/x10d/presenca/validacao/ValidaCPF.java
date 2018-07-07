package br.com.x10d.presenca.validacao;

import android.util.Log;
import android.widget.EditText;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidaCPF implements Validador {

    public static final String CPF_INVALIDO = "CPF invalido";
    public static final String DEVE_TER_ONZE_DIGITOS = "O CPF precisa ter 11 digitos";
    private static final String ERRO_FORMATAO_CPF = "erro formatação CPF";
    private final EditText etCPF;
    private final ValidaCampoVazio validaCampoVazio;
    private final CPFFormatter cPFFormatter = new CPFFormatter();

    public ValidaCPF(EditText etCPF) {
    	
        this.etCPF = etCPF;
        this.validaCampoVazio = new ValidaCampoVazio(etCPF);
    }

    private boolean validaCalculoCpf(String cpf) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e){
        	etCPF.setError(CPF_INVALIDO);
            return false;
        }
        return true;
    }

    private boolean validaCampoComOnzeDigitos(String cpf) {
        if (cpf.length() != 11) {
        	etCPF.setError(DEVE_TER_ONZE_DIGITOS);
            return false;
        }
        return true;
    }

    @Override
    public boolean estaValido(){
    	
        if(!validaCampoVazio.estaValido()) {        	
        	return false;
        } 
        
        String cpf = getCpf();
        
        String cpfSemFormato = cpf;
        
        try {
            cpfSemFormato = cPFFormatter.unformat(cpf);
            
        } catch (IllegalArgumentException e){
        	
            Log.e(ERRO_FORMATAO_CPF, e.getMessage());
        }
        
        if(!validaCampoComOnzeDigitos(cpfSemFormato)) return false;
        
        if(!validaCalculoCpf(cpfSemFormato)) return false;
        
        adicionaFormatacao(cpfSemFormato);
        
        return true;
    }

    private void adicionaFormatacao(String cpf) {
        String cpfFormatado = cPFFormatter.format(cpf);
        etCPF.setText(cpfFormatado);
    }

    private String getCpf() {
        return etCPF.getText().toString();
    }

}
