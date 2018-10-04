package br.com.x10d.presenca.view;

import java.io.File;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.view.View;
import android.webkit.MimeTypeMap;
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
import android.widget.Toast;
import br.com.x10d.presenca.dao.Dao;
import br.com.x10d.presenca.model.Membro;
import br.com.x10d.presenca.util.GeraPDF;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.util.TelaBuilder;
import br.com.x10d.presenca.webservice.ListaMembroWS;

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
		
		
		TextView tvTitulo = telaBuilder.criaTextViewTITULO("Impress�o de c�digo de barras");
		
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
		
		TextView tvDe = telaBuilder.criaTextViewTITULO("C�digo DE:");
		
		etDe = telaBuilder.criaEditText("");
		etDe.setInputType(InputType.TYPE_CLASS_NUMBER);
		etDe.setLayoutParams(params);
		
		TextView tvAteh = telaBuilder.criaTextViewTITULO("C�digo At�:");
		
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
		
		
		TextView tvCodigo = telaBuilder.criaTextViewTITULO("C�digo");
		
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
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.setMargins(10, 0, 10, 0);
			
		
		
		new ListaMembroWS(context).buscarListaDeMembros();
		/*
		try {	
			Dao dao = new Dao(context);
			for(Membro membro : dao.listaTodaTabela(Membro.class)) {
				
				LinearLayout llLinha = new LinearLayout(context);
				
				TextView tvId = new TextView(context);
				tvId.setText(""+membro.getId());
				tvId.setLayoutParams(lp);
			
				TextView tvNomeMembro = new TextView(context);
				tvNomeMembro.setText(membro.getNome());
				tvNomeMembro.setLayoutParams(lp);
				
				TextView tvCongregacao = new TextView(context);
				tvCongregacao.setText(membro.getCongregacao());
				tvCongregacao.setLayoutParams(lp);
				
				llLinha.addView(tvId);
				llLinha.addView(tvNomeMembro);
				llLinha.addView(tvCongregacao);
				
				llTela.addView(llLinha);
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			new MeuAlerta("Erro", "Ocorreu um erro n�o achou -> "+ex, context).meuAlertaOk();
		}
		*/
		
		
		scrollView.addView(llTela);
		
		setContentView(scrollView);
	}
	
	private void acaoGerarPDF(){
		
		String querySelect = "";

		if(rbTodos.isChecked()) {

			querySelect = "select * from membro";
		}

		if(rbLista.isChecked()) {
			
			int de = devolveInteiroValido(etDe);
			int ateh = devolveInteiroValido(etAteh);
				
			querySelect = "select * from membro where keyy between "+de+" and "+ateh;
		}

		if(rbIndividual.isChecked()) {
		
			int individual = devolveInteiroValido(etCodigo);

			querySelect = "select * from membro where keyy == "+individual;			
		}

		Dao dao = new Dao(context);
					
		List<Membro> listaComMembros = dao.devolveListaBaseadoEmSQL_final(Membro.class, querySelect);
			
		if(listaComMembros.size() > 0) {
			
			criaArquivoPDF(listaComMembros);
		}else {
			new MeuAlerta("Aviso", "N�o encontrou", context).meuAlertaOk();
		}
		
	}
	
	private void criaArquivoPDF(List<Membro> listaComMembros) {

	String srcPresenca = Environment.getExternalStorageDirectory()+"/Presenca/CodigoDeBarras";	
	
		try {
		
			GeraPDF geraPDF = new GeraPDF();
			geraPDF.criaPDF(srcPresenca+".pdf", listaComMembros);
			
			Toast.makeText(context, "PDF gerado com sucesso!", Toast.LENGTH_SHORT).show();
			
			chamaVisualizadorPDF(srcPresenca+".pdf");
		
		} 
		catch (Exception erro) {
		
			new MeuAlerta("Erro", "Erro n�o cria��o do PDF: "+erro, context).meuAlertaOk();

			erro.printStackTrace();
		}
	}

	private void chamaVisualizadorPDF(String caminhoComExtensao){
     	
	   	String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(".pdf");
	   		   	
	   				   Intent intent = new Intent(Intent.ACTION_VIEW);
	   		   				  intent.setDataAndType(Uri.fromFile(new File(caminhoComExtensao)), mimeType);		
	   		   				  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   	startActivity(intent);
	   	//finish(); 
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
