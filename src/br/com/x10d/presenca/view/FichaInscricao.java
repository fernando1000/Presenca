package br.com.x10d.presenca.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView.LayoutParams;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.x10d.presenca.R;
import br.com.x10d.presenca.dao.Dao;
import br.com.x10d.presenca.model.Membro;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.validacao.ValidaCPF;
import br.com.x10d.presenca.validacao.ValidaCampoVazio;
import br.com.x10d.presenca.validacao.ValidaEmail;
import br.com.x10d.presenca.validacao.ValidaTelefone;
import br.com.x10d.presenca.validacao.Validador;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class FichaInscricao extends Activity{

    private final List<Validador> listaComValidadores = new ArrayList<Validador>();

	private EditText etNome;
	private EditText etProfissao;
	private EditText etDtNasc;
	private EditText etNat;
	private EditText etEmail;
	private EditText etTelR;
	private EditText etCel;
	private EditText etEnd;
	private EditText etDtBat;
	private EditText etLcBat;
	private EditText etCongregacao;
	private EditText etCargo;
	private EditText etRg;
	private EditText etCpf;
	private EditText etNomePai;
	private EditText etNomeMae;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = FichaInscricao.this;
		
		ScrollView scrollView = new ScrollView(context);
		
		final LinearLayout llTela = new LinearLayout(context);
		llTela.setOrientation(LinearLayout.VERTICAL );
		llTela.setPadding(10, 0, 10, 0);
		
//		Deixei comentado para incluir a imagem de fundo posteriormente
		//llTela.setBackgroundColor(getResources().getColor(android.R.color.white));
		
	
		ImageView ivMarcaDagua = new ImageView(context);
		ivMarcaDagua.setImageDrawable(getResources().getDrawable(R.drawable.new_asdb));
		ivMarcaDagua.setAlpha(20);
		
		llTela.setBackgroundDrawable(ivMarcaDagua.getDrawable());
		
		
		LinearLayout llTitulo = new LinearLayout(context);
		
		LinearLayout llLinha1 = new LinearLayout(context);
		
		TextView tvTitulo = new TextView(context);
		tvTitulo.setText("Ficha de cadastro para membresia da\nAssembléia de Deus Belém - Setor 37 - Itapevi");
		
		ImageView ivLogo = new ImageView(context);
		ivLogo.setImageDrawable(getResources().getDrawable(R.drawable.new_asdb));
		ivLogo.setLayoutParams(new LinearLayout.LayoutParams(130, 150));
		
		llTitulo.addView(tvTitulo);
		llTitulo.addView(ivLogo);
		
		TextView tvNome = new TextView(context);
		tvNome.setText("*Nome");
		
		etNome = new EditText(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etNome.setLayoutParams(params);
		etNome.setHint("Nome completo");
        adicionaValidacaoCampoVazio(etNome);
      
		llLinha1.addView(tvNome);
		llLinha1.addView(etNome);
		
		LinearLayout llLinha2 = new LinearLayout(context);
			
		TextView tvProfissao = new TextView(context);
		tvProfissao.setText("Profissão");
		llLinha2.addView(tvProfissao);
		
		etProfissao = new EditText(context);
		LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etProfissao.setLayoutParams(params2);
    	
		
		llLinha2.addView(etProfissao);
		
		LinearLayout llLinha3 = new LinearLayout(context);
		
		TextView tvDtNasc = new TextView(context);
		tvDtNasc.setText("*Data nascimento");
		
		llLinha3.addView(tvDtNasc);
		
		etDtNasc = new EditText(context);
		LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etDtNasc.setLayoutParams(params3);
		etDtNasc.setHint("Data Nascimento");
        adicionaValidacaoCampoVazio(etDtNasc);
    
		llLinha3.addView(etDtNasc);
		
		LinearLayout llLinha4 = new LinearLayout(context);
		
		TextView tvNat = new TextView(context);
		tvNat.setText("Naturalidade");
		llLinha4.addView(tvNat);
		
		etNat = new EditText(context);
		LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etNat.setLayoutParams(params4);

		llLinha4.addView(etNat);
		
		LinearLayout llLinha5 = new LinearLayout(context);
		
		TextView tvEmail = new TextView(context);
		tvEmail.setText("E-mail");
		llLinha5.addView(tvEmail);
		
		etEmail = criaEtEmailComValidacaoEFormatacao();
		LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etEmail.setLayoutParams(params5);
		
		llLinha5.addView(etEmail);
		
		LinearLayout llLinha6 = new LinearLayout(context);
		
		TextView tvTelR = new TextView(context);
		tvTelR.setText("Tel Res.");
		llLinha6.addView(tvTelR);
		
		etTelR = criaEtTelefoneComValidacaoEFormatacao();
		LinearLayout.LayoutParams params6 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etTelR.setLayoutParams(params6);

		llLinha6.addView(etTelR);
		
		LinearLayout llLinha7 = new LinearLayout(context);
		
		TextView tvCel = new TextView(context);
		tvCel.setText("Cel.");
		llLinha7.addView(tvCel);
		
		etCel = criaEtTelefoneComValidacaoEFormatacao();
		LinearLayout.LayoutParams params7 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etCel.setLayoutParams(params7);
		
		llLinha7.addView(etCel);
		
		LinearLayout llLinha8 = new LinearLayout(context);
		
		TextView tvWhats = new TextView(context);
		tvWhats.setText("Whats?");
		llLinha8.addView(tvWhats);
		
		RadioButton rbWhatsSim = new RadioButton(context);
		rbWhatsSim.setText("Sim");
		
		RadioButton rbWhatsNao = new RadioButton(context);
		rbWhatsNao.setText("Não");
		
		RadioGroup rgWhats = new RadioGroup(context);
		rgWhats.setOrientation(LinearLayout.HORIZONTAL);
		rgWhats.addView(rbWhatsSim);
		rgWhats.addView(rbWhatsNao);
		
		llLinha8.addView(rgWhats);
		
		LinearLayout llLinha9 = new LinearLayout(context);
		
		TextView tvEnd = new TextView(context);
		tvEnd.setText("*Endereço");
		llLinha9.addView(tvEnd);
		
		etEnd = new EditText(context);
		LinearLayout.LayoutParams params9 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etEnd.setLayoutParams(params9);
		etEnd.setHint("Endereço");
        adicionaValidacaoCampoVazio(etEnd);
    
		llLinha9.addView(etEnd);
		
		LinearLayout llLinha10 = new LinearLayout(context);
		
		TextView tvDtBat = new TextView(context);
		tvDtBat.setText("Data de batismo");
		llLinha10.addView(tvDtBat);
		
		etDtBat = new EditText(context);
		LinearLayout.LayoutParams params10 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etDtBat.setLayoutParams(params10);
		
		llLinha10.addView(etDtBat);
		
		LinearLayout llLinha11 = new LinearLayout(context);
		
		TextView tvLcBat = new TextView(context);
		tvLcBat.setText("Local de batismo");
		llLinha11.addView(tvLcBat);
		
		etLcBat = new EditText(context);
		LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etLcBat.setLayoutParams(params11);
		
		llLinha11.addView(etLcBat);
		
		LinearLayout llLinha12 = new LinearLayout(context);
		
		TextView tvCongregacao = new TextView(context);
		tvCongregacao.setText("*Congregação");
		llLinha12.addView(tvCongregacao);
		
		etCongregacao = new EditText(context);
		LinearLayout.LayoutParams params12 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etCongregacao.setLayoutParams(params12);
		etCongregacao.setHint("Nome congregação");
        adicionaValidacaoCampoVazio(etCongregacao);
    
		llLinha12.addView(etCongregacao);

		LinearLayout llLinha13 = new LinearLayout(context);
		
		TextView tvCargo = new TextView(context);
		tvCargo.setText("Cargo");
		llLinha13.addView(tvCargo);
		
		etCargo = new EditText(context);
		LinearLayout.LayoutParams params13 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etCargo.setLayoutParams(params13);
		
		llLinha13.addView(etCargo);
		
		LinearLayout llLinha14 = new LinearLayout(context);
		
		TextView tvRg = new TextView(context);
		tvRg.setText("RG");
		llLinha14.addView(tvRg);
		
		etRg = new EditText(context);
		LinearLayout.LayoutParams params14 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etRg.setLayoutParams(params14);
		
		llLinha14.addView(etRg);
		
		LinearLayout llLinha15 = new LinearLayout(context);
		
		TextView tvCpf = new TextView(context);
		tvCpf.setText("*CPF");
		llLinha15.addView(tvCpf);
		
		etCpf = criaEtCPFComValidacaoEFormatacao();
		LinearLayout.LayoutParams params15 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etCpf.setLayoutParams(params15);
		
		llLinha15.addView(etCpf);
		
		LinearLayout llLinha16 = new LinearLayout(context);
		
		TextView tvNomePai = new TextView(context);
		tvNomePai.setText("Nome do Pai");
		llLinha16.addView(tvNomePai);
		
		etNomePai = new EditText(context);
		LinearLayout.LayoutParams params16 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etNomePai.setLayoutParams(params16);
		
		llLinha16.addView(etNomePai);		

		LinearLayout llLinha17 = new LinearLayout(context);
		
		TextView tvNomeMae = new TextView(context);
		tvNomeMae.setText("Nome da Mãe");
		llLinha17.addView(tvNomeMae);
		
		etNomeMae = new EditText(context);
		LinearLayout.LayoutParams params17 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etNomeMae.setLayoutParams(params17);
		
		llLinha17.addView(etNomeMae);
		
		
		llTela.addView(llTitulo);
		llTela.addView(llLinha1);
		llTela.addView(llLinha2);
		llTela.addView(llLinha3);
		llTela.addView(llLinha4);
		llTela.addView(llLinha5);
		llTela.addView(llLinha6);
		llTela.addView(llLinha7);
		llTela.addView(llLinha8);
		llTela.addView(llLinha9);
		llTela.addView(llLinha10);
		llTela.addView(llLinha11);
		llTela.addView(llLinha12);
		llTela.addView(llLinha13);
		llTela.addView(llLinha14);
		llTela.addView(llLinha15);
		llTela.addView(llLinha16);
		llTela.addView(llLinha17);
		llTela.addView(criaBotaoCadastrar(llTela));
		
		scrollView.addView(llTela);
		setContentView(scrollView);
	}
	
    private Button criaBotaoCadastrar(final LinearLayout llTela) {
    	
        Button btnCadastrar = new Button(context);
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
                boolean formularioEstaValido = validaTodosCampos();
                
                if(formularioEstaValido){
                	
                	acaoSalvar(llTela);
                }
            }
        });
        
        return btnCadastrar;
    }
    
	private void acaoSalvar(LinearLayout llTela) {
				
		Membro membro = new Membro();
		
		membro.setNome(etNome.getText().toString());
		membro.setProfissao(etProfissao.getText().toString());
		membro.setData_nascimento(etDtNasc.getText().toString());
		membro.setNaturalidade(etNat.getText().toString());
		membro.setEmail(etEmail.getText().toString());
		membro.setFone_residencial(etTelR.getText().toString());
		membro.setFone_celular(etCel.getText().toString());
		membro.setEndereco(etEnd.getText().toString());
		membro.setData_batismo(etDtBat.getText().toString());
		membro.setLocal_batismo(etLcBat.getText().toString());
		membro.setCongregacao(etCongregacao.getText().toString());
		membro.setCargo(etCargo.getText().toString());
		membro.setRg(etRg.getText().toString());
		membro.setCpf(etCpf.getText().toString());
		membro.setNome_pai(etNomePai.getText().toString());
		membro.setNome_mae(etNomeMae.getText().toString());
	
		try {
			Dao dao = new Dao(context); 
				dao.insereOUatualiza(membro, Membro.COLUMN_TEXT_CPF, membro.getCpf());
			
			new MeuAlerta("Aviso", "Cadastro realizado com sucesso!", context).meuAlertaOk();
			
			limpaTodosOsCampos(llTela);
			

		} catch (Exception ex) {
			ex.printStackTrace();	
			new MeuAlerta("Erro", ""+ex, context).meuAlertaOk();
		}
	
	}

	private void limpaTodosOsCampos(LinearLayout llTela) {
		
		for(int i=0; i<llTela.getChildCount(); i++) {
			
			Object obj = llTela.getChildAt(i);
			
			if(obj instanceof LinearLayout) {
			
				LinearLayout llLinha = (LinearLayout) obj;
				
				for(int x=0; x<llLinha.getChildCount(); x++) {
				
					Object obj2 = llLinha.getChildAt(x);
					
					if(obj2 instanceof EditText) {
						
						EditText editText = (EditText) obj2;
						editText.setText("");
					}
				}
			}
		}
	}
    
    private boolean validaTodosCampos() {
    	
        boolean formularioEstaValido = true;
        
        for (Validador validador : listaComValidadores) {
        	
            if(!validador.estaValido()){
            	
                formularioEstaValido = false;
            }
        }
        return formularioEstaValido;
    }


    private EditText criaEtTelefoneComValidacaoEFormatacao() {
        
        final EditText etTelefone = new EditText(context);
        etTelefone.setHint("telefone com ddd");
        
        final ValidaTelefone validaTelefone = new ValidaTelefone(etTelefone);
        
        listaComValidadores.add(validaTelefone);
        
        //final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();
        
        etTelefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            	
                String telefoneComDdd = etTelefone.getText().toString();
                
                if(hasFocus){
                	
                    String telefoneComDddSemFormatacao = validaTelefone.removeFormatacao(telefoneComDdd);
                    etTelefone.setText(telefoneComDddSemFormatacao);
                } else {
                	validaTelefone.estaValido();
                }
            }
        });
        
        return etTelefone;
    }

    private EditText criaEtCPFComValidacaoEFormatacao() {
    	
        final EditText etCPF = new EditText(context);
        etCPF.setHint("CPF");
        
        final CPFFormatter cPFFormatter = new CPFFormatter();
        
        final ValidaCPF validaCpf = new ValidaCPF(etCPF);
        
        listaComValidadores.add(validaCpf);
        
        etCPF.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            	
                if (hasFocus) {
                	
                    removeFormatacaoCPF(cPFFormatter, etCPF);
                } else {
                	validaCpf.estaValido();
                }
            }
        });

        return etCPF;
    }

    private void removeFormatacaoCPF(CPFFormatter cPFFormatter, EditText etCPF) {
    	
        String cpf = etCPF.getText().toString();
        
        try {
            String cpfSemFormato = cPFFormatter.unformat(cpf);
            
            etCPF.setText(cpfSemFormato);
            
        } catch (IllegalArgumentException ex){
           
            Log.i("tag","Erro ao apagar a formatacao do CPF -> "+ex.getMessage());
            
        }
    }

    private EditText criaEtEmailComValidacaoEFormatacao() {
        
        EditText etEmail = new EditText(context);
        etEmail.setHint("email");
        final ValidaEmail validaEmail = new ValidaEmail(etEmail);
        listaComValidadores.add(validaEmail);
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            	
                if(!hasFocus){
                	
                	validaEmail.estaValido();
                }
            }
        });

        return etEmail;
    }

    private void adicionaValidacaoCampoVazio(EditText editText) {
    	
        final ValidaCampoVazio validacaoCampoVazio = new ValidaCampoVazio(editText);
        
        listaComValidadores.add(validacaoCampoVazio);
        
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            	
                if (!hasFocus) {
                	
                	validacaoCampoVazio.estaValido();
                }
            }
        });
        
    }

	
	
	
	
}
