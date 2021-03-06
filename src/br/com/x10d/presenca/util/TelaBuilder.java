package br.com.x10d.presenca.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.x10d.presenca.R;

public class TelaBuilder {

	private Context context;
	
	public TelaBuilder(Context _context){
		this.context = _context;
	}
		
	public TextView criaTextViewTITULO(String texto){
		
		TextView textView = new TextView(context);
		textView.setTextSize(20);
		textView.setTextColor(context.getResources().getColor(R.color.azul_3_logo)); 
		textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
		textView.setText(texto);	

		return textView;
	}

	public Button criaBotao(String texto, LayoutParams lllp){

		Button button = new Button(context);
		button.setBackground(context.getResources().getDrawable(R.drawable.style_btn_x10d));
		//button.setBackgroundColor(context.getResources().getColor(R.color.abobora));
		SpannableString spanString = new SpannableString(texto);
		  			   spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
		button.setText(spanString);
		button.setTextColor(Color.BLACK);
		button.setTextSize(24);
		button.setLayoutParams(lllp);
		
		return button;
	}

	public EditText criaEditText(String texto){
	
	    EditText editText = new EditText(context);
	    editText.setTextSize(20);
	    editText.setBackground(context.getResources().getDrawable(R.drawable.style_edit_x10d));    		  
	    editText.setText(texto);
	    
	    return editText;
	}

	public LinearLayout criaLinearLayoutTELA(){
	
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		//linearLayout.setBackgroundColor(Color.WHITE);
		
		linearLayout.setBackgroundColor(context.getResources().getColor(R.color.background));
		
		linearLayout.setPadding(10, 0, 10, 0);
	
		return linearLayout;
	}

	public LinearLayout criaLinearLayoutColuna(LayoutParams layoutParams){
		
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setPadding(10, 0, 10, 0);
		linearLayout.setLayoutParams(layoutParams);

		return linearLayout;
	}

	public LinearLayout criaLinearLayoutImageViewTextView(Drawable drawable, String titulo){
		
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		
		ImageView imageView = criaImageView(drawable);
		
		LayoutParams lp_MATCH_350 = new LayoutParams(LayoutParams.MATCH_PARENT, 350);			
					 lp_MATCH_350.setMargins(0, 120, 0, 0);		
		imageView.setLayoutParams(lp_MATCH_350);
			
		linearLayout.addView(imageView);
		
		TextView textView = criaTextViewTITULO(titulo);
				 textView.setGravity(Gravity.CENTER);
				 textView.setTextSize(25);

		linearLayout.addView(textView);
		
		return linearLayout;
	}

	private ImageView criaImageView(Drawable drawable) {
		
		ImageView imageView = new ImageView(context);
		imageView.setImageDrawable(drawable);
		
		
		return imageView;
	}

	public LinearLayout criaLinearLayoutLinha_TV_ET(String titulo, EditText et_conteudo){
		
		LayoutParams lp_MATCH_WRAP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);			
		//lp_MATCH_WRAP.setMargins(0, 20, 0, 20);		
		et_conteudo.setLayoutParams(lp_MATCH_WRAP);

		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		linearLayout.addView(criaTextViewTITULO(titulo));
		linearLayout.addView(et_conteudo);
		
		return linearLayout;
	}
	public LinearLayout criaLinearLayoutLinha_TV_SPINNER(String titulo, Spinner spinner){
		
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		linearLayout.addView(criaTextViewTITULO(titulo));
		linearLayout.addView(spinner);
		
		return linearLayout;
	}

	private LinearLayout criaLinearLayoutcomBordaArredondada(){
		
		LinearLayout ll_holder = new LinearLayout(context);
		ll_holder.setOrientation(LinearLayout.VERTICAL);
		ll_holder.setBackground(criaGradientDrawable(Color.WHITE));
		ll_holder.setPadding(10, 10, 10, 10);
		
		LayoutParams lp_MATCH_WRAP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);			
							lp_MATCH_WRAP.setMargins(0, 20, 0, 20);		
		ll_holder.setLayoutParams(lp_MATCH_WRAP);
		
		return ll_holder;
	}
	
	private GradientDrawable criaGradientDrawable(int cor){
		
		GradientDrawable drawable = new GradientDrawable();
		drawable.setShape(GradientDrawable.RECTANGLE);
		drawable.setStroke(3, Color.BLACK);
		drawable.setCornerRadius(8);
		drawable.setColor(cor);
		
		return drawable;
	}

	public ScrollView criaScrollView(){
		
		ScrollView scrollView = new ScrollView(context);
	
		return scrollView;
	}
	
	public View criaViewEspacoVertical(int altura, int cor){
		
		View view = new View(context);
				
		LayoutParams lp_MATCH_altura = new LayoutParams(LayoutParams.MATCH_PARENT, altura);			
		
		view.setLayoutParams(lp_MATCH_altura);
		view.setBackgroundColor(cor);
		
		return view;
	}

	
}
