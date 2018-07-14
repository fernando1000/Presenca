package br.com.x10d.presenca.view;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.CustomLabelFormatter;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.LegendAlign;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RelatorioActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Context context = RelatorioActivity.this;
		
		LinearLayout llTela = new LinearLayout(context);
		
		
		int i, tam = 20;
		double v = 0;
		
		// SENO
		GraphViewData[] graphViewData = new GraphViewData[tam];
		for(i = 0; i < tam; i++){
			v += 0.3;
			graphViewData[i] = new GraphViewData(i, Math.sin(v));
		}
		GraphViewSeries seriesSeno = new GraphViewSeries("seriesSeno", new GraphViewSeriesStyle(Color.BLUE, 3), graphViewData);
		
		// COSSENO
		graphViewData = new GraphViewData[tam];
		for(i = 0; i < tam; i++){
			v += 0.3;
			graphViewData[i] = new GraphViewData(i, Math.cos(v));
		}
		GraphViewSeries seriesCosseno = new GraphViewSeries("seriesCosseno", new GraphViewSeriesStyle(Color.GREEN, 3), graphViewData);
		
		
		BarGraphView graphView = new BarGraphView(this, "Exemplo GraphView");
		graphView.addSeries(seriesSeno);
		graphView.addSeries(seriesCosseno);
		
		graphView.setShowLegend(true);
		graphView.setLegendAlign(LegendAlign.BOTTOM);
		
		graphView.getGraphViewStyle().setGridColor(Color.GRAY);
		graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.WHITE);
		graphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
		graphView.getGraphViewStyle().setTextSize(15);
		
		/*graph.setVerticalLabels(new String[]{"y1", "y2", "y3", "y4"});
		graph.setHorizontalLabels(new String[]{"x1", "x2", "x3", "x4"});*/
		
		/*graph.setCustomLabelFormatter(new CustomLabelFormatter(){
			@Override
			public String formatLabel(double value, boolean isValueX) {
				// TODO Auto-generated method stub
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
		}});*/
		
		graphView.setViewPort(10, 30);
		graphView.setScrollable(true);
		graphView.setScalable(true);
		
		/*graph.setDrawBackground(true);
		graph.setBackgroundColor(Color.BLUE);*/
		
		llTela.addView(graphView);
		
		setContentView(llTela);
	}
}
