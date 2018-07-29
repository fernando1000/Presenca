package br.com.x10d.presenca.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.CustomLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewDataInterface;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.LegendAlign;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import br.com.x10d.presenca.dao.Dao;
import br.com.x10d.presenca.model.Chamada;

public class RelatorioActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Context context = RelatorioActivity.this;
		
		LinearLayout llTela = new LinearLayout(context);
		
		
		
    	//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","BR")); 
    	//String dataAtualFormatada = dateFormat.format(new Date());

    	//Dao dao = new Dao(context);
    	
		//List<Chamada> lista = dao.listaTodaTabela(Chamada.class, Chamada.COLUMN_TEXT_DATA_DMA, dataAtualFormatada);

		
		// SENO
		GraphViewData[] graphViewData = new GraphViewData[6];					
		graphViewData[0] = new GraphViewData(0, 0);			
		graphViewData[1] = new GraphViewData(1, 1);			
		graphViewData[2] = new GraphViewData(2, 2);			
		graphViewData[3] = new GraphViewData(3, 3);			
		graphViewData[4] = new GraphViewData(4, 4);			
		graphViewData[5] = new GraphViewData(5, 5);			

		GraphViewSeries seriesSeno = new GraphViewSeries("Qtde", new GraphViewSeriesStyle(Color.BLUE, 9), graphViewData);
		
		// COSSENO
		//graphViewData = new GraphViewData[tam];
		//for(i = 0; i < tam; i++){
			//v += 0.3;
			//graphViewData[i] = new GraphViewData(i, Math.cos(v));
		//}
		//GraphViewSeries seriesCosseno = new GraphViewSeries("seriesCosseno", new GraphViewSeriesStyle(Color.GREEN, 3), graphViewData);
		
		
		BarGraphView graphView = new BarGraphView(this, "Relatório De Presentes no dia");
		graphView.addSeries(seriesSeno);
		//graphView.addSeries(seriesCosseno);
		
		graphView.setShowLegend(true);
		graphView.setLegendAlign(LegendAlign.BOTTOM);
		
		graphView.getGraphViewStyle().setGridColor(Color.GRAY);
		graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
		graphView.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
		graphView.getGraphViewStyle().setTextSize(25);
		
			
		/*
		graphView.setVerticalLabels(new String[]{"y1", "y2", "y3", "y4"});
		graphView.setHorizontalLabels(new String[]{"x1", "x2", "x3", "x4"});
		
		graphView.setCustomLabelFormatter(new CustomLabelFormatter(){
			@Override
			public String formatLabel(double value, boolean isValueX) {
				if(isValueX){
					if(value > 2){
						return("x1");
					}
					return("x2");
				}
				else{
					if(value > 2){
						return("y1");
					}
					return("y2");
				}
		}});
		*/
		//graphView.setViewPort(0, 10);
		graphView.setScrollable(true);
		graphView.setScalable(true);
		
		//graphView.setDrawBackground(true);
		//graphView.setBackgroundColor(Color.BLUE);
		
		llTela.addView(graphView);
		
		setContentView(llTela);
	}
}
