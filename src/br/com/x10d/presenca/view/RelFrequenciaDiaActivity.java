package br.com.x10d.presenca.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.x10d.presenca.dao.Dao;
import br.com.x10d.presenca.model.Chamada;
import br.com.x10d.presenca.model.Membro;
import br.com.x10d.presenca.util.TelaBuilder;

public class RelFrequenciaDiaActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		final Context context = RelFrequenciaDiaActivity.this;
		
		TelaBuilder telaBuilder = new TelaBuilder(context);
	
		LinearLayout llTela = telaBuilder.criaLinearLayoutTELA();
		
		
		Dao dao = new Dao(context);
				
		for (Chamada chamada : dao.listaTodaTabela(Chamada.class)) {
		
			Membro membro = (Membro)dao.devolveObjeto(Membro.class, "keyy", chamada.getId());
			
			TextView textView = telaBuilder.criaTextViewTITULO(membro.getNome()+" | "+membro.getCongregacao()+" | "+
															   chamada.getNomePalestra()+" | "+chamada.getData());
			textView.setTextSize(10);
			textView.setTextColor(Color.BLACK);
		
			llTela.addView(textView);
			
		}
		
		
		
		setContentView(llTela);
	}
}
