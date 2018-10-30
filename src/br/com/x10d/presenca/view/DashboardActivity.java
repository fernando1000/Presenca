package br.com.x10d.presenca.view;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import br.com.x10d.presenca.R;
import br.com.x10d.presenca.util.AcaoSairDoAplicativo;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.util.TelaBuilder;
import android.widget.LinearLayout.LayoutParams;
import br.com.x10d.presenca.view.CadastroMembroActivity;
import br.com.x10d.presenca.webservice.RelatorioFrequenciaEventoWS;
import br.com.x10d.presenca.webservice.RelatorioPercentualPresencaWS;

public class DashboardActivity extends Activity {

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
		
		LinearLayout llIncricao = telaBuilder.criaLinearLayoutImageViewTextView(getResources().getDrawable(R.drawable.cadastro), "Realizar Inscrição");
					 llIncricao.setOnClickListener(new View.OnClickListener() {
						 	@Override
						 	public void onClick(View v) {

						 		startActivity(new Intent(context, CadastroMembroActivity.class));	
							}
					 	});

					 llColunaEsquerda.addView(llIncricao);
						     
		LinearLayout llGeraCodigoDeBarras = telaBuilder.criaLinearLayoutImageViewTextView(getResources().getDrawable(R.drawable.impresora), "Gerar Código de Barras");
					 llGeraCodigoDeBarras.setOnClickListener(new View.OnClickListener() {
						 	@Override
						 	public void onClick(View v) {
						 		
								startActivity(new Intent(context, GeraCodigoDeBarrasActivity.class));	
							}
					 	});
					     
		llColunaEsquerda.addView(llGeraCodigoDeBarras);

		llTela.addView(llColunaEsquerda);
	
		LinearLayout llColunaDireita = telaBuilder.criaLinearLayoutColuna(lp_colunaDireita);
		
		LinearLayout llRegistarPresenca = telaBuilder.criaLinearLayoutImageViewTextView(getResources().getDrawable(R.drawable.launcher_icon), "Registrar Presença");
					 llRegistarPresenca.setOnClickListener(new View.OnClickListener() {
						 	@Override
						 	public void onClick(View v) {
						 	
						 		startActivity(new Intent(context, ChamadaActivity.class));	
							}
					 	});
						
					 llColunaDireita.addView(llRegistarPresenca);
					 
		LinearLayout llRelatorio = telaBuilder.criaLinearLayoutImageViewTextView(getResources().getDrawable(R.drawable.chart), "Relatórios");
					 llRelatorio.setOnClickListener(new View.OnClickListener() {
						 	@Override
						 	public void onClick(View v) {
								
								criaListaComRelatorios();
							}
					 	});
							
					 llColunaDireita.addView(llRelatorio);

		llTela.addView(llColunaDireita);
		
		scrollView.addView(llTela);

		return scrollView;
	}

	private void criaListaComRelatorios() {
		
		ArrayList<String> lista = new ArrayList<String>();
						  lista.add("Frequencia Evento");
						  lista.add("Percentual Presença");
							
		escolheApenasUmItemDaLista("Relatórios", lista);
	}
	
	private void escolheApenasUmItemDaLista(String titulo, final ArrayList<String> lista) {

		ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.item_menu_geral, lista);

		AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
		builder1.setTitle(titulo);
		builder1.setSingleChoiceItems(arrayAdapter, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int posicao) {

				if (posicao == 0) {
					new RelatorioFrequenciaEventoWS(context).buscaRelatorio();
				}
				if (posicao == 1) {
					new RelatorioPercentualPresencaWS(context).buscaRelatorio();
				}				
				dialogInterface.dismiss();
			}
		});
		builder1.show();
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
