package br.com.x10d.presenca.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import br.com.x10d.presenca.util.TelaBuilder;
import br.com.x10d.presenca.webservice.ListaMembroDeAtehWS;
import br.com.x10d.presenca.webservice.ListaMembroPorIdWS;
import br.com.x10d.presenca.webservice.ListaMembroTodosNaTelaWS;
import br.com.x10d.presenca.webservice.ListaMembroTodosWS;

public class GeraCodigoDeBarrasActivity extends Activity{

	private LinearLayout llDeAteh;
	private LinearLayout llTela;
	private LinearLayout llCodigo;
	private Context context;
	private EditText etCodigo;
	private RadioButton rbIndividual;
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
		
		rbIndividual = new RadioButton(context);
		rbIndividual.setId(3333);
		rbIndividual.setText("Individual");
		rbIndividual.setOnCheckedChangeListener( new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					llDeAteh.setVisibility(View.GONE);
					llCodigo.setVisibility(View.VISIBLE);
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
		radioGroup.addView(rbIndividual);
		
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
		
		scrollView.addView(llTela);
		
		setContentView(scrollView);
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

		if(rbIndividual.isChecked()) {
			
			int id = devolveInteiroValido(etCodigo);
			
			new ListaMembroPorIdWS(context).buscarListaDeMembros(id);
		}
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
