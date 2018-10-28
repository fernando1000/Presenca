package br.com.x10d.presenca.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.x10d.presenca.util.TelaBuilder;
import br.com.x10d.presenca.webservice.RelatorioFrequenciaEventoWS;
import br.com.x10d.presenca.webservice.RelatorioPercentualPresencaWS;

public class RelatorioActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final Context context = RelatorioActivity.this;
		
		TelaBuilder telaBuilder = new TelaBuilder(context);
		
		LinearLayout llTela = telaBuilder.criaLinearLayoutTELA();
		llTela.setOrientation(LinearLayout.VERTICAL);
	
		TextView tv_frequenciaPorDia = telaBuilder.criaTextViewTITULO("Relatório Frequencia Evento");
		tv_frequenciaPorDia.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				new RelatorioFrequenciaEventoWS(context).buscaRelatorio();
			}
		});
		
		TextView tv_aproveitamentoPorCongregacao = telaBuilder.criaTextViewTITULO("Relatório Percentual Presença");
		tv_aproveitamentoPorCongregacao.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				new RelatorioPercentualPresencaWS(context).buscaRelatorio();
			}
		});
				
		llTela.addView(tv_frequenciaPorDia);
		llTela.addView(tv_aproveitamentoPorCongregacao);
		
		setContentView(llTela);
	}
}
