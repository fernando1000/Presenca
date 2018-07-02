package br.com.x10d.presenca.view;

import java.util.Arrays;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import br.com.x10d.presenca.dao.Dao;
import br.com.x10d.presenca.model.Membro;
import br.com.x10d.presenca.util.MeuAlerta;

public class ImprimeCodigoDeBarras extends Activity{

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
		
		
		context = ImprimeCodigoDeBarras.this;
		
		ScrollView scrollView = new ScrollView(context); 
		
		llTela = new LinearLayout(context);
		llTela.setOrientation(LinearLayout.VERTICAL);
		
		
		TextView tvTitulo = new TextView(context);
		tvTitulo.setText("Impressão de código de barras");
		
		rbTodos = new RadioButton(context);
		rbTodos.setText("Todos");
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
		llDeAteh.setVisibility(View.GONE);
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, LayoutParams.WRAP_CONTENT); 
		
		TextView tvDe = new TextView(context);
		tvDe.setText("Código DE");
		
		etDe = new EditText(context);
		etDe.setLayoutParams(params);
		
		TextView tvAteh = new TextView(context);
		tvAteh.setText("Código Até");
		
		etAteh = new EditText(context);
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
		
		
		TextView tvCodigo = new TextView(context);
		tvCodigo.setText("Código");
		
		etCodigo = new EditText(context);
		etCodigo.setLayoutParams(params);
	
		llCodigo.addView(tvCodigo);
		llCodigo.addView(etCodigo);
		
		
		Button gerarPDF = new Button(context);
		gerarPDF.setText("Gerar PDF");
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

		try {	
			
			Dao dao = new Dao(context);
			
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.setMargins(10, 0, 10, 0);
			
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
				
				TextView tvCPF = new TextView(context);
				tvCPF.setText(membro.getCpf());
				tvCPF.setLayoutParams(lp);
				
				llLinha.addView(tvId);
				llLinha.addView(tvNomeMembro);
				llLinha.addView(tvCongregacao);
				llLinha.addView(tvCPF);
				
				llTela.addView(llLinha);
			}
			
		} 
		catch (Exception ex) {
			
			ex.printStackTrace();
			
			new MeuAlerta("Erro", "Ocorreu um erro não achou -> "+ex, context).meuAlertaOk();
		}
		
		scrollView.addView(llTela);
		
		setContentView(scrollView);
	}
	
	private void acaoGerarPDF(){
		
		String querySelect = "";

		if(rbTodos.isChecked()) {

			querySelect = "select * from "+Membro.class.getSimpleName().toLowerCase();
		}

		if(rbLista.isChecked()) {
			
			int de = Integer.parseInt(etDe.getText().toString());
			int ateh = Integer.parseInt(etAteh.getText().toString());
				
			querySelect = "select * from "+Membro.class.getSimpleName().toLowerCase()
					     +" where "+Membro.COLUMN_INTEGER_ID+" between "+de+" and "+ateh;
		}

		if(rbIndividual.isChecked()) {
			
			int individual = Integer.parseInt(etCodigo.getText().toString());

			querySelect = "select * from "+Membro.class.getSimpleName().toLowerCase()
					     +" where "+Membro.COLUMN_INTEGER_ID+" = "+individual;			
		}

		Dao dao = new Dao(context);
		
		Log.i("tag","querySelect: "+querySelect);
			
		for(Membro membro : dao.devolveListaBaseadoEmSQL_final(Membro.class, querySelect)){
			
			Log.i("tag","ids encontrados: "+membro.getId());
			
			//gerarCodigoDeBarras(context, llTela, ""+membro.getId());			
		}

	}
	
	private void gerarCodigoDeBarras(Context context, LinearLayout llTela, String codigo) {
		
		ImageView imageView = new ImageView(context);
		try {
			Bitmap bitmap = criaBitmapDoCodigoDeBarras(codigo, 200, 100);
			imageView.setImageBitmap(bitmap);
		} 
		catch (WriterException e) {
			
			e.printStackTrace();
		}
	
		llTela.addView(imageView);

		new MeuAlerta("Atenção", "Gerando PDF...", context).meuAlertaOk();
	}
	
	private Bitmap criaBitmapDoCodigoDeBarras(String texto, int width, int height) throws WriterException {
		   
		MultiFormatWriter writer = new MultiFormatWriter();
	    String finalData = Uri.encode(texto);

	    // Use 1 as the height of the matrix as this is a 1D Barcode.
	    BitMatrix bm = writer.encode(finalData, BarcodeFormat.CODE_128, width, 1);
	    
	    int bmWidth = bm.getWidth();

	    Bitmap imageBitmap = Bitmap.createBitmap(bmWidth, height, Config.ARGB_8888);

	    for (int i = 0; i < bmWidth; i++) {
	        // Paint columns of width 1
	        int[] column = new int[height];
	        Arrays.fill(column, bm.get(i, 0) ? Color.BLACK : Color.WHITE);
	        imageBitmap.setPixels(column, 0, 1, i, 0, 1, height);
	    }

	    return imageBitmap;
	}

	
}
