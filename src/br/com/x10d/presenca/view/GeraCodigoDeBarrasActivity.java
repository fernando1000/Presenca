package br.com.x10d.presenca.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import br.com.x10d.presenca.model.Cadastro;
import br.com.x10d.presenca.util.TelaBuilder;
import br.com.x10d.presenca.webservice.ListaMembroDeAtehWS;
import br.com.x10d.presenca.webservice.ListaMembroTodosNaTelaWS;
import br.com.x10d.presenca.webservice.ListaMembroTodosWS;

public class GeraCodigoDeBarrasActivity extends Activity{

	private LinearLayout llDeAteh;
	private LinearLayout llTela;
	private LinearLayout llCodigo;
	private Context context;
	private EditText etCodigo;
	private RadioButton rbTodos;
	private RadioButton rbLista;
	private EditText etDe;
	private EditText etAteh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		
		context = GeraCodigoDeBarrasActivity.this;
		
		ScrollView scrollView = new ScrollView(context); 
		
		TelaBuilder telaBuilder = new TelaBuilder(context);

		llTela = telaBuilder.criaLinearLayoutTELA();
		llTela.setOrientation(LinearLayout.VERTICAL);
		
		TextView tvTitulo = telaBuilder.criaTextViewTITULO("Impressão de código de barras");
		
		rbTodos = new RadioButton(context);
		rbTodos.setId(1111);
		rbTodos.setText("Todos");
		rbTodos.setChecked(true);
		rbTodos.setOnCheckedChangeListener( new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					llDeAteh.setVisibility(View.GONE);
					llCodigo.setVisibility(View.GONE);
				}
			}	
		});
		
		rbLista = new RadioButton(context);
		rbLista.setId(2222);
		rbLista.setText("Lista");
		rbLista.setOnCheckedChangeListener( new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					llDeAteh.setVisibility(View.VISIBLE);
					llCodigo.setVisibility(View.GONE);
				}
			}
		});

		llDeAteh = new LinearLayout(context);
		//llDeAteh.setBackgroundColor(context.getResources().getColor(R.color.transparenteNegro));

		llDeAteh.setVisibility(View.GONE);
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, LayoutParams.WRAP_CONTENT); 
		
		TextView tvDe = telaBuilder.criaTextViewTITULO("Código DE:");
		
		etDe = telaBuilder.criaEditText("");
		etDe.setInputType(InputType.TYPE_CLASS_NUMBER);
		etDe.setLayoutParams(params);
		
		TextView tvAteh = telaBuilder.criaTextViewTITULO("Código Até:");
		
		etAteh = telaBuilder.criaEditText("");
		etAteh.setInputType(InputType.TYPE_CLASS_NUMBER);
		etAteh.setLayoutParams(params);
			
		llDeAteh.addView(tvDe);
		llDeAteh.addView(etDe);
		llDeAteh.addView(tvAteh);
		llDeAteh.addView(etAteh);

		RadioGroup radioGroup = new RadioGroup(context); 
		radioGroup.addView(rbTodos);
		radioGroup.addView(rbLista);
		//radioGroup.addView(rbIndividual);
		
		llCodigo = new LinearLayout(context);
		llCodigo.setVisibility(View.GONE);
		
		TextView tvCodigo = telaBuilder.criaTextViewTITULO("Código");
		
		etCodigo = telaBuilder.criaEditText("");
		etCodigo.setInputType(InputType.TYPE_CLASS_NUMBER);
		etCodigo.setLayoutParams(params);
	
		llCodigo.addView(tvCodigo);
		llCodigo.addView(etCodigo);
		
		LayoutParams lllp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);			

		Button gerarPDF = telaBuilder.criaBotao("Gerar PDF", lllp);
		gerarPDF.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				acaoGerarPDF();
			}
		});
				
	
		
		llTela.addView(tvTitulo);
		llTela.addView(radioGroup);
		llTela.addView(llDeAteh);
		llTela.addView(llCodigo);
		llTela.addView(gerarPDF);
		
		new ListaMembroTodosNaTelaWS(context, llTela).buscarListaComTodosMembros();
		
		//criaMembroMock();
		
		scrollView.addView(llTela);
		
		setContentView(scrollView);
	}
	
	

	private void criaMembroMock() {
		
		List<Cadastro> lista = new ArrayList<Cadastro>();
		Cadastro membro1 = new Cadastro();
		membro1.setId(1);
		membro1.setNome("fernando pereira santos");
		membro1.setCamiseta("camiseta");
		membro1.setCargo("cargo");
		membro1.setCodigoBarras("codigoBarras");
		membro1.setCongregacao("congregacao");
		membro1.setCpf("cpf");
		membro1.setDataBatismo("dataBatismo");
		membro1.setDataCadastro(new Date());
		membro1.setDataNascimento("dataNascimento");
		membro1.setEmail("email");
		membro1.setEndereco("endereco");
		membro1.setFoneCelular("foneCelular");
		membro1.setFoneResidencial("foneResidencial");
		membro1.setLocalBatismo("localBatismo");
		membro1.setNaturalidade("naturalidade");
		membro1.setNomeMae("nomeMae");
		membro1.setNomePai("nomePai");
		membro1.setProfissao("profissao");
		membro1.setRg("rg");
		membro1.setWhatsapp("whatsapp");
		
		
		Cadastro membro2 = new Cadastro();
		membro2.setId(2);
		membro2.setNome("lais ribeiro silva");
		lista.add(membro1);
		lista.add(membro2);
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.setMargins(10, 0, 10, 0);

		for(final Cadastro membro : lista) {
			
		LinearLayout llLinha = new LinearLayout(context);
		
		TextView tvId = new TextView(context);
		tvId.setText(""+membro.getId());
		tvId.setLayoutParams(lp);
	
		String nome = membro.getNome();
		  int tamanhoNome = nome.length();
		  if(tamanhoNome > 30 ) {
			  nome = nome.substring(0, 30);
		}
		TextView tvNomeMembro = new TextView(context);
		tvNomeMembro.setText(nome);
		tvNomeMembro.setLayoutParams(lp);
		//INICIO adicionei este codigo:
		//tvNomeMembro.setTag(membro.getId());
		tvNomeMembro.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				Intent intent = new Intent(context, CadastroMembroActivity.class);
						  Bundle bundle = new Bundle();
								 bundle.putSerializable("membro", membro);
				intent.putExtras(bundle);
				startActivity(intent);	
			}
		});
		//FIM
			
		llLinha.addView(tvId);
		llLinha.addView(tvNomeMembro);
		
		llTela.addView(llLinha);
		}
	}
	
	private void acaoGerarPDF(){

		if(rbTodos.isChecked()) {
			
			new ListaMembroTodosWS(context).buscarListaComTodosMembros();
		}

		if(rbLista.isChecked()) {
			
			int de = devolveInteiroValido(etDe);
			int ateh = devolveInteiroValido(etAteh);
			
			new ListaMembroDeAtehWS(context).buscarListaDeMembrosDeAteh(de, ateh);
		}
		/*
		if(rbIndividual.isChecked()) {
			int id = devolveInteiroValido(etCodigo);
			new ListaMembroPorIdWS(context).buscarListaDeMembros(id);
		}
		*/
	}
	
	private int devolveInteiroValido(EditText editText) {
		
		String stringNumero = "0";
		
		if(!editText.getText().toString().isEmpty()) {
			stringNumero = editText.getText().toString();
		}
	
		int numero = Integer.parseInt(stringNumero);

		return numero;
	}
	
}
