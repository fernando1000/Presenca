package br.com.x10d.presenca.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.zxing.client.android.CaptureActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import br.com.x10d.presenca.dao.Dao;
import br.com.x10d.presenca.model.Chamada;
import br.com.x10d.presenca.model.Membro;
import br.com.x10d.presenca.util.AcaoAlertDialog;
import br.com.x10d.presenca.util.AcaoVaiParaQualquerActivity;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.util.TelaBuilder;

public class ChamadaActivity extends Activity{

	private LinearLayout llTela;
	private Context context;
	private static final int CODIGO_DA_REQUISICAO = 777;
	private Dao dao;
	private TelaBuilder telaBuilder;
	private String dataAtualFormatada;
	private LinearLayout llListaDosPresentes;
	private EditText etNomePalestra;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); 

		context = ChamadaActivity.this;
		
		dao = new Dao(context);
		
		telaBuilder = new TelaBuilder(context);

		llTela = telaBuilder.criaLinearLayoutTELA();
		llTela.setOrientation(LinearLayout.VERTICAL);

		int altura = 50;
		int cor = Color.WHITE;
		
		llTela.addView(telaBuilder.criaViewEspacoVertical(altura, cor));

		LayoutParams lllp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			
		Button botaoEscanearCodigo = telaBuilder.criaBotao("Escanear Código", lllp);
		botaoEscanearCodigo.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					chamaLeitorDeCodigoDeBarras();
				}
			});
		llTela.addView(botaoEscanearCodigo);
		
		llTela.addView(telaBuilder.criaViewEspacoVertical(altura, cor));

		final EditText etCodigo = telaBuilder.criaEditText("");
		etCodigo.setInputType(InputType.TYPE_CLASS_NUMBER);
		etCodigo.setFilters( new InputFilter[] { new InputFilter.LengthFilter(12) } );
		
		LinearLayout llLinha = telaBuilder.criaLinearLayoutLinha_TV_ET("Informar Código:", etCodigo);

		llTela.addView(llLinha);

		etNomePalestra = telaBuilder.criaEditText("");
		etNomePalestra.setFilters( new InputFilter[] { new InputFilter.LengthFilter(40) } );
		
		LinearLayout llLinha2 = telaBuilder.criaLinearLayoutLinha_TV_ET("Nome da palestra:", etNomePalestra);

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

    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","BR")); 
    	dataAtualFormatada = dateFormat.format(new Date());

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

		llListaDosPresentes = new LinearLayout(context);
		llListaDosPresentes.setBackgroundColor(Color.LTGRAY);
		llListaDosPresentes.setOrientation(LinearLayout.VERTICAL);

		scrollView.addView(llListaDosPresentes);
		
		llChamadaHolder.addView(scrollView);
		
		llTela.addView(llChamadaHolder);
		
		

		for(Chamada chamada : dao.listaTodaTabela(Chamada.class, Chamada.COLUMN_TEXT_DATA_DMA, dataAtualFormatada)){
			
			adicionaMembroNaListaDeChamada(chamada.getDataDMAHMS(), chamada.getMembroId());
		}

		setContentView(llTela);
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
		
		Membro membro = (Membro)dao.devolveObjeto(Membro.class, "keyy", codigoBarras);
		
		if(membro == null) {
			
			AcaoAlertDialog acaoVaiParaQualquerActivity = new AcaoVaiParaQualquerActivity(ChamadaActivity.this, CadastroMembroActivity.class);
			
			new MeuAlerta("Aviso", "Membro não encontrado, deseja realizar a inscrição deste membro?", context).meuAlertaSimNao(acaoVaiParaQualquerActivity);
			
		}else {
								
			Chamada chamad = (Chamada)dao.devolveObjeto(Chamada.class, 
														Chamada.COLUMN_TEXT_DATA_DMA, dataAtualFormatada, 
														Chamada.COLUMN_TEXT_MEMBRO_ID, codigoBarras);
				
			if(chamad == null) {

				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", new Locale("pt","BR")); 
		    	String dataAtualComHMS = dateFormat.format(new Date());

		    	Chamada chamada = new Chamada();
		    				 String nomePalestra = etNomePalestra.getText().toString();
		    	chamada.setPalestra(nomePalestra);		    	
				chamada.setDataDMA(dataAtualFormatada);
				chamada.setDataDMAHMS(dataAtualComHMS);
				chamada.setMembroId(codigoBarras);
				
				dao.insereOUatualiza(chamada, 
									 Chamada.COLUMN_TEXT_DATA_DMA, dataAtualFormatada, 
									 Chamada.COLUMN_TEXT_MEMBRO_ID, codigoBarras);

				adicionaMembroNaListaDeChamada(chamada.getDataDMAHMS(), chamada.getMembroId());
			}else {
				new MeuAlerta("Atenção", "Membro já adicionado na lista de chamada!", context).meuAlertaOk();
			}
			
		}
		
	}
	
	private void adicionaMembroNaListaDeChamada(String data, String membroId) {
		
		Membro membro = (Membro)dao.devolveObjeto(Membro.class, "keyy", membroId);
		
		String hora = data.substring(10, data.length());
		
		TextView textView = telaBuilder.criaTextViewTITULO(hora+" | "+membro.getNome());
		textView.setTextSize(15);
		textView.setTextColor(Color.BLACK);
		
		llListaDosPresentes.addView(textView);
	}

}
