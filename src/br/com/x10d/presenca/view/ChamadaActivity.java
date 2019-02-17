package br.com.x10d.presenca.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import com.google.zxing.client.android.CaptureActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.x10d.presenca.R;
import br.com.x10d.presenca.model.Chamada;
import br.com.x10d.presenca.util.TelaBuilder;
import br.com.x10d.presenca.webservice.ListaChamadaTodosNaTelaWS;
import br.com.x10d.presenca.webservice.ListaMembroPorCodigoBarrasWS;

public class ChamadaActivity extends Activity{

	private LinearLayout llTela;
	private Context context;
	private static final int CODIGO_DA_REQUISICAO = 777;
	public static final int REQUISICAO_PERMISSAO_TIRAR_FOTO = 111;

	private TelaBuilder telaBuilder;
	private String dataAtualFormatada;
	private LinearLayout llListaDosPresentes;
	private Spinner spinnerNomePalestra;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); 

		context = ChamadaActivity.this;
			
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","BR")); 
    	dataAtualFormatada = dateFormat.format(new Date());

		telaBuilder = new TelaBuilder(context);

		llTela = telaBuilder.criaLinearLayoutTELA();
		llTela.setOrientation(LinearLayout.VERTICAL);

		int altura = 50;
		int cor = context.getResources().getColor(R.color.background);
		
		llTela.addView(telaBuilder.criaViewEspacoVertical(altura, cor));

		LayoutParams lllp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			
		Button botaoEscanearCodigo = telaBuilder.criaBotao("Escanear Código", lllp);
		botaoEscanearCodigo.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					
					if (Build.VERSION.SDK_INT >= 23) {
						
						if (permitiuTirarFoto()) {
							 
							chamaLeitorDeCodigoDeBarras();
						} 	
				    } 
					else {
				    	chamaLeitorDeCodigoDeBarras();
					}
					
				}
			});
		llTela.addView(botaoEscanearCodigo);
		
		llTela.addView(telaBuilder.criaViewEspacoVertical(altura, cor));

		final EditText etCodigo = telaBuilder.criaEditText("");
		etCodigo.setInputType(InputType.TYPE_CLASS_NUMBER);
		etCodigo.setFilters( new InputFilter[] { new InputFilter.LengthFilter(9)});
		
		LinearLayout llLinha = telaBuilder.criaLinearLayoutLinha_TV_ET("Informar Código: ", etCodigo);
		
		//llLinha.setBackgroundColor(context.getResources().getColor(R.color.background));

		
		llTela.addView(llLinha);

		llListaDosPresentes = new LinearLayout(context);
		llListaDosPresentes.setBackgroundColor(Color.LTGRAY);
		llListaDosPresentes.setOrientation(LinearLayout.VERTICAL);

		ArrayList<String> listaNomePalestra = new ArrayList<String>();
						  listaNomePalestra.add("1º Período");
						  listaNomePalestra.add("2º Período");
  
		spinnerNomePalestra = new Spinner(context);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		spinnerNomePalestra.setLayoutParams(params);
		spinnerNomePalestra.setAdapter(new ArrayAdapter(context, R.layout.item_menu_geral, listaNomePalestra));
		spinnerNomePalestra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int posicaoSelecionada, long id) {
				
				Chamada chamada = new Chamada();
						chamada.setNomePalestra(spinnerNomePalestra.getSelectedItem().toString());
				new ListaChamadaTodosNaTelaWS(context, llListaDosPresentes).buscarListaComTodasChamadas(chamada);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {	
			}
		});
		
		
		LinearLayout llLinha2 = telaBuilder.criaLinearLayoutLinha_TV_SPINNER("Informe o Período:", spinnerNomePalestra);
					 llLinha2.setVisibility(View.INVISIBLE);

		if(dataAtualFormatada.contains("02/11/2018")) {
			Log.i("tag","contem");
			llLinha2.setVisibility(View.VISIBLE);
		}
		
		llTela.addView(llLinha2);

		llTela.addView(telaBuilder.criaViewEspacoVertical(altura, cor));

		LayoutParams lllpMW = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		Button botaoRegistrarCodigoInformado = telaBuilder.criaBotao("Registrar Código Informado", lllpMW);
		botaoRegistrarCodigoInformado.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					String membroId = etCodigo.getText().toString();
					
					procuraCodigoInformado(membroId);
				}
			});
		llTela.addView(botaoRegistrarCodigoInformado);
		
		llTela.addView(telaBuilder.criaViewEspacoVertical(altura, cor));


    	TextView tvTituloListaDeChamada = telaBuilder.criaTextViewTITULO("Chamada em "+dataAtualFormatada);
		    	 tvTituloListaDeChamada.setTextSize(22);
		    	 tvTituloListaDeChamada.setGravity(Gravity.CENTER);
		    	 tvTituloListaDeChamada.setTextColor(Color.BLACK);
		    	
		llTela.addView(tvTituloListaDeChamada);
		
		LayoutParams lllpMM = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		
		LinearLayout llChamadaHolder = new LinearLayout(context);
		llChamadaHolder.setLayoutParams(lllpMM);
	
		ScrollView scrollView = telaBuilder.criaScrollView();
		scrollView.setLayoutParams(lllpMM);


		scrollView.addView(llListaDosPresentes);
		
		llChamadaHolder.addView(scrollView);
		
		llTela.addView(llChamadaHolder);
		
		setContentView(llTela);
	}
		
	private boolean permitiuTirarFoto(){
		
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            
   	     	requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUISICAO_PERMISSAO_TIRAR_FOTO);		      
 		
            return false;
        }

        return true;
    }

	private void chamaLeitorDeCodigoDeBarras() {
		
		Intent intent = new Intent(context, CaptureActivity.class);
			
		startActivityForResult(intent, CODIGO_DA_REQUISICAO);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		
		if(CODIGO_DA_REQUISICAO == requestCode && RESULT_OK == resultCode){
		
			//new MeuAlerta("Aviso", data.getStringExtra("SCAN_RESULT") +" ("+data.getStringExtra("SCAN_FORMAT")+")", context).meuAlertaOk();
			
			String membroId = data.getStringExtra("SCAN_RESULT");
			
			procuraCodigoInformado(membroId);
		}
	}
	
	private void procuraCodigoInformado(String membroId) {
		
		if(membroId.equals("")) {
			membroId = "0";
		}
		String codigoBarras = String.format("%08d", Long.parseLong(membroId));
		
		String nomePalestra = spinnerNomePalestra.getSelectedItem().toString();
		
		Chamada chamada = new Chamada();
		chamada.setCodigoBarras(codigoBarras);
		chamada.setNomePalestra(nomePalestra);
		
		new ListaMembroPorCodigoBarrasWS(context, llListaDosPresentes).buscarMembro(chamada);	
	}
	
}
