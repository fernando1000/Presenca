package br.com.x10d.presenca.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.x10d.presenca.util.TelaBuilder;

public class RelatorioActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final Context context = RelatorioActivity.this;
		
		TelaBuilder telaBuilder = new TelaBuilder(context);
		
		LinearLayout llTela = telaBuilder.criaLinearLayoutTELA();
		llTela.setOrientation(LinearLayout.VERTICAL);

		TextView tv_frequencia = telaBuilder.criaTextViewTITULO("Relatório de frequencia");
		tv_frequencia.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		TextView tv_frequenciaPorDia = telaBuilder.criaTextViewTITULO("Relatório de frequencia por dia");
		tv_frequenciaPorDia.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(context, RelFrequenciaDiaActivity.class));	
			}
		});
		
		TextView tv_aproveitamentoPorCongregacao = telaBuilder.criaTextViewTITULO("Relatório de aproveitamento por congreg");
		tv_aproveitamentoPorCongregacao.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
				
		llTela.addView(tv_frequencia);
		llTela.addView(tv_frequenciaPorDia);
		llTela.addView(tv_aproveitamentoPorCongregacao);
		
		setContentView(llTela);
	}
}
