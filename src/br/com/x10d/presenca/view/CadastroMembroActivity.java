package br.com.x10d.presenca.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.x10d.presenca.R;
import br.com.x10d.presenca.model.Cadastro;
import br.com.x10d.presenca.model.Congregacoes;
import br.com.x10d.presenca.util.TelaBuilder;
import br.com.x10d.presenca.validacao.ValidaCPF;
import br.com.x10d.presenca.validacao.ValidaCampoVazio;
import br.com.x10d.presenca.validacao.ValidaData;
import br.com.x10d.presenca.validacao.ValidaEmail;
import br.com.x10d.presenca.validacao.ValidaRG;
import br.com.x10d.presenca.validacao.ValidaTelefone;
import br.com.x10d.presenca.validacao.Validador;
import br.com.x10d.presenca.webservice.CadastroMembroWS;

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
	private Spinner spinnerCongregacao;
	private EditText etCargo;
	private EditText etRg;
	private EditText etCpf;
	private EditText etNomePai;
	private EditText etNomeMae;
	private Context context;
	private TelaBuilder telaBuilder;
	private LinearLayout.LayoutParams params_MATCH_WRAP;
	private RadioButton rbWhatsSim;
	private Cadastro membro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); 
		
		Bundle bundle = getIntent().getExtras();
		if(bundle != null) {
			membro = (Cadastro) bundle.getSerializable("membro");
		}
		context = CadastroMembroActivity.this;

		telaBuilder = new TelaBuilder(context);
		
		params_MATCH_WRAP = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		ScrollView scrollView = new ScrollView(context);
					scrollView.addView(criaLLtela());
		setContentView(scrollView);
	}
	
	private LinearLayout criaLLtela() {
		LinearLayout llTela = new LinearLayout(context);
		llTela.setOrientation(LinearLayout.VERTICAL );
		llTela.setPadding(10, 0, 10, 0);			
		//llTela.setBackgroundDrawable(devolveDrawableMarcaDagua());
		llTela.setBackgroundColor(context.getResources().getColor(R.color.background));
		
		llTela.addView(criaLLtitulo());
		llTela.addView(criaLLlinha1());
		llTela.addView(criaLLlinha2());
		llTela.addView(criaLLlinha3());
		llTela.addView(criaLLlinha4());
		llTela.addView(criaLLlinha5());
		llTela.addView(criaLLlinha6());
		llTela.addView(criaLLlinha7());
		llTela.addView(criaLLlinha8());
		llTela.addView(criaLLlinha9());
		llTela.addView(criaLLlinha10());
		llTela.addView(criaLLlinha11());
		llTela.addView(criaLLlinha12());
		llTela.addView(criaLLlinha13());
		llTela.addView(criaLLlinha14());
		llTela.addView(criaLLlinha15());
		llTela.addView(criaLLlinha16());
		llTela.addView(criaLLlinha17());
		llTela.addView(criaBotaoCadastrar(llTela));
		return llTela;
	}
	private Drawable devolveDrawableMarcaDagua() {
		ImageView ivMarcaDagua = new ImageView(context);
		ivMarcaDagua.setImageDrawable(getResources().getDrawable(R.drawable.logo_asdb));
		ivMarcaDagua.setAlpha(20);
		return ivMarcaDagua.getDrawable();
	}
	private ImageView devolveIVlogo() {
		ImageView ivLogo = new ImageView(context);
		ivLogo.setImageDrawable(getResources().getDrawable(R.drawable.logo_asdb));
		ivLogo.setLayoutParams(new LinearLayout.LayoutParams(130, 150));
		return ivLogo;
	}
	private LinearLayout criaLLtitulo() {
		LinearLayout llTitulo = new LinearLayout(context);
				TextView tvTitulo = new TextView(context);
						 tvTitulo.setText("Ficha de cadastro para membresia da\nAssembléia de Deus Belém - Setor 37 - Itapevi");
		llTitulo.addView(tvTitulo);
		llTitulo.addView(devolveIVlogo());
		return llTitulo;
	}
	private LinearLayout criaLLlinha1() {
		LinearLayout llLinha1 = new LinearLayout(context);
		llLinha1.addView(telaBuilder.criaTextViewTITULO("*Nome:"));
			etNome = criaETinscricao();
			etNome.setHint("Nome completo");
			etNome.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
			if(membro != null) {
				etNome.setText(membro.getNome());
			}
			
			adicionaValidacaoCampoVazio(etNome);
		llLinha1.addView(etNome);
		return llLinha1;
	}
	private LinearLayout criaLLlinha2() {
		LinearLayout llLinha2 = new LinearLayout(context);
		llLinha2.addView(telaBuilder.criaTextViewTITULO("Profissão:"));
			etProfissao = criaETinscricao();
			if(membro != null) {
				etProfissao.setText(membro.getProfissao());
			}
			
		llLinha2.addView(etProfissao);
		return llLinha2;
	}
	private LinearLayout criaLLlinha3() {
		LinearLayout llLinha3 = new LinearLayout(context);
		llLinha3.addView(telaBuilder.criaTextViewTITULO("Data Nascimento:"));
			etDtNasc = criaEtDataComValidacaoEFormatacao();
			etDtNasc.setLayoutParams(params_MATCH_WRAP);
			etDtNasc.setHint("dd/mm/aaaa");
			etDtNasc.setInputType(InputType.TYPE_CLASS_NUMBER);
			if(membro != null) {
				etDtNasc.setText(membro.getDataNascimento());
			}

		llLinha3.addView(etDtNasc);
		return llLinha3;
	}
	
	private LinearLayout criaLLlinha4() {
		LinearLayout llLinha4 = new LinearLayout(context);
		llLinha4.addView(telaBuilder.criaTextViewTITULO("Naturalidade:"));
			etNat = criaETinscricao();
			if(membro != null) {
				etNat.setText(membro.getNaturalidade());
			}

		llLinha4.addView(etNat);
		return llLinha4;
	} 
	private LinearLayout criaLLlinha5() {
		LinearLayout llLinha5 = new LinearLayout(context);
		llLinha5.addView(telaBuilder.criaTextViewTITULO("E-mail:"));
			etEmail = criaEtEmailComValidacaoEFormatacao();
			etEmail.setLayoutParams(params_MATCH_WRAP);
			if(membro != null) {
				etEmail.setText(membro.getEmail());
			}

		llLinha5.addView(etEmail);
		return llLinha5;
	}
	private LinearLayout criaLLlinha6() {
		LinearLayout llLinha6 = new LinearLayout(context);
		llLinha6.addView(telaBuilder.criaTextViewTITULO("Tel. Res.:"));
			etTelR = criaEtTelefoneComValidacaoEFormatacao(params_MATCH_WRAP);
			if(membro != null) {
				etTelR.setText(membro.getFoneResidencial());
			}

		llLinha6.addView(etTelR);
		return llLinha6;
	}
	private LinearLayout criaLLlinha7() {
		LinearLayout llLinha7 = new LinearLayout(context);
		llLinha7.addView(telaBuilder.criaTextViewTITULO("Tel. Cel.:"));
			etCel = criaEtTelefoneComValidacaoEFormatacao(params_MATCH_WRAP);
			if(membro != null) {
				etCel.setText(membro.getFoneCelular());
			}

		llLinha7.addView(etCel);
		return llLinha7;
	}
	private LinearLayout criaLLlinha8() {
		LinearLayout llLinha8 = new LinearLayout(context);
		llLinha8.addView(telaBuilder.criaTextViewTITULO("WhatsApp?"));
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
		llLinha8.addView(rgWhatsApp);
		return llLinha8;
	}
	private LinearLayout criaLLlinha9() {
		LinearLayout llLinha9 = new LinearLayout(context);
		llLinha9.addView(telaBuilder.criaTextViewTITULO("Endereço:"));
			etEnd = criaETinscricao();
			if(membro != null) {
				etEnd.setText(membro.getEndereco());
			}

			//adicionaValidacaoCampoVazio(etEnd);
		llLinha9.addView(etEnd);
		return llLinha9;
	}
	private LinearLayout criaLLlinha10() {
		LinearLayout llLinha10 = new LinearLayout(context);
		llLinha10.addView(telaBuilder.criaTextViewTITULO("Data Batismo:"));
			etDtBat = criaEtDataComValidacaoEFormatacao();
			etDtBat.setLayoutParams(params_MATCH_WRAP);
			etDtBat.setInputType(InputType.TYPE_CLASS_NUMBER);
			etDtBat.setHint("dd/mm/aaaa");
			if(membro != null) {
				etDtBat.setText(membro.getDataBatismo());
			}

		llLinha10.addView(etDtBat);
		return llLinha10;
	}
	private LinearLayout criaLLlinha11() {
		LinearLayout llLinha11 = new LinearLayout(context);
		llLinha11.addView(telaBuilder.criaTextViewTITULO("Local Batismo:"));
			etLcBat = criaETinscricao();
			if(membro != null) {
				etLcBat.setText(membro.getLocalBatismo());
			}

		llLinha11.addView(etLcBat);
		return llLinha11;
	}
	private LinearLayout criaLLlinha12() {
		LinearLayout llLinha12 = new LinearLayout(context);
		llLinha12.addView(telaBuilder.criaTextViewTITULO("Congregação:"));
			spinnerCongregacao = new Spinner(context);
			spinnerCongregacao.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			spinnerCongregacao.setAdapter(new ArrayAdapter(context, R.layout.item_menu_geral, Congregacoes.pegaLista()));
			spinnerCongregacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int posicaoSelecionada, long id) {
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {	
				}
			});
		llLinha12.addView(spinnerCongregacao);
		return llLinha12;
	}
	private LinearLayout criaLLlinha13() {
		LinearLayout llLinha13 = new LinearLayout(context);
		llLinha13.addView(telaBuilder.criaTextViewTITULO("Cargo:"));
			etCargo = criaETinscricao();
			if(membro != null) {
				etCargo.setText(membro.getCargo());
			}

		llLinha13.addView(etCargo);
		return llLinha13;
	}
	private LinearLayout criaLLlinha14() {
		LinearLayout llLinha14 = new LinearLayout(context);
		llLinha14.addView(telaBuilder.criaTextViewTITULO("RG:"));
			etRg = criaEtRGComValidacaoEFormatacao();
			etRg.setLayoutParams(params_MATCH_WRAP);
			etRg.setInputType(InputType.TYPE_CLASS_NUMBER);
			if(membro != null) {
				etRg.setText(membro.getRg());
			}

		llLinha14.addView(etRg);
		return llLinha14;
	}
	private LinearLayout criaLLlinha15() {
		LinearLayout llLinha15 = new LinearLayout(context);
		llLinha15.addView(telaBuilder.criaTextViewTITULO("*CPF:"));
			etCpf = criaEtCPFComValidacaoEFormatacao();
			etCpf.setLayoutParams(params_MATCH_WRAP);
			etCpf.setInputType(InputType.TYPE_CLASS_NUMBER);
			if(membro != null) {
				etCpf.setText(membro.getCpf());
			}

		llLinha15.addView(etCpf);
		return llLinha15;
	}
	private LinearLayout criaLLlinha16() {
		LinearLayout llLinha16 = new LinearLayout(context);
		llLinha16.addView(telaBuilder.criaTextViewTITULO("Nome Pai:"));
			etNomePai = criaETinscricao();
			etNomePai.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
			if(membro != null) {
				etNomePai.setText(membro.getNomePai());
			}

		llLinha16.addView(etNomePai);		
		return llLinha16;
	}
	private LinearLayout criaLLlinha17() {
		LinearLayout llLinha17 = new LinearLayout(context);
		llLinha17.addView(telaBuilder.criaTextViewTITULO("Nome Mãe:"));
			etNomeMae = criaETinscricao();
			etNomeMae.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
			if(membro != null) {
				etNomeMae.setText(membro.getNomeMae());
			}

		llLinha17.addView(etNomeMae);
		return llLinha17;
	}
	
    private Button criaBotaoCadastrar(final LinearLayout llTela) {

        Button btnCadastrar = telaBuilder.criaBotao("Salvar", params_MATCH_WRAP);
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
		membro.setCongregacao(spinnerCongregacao.getSelectedItem().toString());
		membro.setCargo(etCargo.getText().toString());
		membro.setRg(etRg.getText().toString());
		membro.setCpf(etCpf.getText().toString());
		membro.setNomePai(etNomePai.getText().toString());
		membro.setNomeMae(etNomeMae.getText().toString());
		
		new CadastroMembroWS(context, llTela).cadastrar(membro);
	}

	public EditText criaETinscricao(){
		
	    EditText editText = new EditText(context);	    
	    editText.setLayoutParams(params_MATCH_WRAP);
	    if(membro != null) {
	    	editText.setText(membro.getNome());
		}
		
	    return editText;
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


    private EditText criaEtTelefoneComValidacaoEFormatacao(LinearLayout.LayoutParams params) {
        
        final EditText etTelefone = new EditText(context);
        etTelefone.setLayoutParams(params);
        etTelefone.setInputType(InputType.TYPE_CLASS_NUMBER);

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
