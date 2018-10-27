package br.com.x10d.presenca.view;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView.LayoutParams;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.x10d.presenca.R;
import br.com.x10d.presenca.model.Cadastro;
import br.com.x10d.presenca.validacao.ValidaCPF;
import br.com.x10d.presenca.validacao.ValidaCampoVazio;
import br.com.x10d.presenca.validacao.ValidaData;
import br.com.x10d.presenca.validacao.ValidaEmail;
import br.com.x10d.presenca.validacao.ValidaRG;
import br.com.x10d.presenca.validacao.ValidaTelefone;
import br.com.x10d.presenca.validacao.Validador;
import br.com.x10d.presenca.webservice.CadastroMembroWS;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class CadastroMembroActivity extends Activity{

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
	private RadioButton rbWhatsSim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); 

		context = CadastroMembroActivity.this;
		
		ScrollView scrollView = new ScrollView(context);
		
		final LinearLayout llTela = new LinearLayout(context);
		llTela.setOrientation(LinearLayout.VERTICAL );
		llTela.setPadding(10, 0, 10, 0);
	
		ImageView ivMarcaDagua = new ImageView(context);
		ivMarcaDagua.setImageDrawable(getResources().getDrawable(R.drawable.logo_asdb));
		ivMarcaDagua.setAlpha(20);
		
		llTela.setBackgroundDrawable(ivMarcaDagua.getDrawable());
		
		
		LinearLayout llTitulo = new LinearLayout(context);
		
		LinearLayout llLinha1 = new LinearLayout(context);
		
		TextView tvTitulo = new TextView(context);
		tvTitulo.setText("Ficha de cadastro para membresia da\nAssembléia de Deus Belém - Setor 37 - Itapevi");
		
		ImageView ivLogo = new ImageView(context);
		ivLogo.setImageDrawable(getResources().getDrawable(R.drawable.logo_asdb));
		ivLogo.setLayoutParams(new LinearLayout.LayoutParams(130, 150));
		
		llTitulo.addView(tvTitulo);
		llTitulo.addView(ivLogo);
		
		TextView tvNome = new TextView(context);
		tvNome.setText("*Nome");
		
		etNome = new EditText(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etNome.setLayoutParams(params);
		etNome.setHint("Nome completo");
		etNome.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
	    
        adicionaValidacaoCampoVazio(etNome);
      
		llLinha1.addView(tvNome);
		llLinha1.addView(etNome);
		
		LinearLayout llLinha2 = new LinearLayout(context);
			
		TextView tvProfissao = new TextView(context);
		tvProfissao.setText("Profissão");
		llLinha2.addView(tvProfissao);
		
		etProfissao = new EditText(context);
		etProfissao.setLayoutParams(params);
    	
		
		llLinha2.addView(etProfissao);
		
		LinearLayout llLinha3 = new LinearLayout(context);
		
		TextView tvDtNasc = new TextView(context);
		tvDtNasc.setText("Data nascimento");
		
		
		llLinha3.addView(tvDtNasc);
		
		etDtNasc = criaEtDataComValidacaoEFormatacao();
		etDtNasc.setLayoutParams(params);
		etDtNasc.setHint("dd/mm/aaaa");
		etDtNasc.setInputType(InputType.TYPE_CLASS_NUMBER);
    
		llLinha3.addView(etDtNasc);
		
		LinearLayout llLinha4 = new LinearLayout(context);
		
		TextView tvNat = new TextView(context);
		tvNat.setText("Naturalidade");
		llLinha4.addView(tvNat);
		
		etNat = new EditText(context);
		etNat.setLayoutParams(params);

		llLinha4.addView(etNat);
		
		LinearLayout llLinha5 = new LinearLayout(context);
		
		TextView tvEmail = new TextView(context);
		tvEmail.setText("E-mail");
		llLinha5.addView(tvEmail);
		
		etEmail = criaEtEmailComValidacaoEFormatacao();
		etEmail.setLayoutParams(params);
		
		llLinha5.addView(etEmail);
		
		LinearLayout llLinha6 = new LinearLayout(context);
		
		TextView tvTelR = new TextView(context);
		tvTelR.setText("Tel Res.");
		llLinha6.addView(tvTelR);
		
		etTelR = criaEtTelefoneComValidacaoEFormatacao();
		etTelR.setLayoutParams(params);
		etTelR.setInputType(InputType.TYPE_CLASS_NUMBER);
		llLinha6.addView(etTelR);
		
		LinearLayout llLinha7 = new LinearLayout(context);
		
		TextView tvCel = new TextView(context);
		tvCel.setText("Cel.");
		llLinha7.addView(tvCel);
		
		etCel = criaEtTelefoneComValidacaoEFormatacao();
		etCel.setLayoutParams(params);
		etCel.setInputType(InputType.TYPE_CLASS_NUMBER);
		llLinha7.addView(etCel);
		
		LinearLayout llLinha8 = new LinearLayout(context);
		
		TextView tvWhats = new TextView(context);
		tvWhats.setText("WhatsApp?");
		llLinha8.addView(tvWhats);
		
		final RadioGroup rgWhatsApp = new RadioGroup(context);
		rgWhatsApp.setOrientation(LinearLayout.HORIZONTAL);
		rgWhatsApp.check(123);
		
						rbWhatsSim = new RadioButton(context);
						rbWhatsSim.setId(111);
						rbWhatsSim.setText("Sim");
		rgWhatsApp.addView(rbWhatsSim);

			RadioButton rbWhatsNao = new RadioButton(context);
						rbWhatsNao.setId(000);
						rbWhatsNao.setText("Não");
						rbWhatsNao.setChecked(true);
		rgWhatsApp.addView(rbWhatsNao);
		
		/*
		rgWhatsApp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
		        @Override
		        public void onCheckedChanged(RadioGroup group, int checkedId) {
		        	
		            int checkedRadioButtonId = rgWhatsApp.getCheckedRadioButtonId();
		            
		            RadioButton radioBtn = (RadioButton) findViewById(checkedRadioButtonId);
		            
		            Toast.makeText(context, radioBtn.getText(), Toast.LENGTH_SHORT).show();
		        }
		    });
		*/
		llLinha8.addView(rgWhatsApp);
		
		LinearLayout llLinha9 = new LinearLayout(context);
		
		TextView tvEnd = new TextView(context);
		tvEnd.setText("*Endereço");
		llLinha9.addView(tvEnd);
		
		etEnd = new EditText(context);
		etEnd.setLayoutParams(params);
		
        adicionaValidacaoCampoVazio(etEnd);
    
		llLinha9.addView(etEnd);
		
		LinearLayout llLinha10 = new LinearLayout(context);
		
		TextView tvDtBat = new TextView(context);
		tvDtBat.setText("Data de batismo");
		llLinha10.addView(tvDtBat);
		
		etDtBat = criaEtDataComValidacaoEFormatacao();
		etDtBat.setLayoutParams(params);
		etDtBat.setInputType(InputType.TYPE_CLASS_NUMBER);
		etDtBat.setHint("dd/mm/aaaa");
		
		llLinha10.addView(etDtBat);
		
		LinearLayout llLinha11 = new LinearLayout(context);
		
		TextView tvLcBat = new TextView(context);
		tvLcBat.setText("Local de batismo");
		llLinha11.addView(tvLcBat);
		
		etLcBat = new EditText(context);
		etLcBat.setLayoutParams(params);
		
		llLinha11.addView(etLcBat);
		
		LinearLayout llLinha12 = new LinearLayout(context);
		
		TextView tvCongregacao = new TextView(context);
		tvCongregacao.setText("*Congregação");
		llLinha12.addView(tvCongregacao);
		
		etCongregacao = new EditText(context);
		etCongregacao.setLayoutParams(params);
		
        adicionaValidacaoCampoVazio(etCongregacao);
    
		llLinha12.addView(etCongregacao);

		LinearLayout llLinha13 = new LinearLayout(context);
		
		TextView tvCargo = new TextView(context);
		tvCargo.setText("Cargo");
		llLinha13.addView(tvCargo);
		
		etCargo = new EditText(context);
		etCargo.setLayoutParams(params);
		
		llLinha13.addView(etCargo);
		
		LinearLayout llLinha14 = new LinearLayout(context);
		
		TextView tvRg = new TextView(context);
		tvRg.setText("RG");
		llLinha14.addView(tvRg);
		
		etRg = criaEtRGComValidacaoEFormatacao();
		etRg.setLayoutParams(params);
		etRg.setInputType(InputType.TYPE_CLASS_NUMBER);
		llLinha14.addView(etRg);
		
		LinearLayout llLinha15 = new LinearLayout(context);
		
		TextView tvCpf = new TextView(context);
		tvCpf.setText("*CPF");
		llLinha15.addView(tvCpf);
		
		etCpf = criaEtCPFComValidacaoEFormatacao();
		etCpf.setLayoutParams(params);
		etCpf.setInputType(InputType.TYPE_CLASS_NUMBER);
		llLinha15.addView(etCpf);
		
		LinearLayout llLinha16 = new LinearLayout(context);
		
		TextView tvNomePai = new TextView(context);
		tvNomePai.setText("Nome do Pai");
		llLinha16.addView(tvNomePai);
		
		etNomePai = new EditText(context);
		etNomePai.setLayoutParams(params);
		etNomePai.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
		   
		llLinha16.addView(etNomePai);		

		LinearLayout llLinha17 = new LinearLayout(context);
		
		TextView tvNomeMae = new TextView(context);
		tvNomeMae.setText("Nome da Mãe");
		llLinha17.addView(tvNomeMae);
		
		etNomeMae = new EditText(context);
		etNomeMae.setLayoutParams(params);
		etNomeMae.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
		   
		
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
				
		Cadastro membro = new Cadastro();
		
		membro.setNome(etNome.getText().toString());
		membro.setProfissao(etProfissao.getText().toString());
		membro.setDataNascimento(etDtNasc.getText().toString());
		membro.setNaturalidade(etNat.getText().toString());
		membro.setEmail(etEmail.getText().toString());
		membro.setFoneResidencial(etTelR.getText().toString());
		membro.setFoneCelular(etCel.getText().toString());
		membro.setWhatsapp(""+rbWhatsSim.isChecked());
		membro.setEndereco(etEnd.getText().toString());
		membro.setDataBatismo(etDtBat.getText().toString());
		membro.setLocalBatismo(etLcBat.getText().toString());
		membro.setCongregacao(etCongregacao.getText().toString());
		membro.setCargo(etCargo.getText().toString());
		membro.setRg(etRg.getText().toString());
		membro.setCpf(etCpf.getText().toString());
		membro.setNomePai(etNomePai.getText().toString());
		membro.setNomeMae(etNomeMae.getText().toString());
		
		new CadastroMembroWS(context, llTela).cadastrar(membro);
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

    private EditText criaEtRGComValidacaoEFormatacao() {
        
        final EditText etRG = new EditText(context);
        etRG.setFilters( new InputFilter[] { new InputFilter.LengthFilter(12) } );
        
        final ValidaRG validaRG = new ValidaRG(etRG);
        
        if(!etRG.getText().toString().isEmpty()) {
        
        	listaComValidadores.add(validaRG);
        }
        
        etRG.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            	
                String rg = etRG.getText().toString();
                
                if(hasFocus){
                	
                    String rgSemFormatacao = validaRG.removeFormatacao(rg);
                    etRG.setText(rgSemFormatacao);
                } else {
                	
                    if(!etRG.getText().toString().isEmpty()) {
                    
                    	validaRG.estaValido();
                    }
                	
                }
            }
        });
        
        return etRG;
    }


    private EditText criaEtTelefoneComValidacaoEFormatacao() {
        
        final EditText etTelefone = new EditText(context);
        etTelefone.setHint("telefone com ddd");
        etTelefone.setFilters( new InputFilter[] { new InputFilter.LengthFilter(16) } );
        
        final ValidaTelefone validaTelefone = new ValidaTelefone(etTelefone);
        
        if(!etTelefone.getText().toString().isEmpty()) {
        
        	listaComValidadores.add(validaTelefone);
        }
        
        etTelefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            	
                String telefoneComDdd = etTelefone.getText().toString();
                
                if(hasFocus){
                	
                    String telefoneComDddSemFormatacao = validaTelefone.removeFormatacao(telefoneComDdd);
                    etTelefone.setText(telefoneComDddSemFormatacao);
                } else {
                	
                    if(!etTelefone.getText().toString().isEmpty()) {
                    
                    	validaTelefone.estaValido();
                    }
                	
                }
            }
        });
        
        return etTelefone;
    }

    private EditText criaEtDataComValidacaoEFormatacao() {
        
        final EditText etData = new EditText(context);
        etData.setFilters( new InputFilter[] { new InputFilter.LengthFilter(12) } );
        
        final ValidaData validaData = new ValidaData(etData);
        
        if(!etData.getText().toString().isEmpty()) {
        
        	listaComValidadores.add(validaData);
        }
        
        etData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            	
                String data = etData.getText().toString();
                
                if(hasFocus){
                	
                    String dataSemFormatacao = validaData.removeFormatacao(data);
                    etData.setText(dataSemFormatacao);
                } else {
                	
                    if(!etData.getText().toString().isEmpty()) {
                    
                    	validaData.estaValido();
                    }
                	
                }
            }
        });
        
        return etData;
    }

    private EditText criaEtCPFComValidacaoEFormatacao() {
    	
        final EditText etCPF = new EditText(context);
        etCPF.setFilters( new InputFilter[] { new InputFilter.LengthFilter(14) } );
        
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
        
        final EditText etEmail = new EditText(context);
        
        final ValidaEmail validaEmail = new ValidaEmail(etEmail);

        if(!etEmail.getText().toString().isEmpty()) {
     	
     		listaComValidadores.add(validaEmail);
     	}
        
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            	
                if(!hasFocus){
                	
                    if(!etEmail.getText().toString().isEmpty()) {
                    
                    	validaEmail.estaValido();
                    }	
                	
                	
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
