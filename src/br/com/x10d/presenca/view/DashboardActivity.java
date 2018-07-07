package br.com.x10d.presenca.view;

import com.google.zxing.client.android.CaptureActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import br.com.x10d.presenca.R;
import br.com.x10d.presenca.util.AcaoSairDoAplicativo;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.util.TelaBuilder;
import android.widget.LinearLayout.LayoutParams;

public class DashboardActivity extends Activity {

	private static final int CODIGO_DA_REQUISICAO = 777;
	private static final int MENU_ITEM_ITEM1 = 1;
	private static final int MENU_ITEM_ITEM2 = 2;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		context = DashboardActivity.this;
		
		setContentView(devolveTelaInicial());
	}
	
	private ScrollView devolveTelaInicial() {
		
		
		TelaBuilder telaBuilder = new TelaBuilder(context);
		
		ScrollView scrollView = telaBuilder.criaScrollView();
		scrollView.setBackgroundColor(Color.WHITE);
			
		LinearLayout llTela = telaBuilder.criaLinearLayoutTELA();
		llTela.setOrientation(LinearLayout.HORIZONTAL);
		llTela.setBackgroundColor(Color.WHITE);

		LayoutParams lp_colunaEsquerda = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0.50f);
		LayoutParams lp_colunaDireita = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0.50f);
		
		LinearLayout llColunaEsquerda = telaBuilder.criaLinearLayoutColuna(lp_colunaEsquerda);
		
		LinearLayout llRealizarIncricao = telaBuilder.criaLinearLayoutImageViewTextView(getResources().getDrawable(R.drawable.cadastro), "Realizar Incrição");
					 llRealizarIncricao.setOnClickListener(new View.OnClickListener() {
						 	@Override
						 	public void onClick(View v) {
						 		
								acaoRealizarInscricao();
							}
					 	});

					 llColunaEsquerda.addView(llRealizarIncricao);
						     
		LinearLayout llImprimir = telaBuilder.criaLinearLayoutImageViewTextView(getResources().getDrawable(R.drawable.impresora), "Imprimir código de barras");
					 llImprimir.setOnClickListener(new View.OnClickListener() {
						 	@Override
						 	public void onClick(View v) {
						 		
								acaoImprimir();
							}
					 	});
					     
		llColunaEsquerda.addView(llImprimir);

		llTela.addView(llColunaEsquerda);
	
		LinearLayout llColunaDireita = telaBuilder.criaLinearLayoutColuna(lp_colunaDireita);
		
		LinearLayout llRegistarPresenca = telaBuilder.criaLinearLayoutImageViewTextView(getResources().getDrawable(R.drawable.launcher_icon), "Registar Presença");
					 llRegistarPresenca.setOnClickListener(new View.OnClickListener() {
						 	@Override
						 	public void onClick(View v) {
						 		
								acaoRegistarPresenca();
							}
					 	});
						
					 llColunaDireita.addView(llRegistarPresenca);
					 
		//LinearLayout llTeste = telaBuilder.criaLinearLayoutImageViewTextView(getResources().getDrawable(R.drawable.ic_launcher), "teste");
					 //llTeste.setOnClickListener(new View.OnClickListener() {
						 	//@Override
						 	//public void onClick(View v) {
								
								//startActivity(new Intent(context, FichaInscricao.class));	

							//}
					 //	});
							
		//llColunaDireita.addView(llTeste);

		llTela.addView(llColunaDireita);
		
		scrollView.addView(llTela);

		return scrollView;
	}
	
	private void acaoImprimir() {
		
		startActivity(new Intent(context, ImprimeCodigoDeBarras.class));	
	}

	private void acaoRealizarInscricao() {
		
		startActivity(new Intent(context, FichaInscricao.class));	
	}

	private void acaoRegistarPresenca() {
		
		Intent intent = new Intent(context, CaptureActivity.class);
			
		startActivityForResult(intent, CODIGO_DA_REQUISICAO);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		
		if(CODIGO_DA_REQUISICAO == requestCode && RESULT_OK == resultCode){
		
			new MeuAlerta("Aviso", "RESULTADO: "+data.getStringExtra("SCAN_RESULT")+" ("+data.getStringExtra("SCAN_FORMAT")+")", context).meuAlertaOk();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		menu.add(Menu.NONE, MENU_ITEM_ITEM1, Menu.NONE, "Perfil");
		menu.add(Menu.NONE, MENU_ITEM_ITEM2, Menu.NONE, "Sair");
		
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
	    switch (item.getItemId()) {
	    
	    	case MENU_ITEM_ITEM1: mostraPerfil();
	    		return true;

	    	case MENU_ITEM_ITEM2: sairDoAplicativo();
    			return true;

	    	default:
	    		return false;
	    }
	}
		
	@Override
	public void onBackPressed() {

		sairDoAplicativo();		
	}
	
	private void sairDoAplicativo() {
		
		AcaoSairDoAplicativo acaoSairAplicativo = new AcaoSairDoAplicativo(DashboardActivity.this);
		
		new MeuAlerta("Sair", "Deseja sair do aplicativo?", context).meuAlertaSimNao(acaoSairAplicativo);
	}
	
	private void mostraPerfil() {

		new MeuAlerta("Sair", "criar tela para mostrar Perfil", context).meuAlertaOk();

	}
	

}
