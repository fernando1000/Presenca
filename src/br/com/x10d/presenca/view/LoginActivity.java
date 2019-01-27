package br.com.x10d.presenca.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import br.com.x10d.presenca.R;
import br.com.x10d.presenca.util.TelaBuilder;
import br.com.x10d.presenca.webservice.AutenticacaoWS;

public class LoginActivity extends Activity {

	private Context context;	
	private AutenticacaoWS autenticacaoWS;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); 

		context = LoginActivity.this;
		
		autenticacaoWS = new AutenticacaoWS(context);
				
		//ActionBar bar = getActionBar();
				  //bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.color.azul_consigaz))));
				  
		TelaBuilder meusWidgetsBuilder = new TelaBuilder(context);
				
		ScrollView scrollView = new ScrollView(context); 
		scrollView.setBackgroundColor(Color.WHITE);
		scrollView.setBackgroundColor(context.getResources().getColor(R.color.mostarda));
		
		LinearLayout llTela = meusWidgetsBuilder.criaLinearLayoutTELA();
		llTela.setBackgroundColor(context.getResources().getColor(R.color.mostarda));
		
		TextView textView_usuario = meusWidgetsBuilder.criaTextViewTITULO("Usuário:");

        final EditText editText_usuario = meusWidgetsBuilder.criaEditText("");
        			   editText_usuario.setFilters( new InputFilter[] { new InputFilter.LengthFilter(6) } );

		TextView textView_senha = meusWidgetsBuilder.criaTextViewTITULO("Senha:");

        final EditText editText_senha = meusWidgetsBuilder.criaEditText("");
        			   editText_senha.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        			   editText_senha.setFilters( new InputFilter[] { new InputFilter.LengthFilter(6) } );

        LayoutParams lp_MATCH_WRAP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);			
					 lp_MATCH_WRAP.setMargins(0, 20, 0, 0);		
					 
		Button button_entrar = meusWidgetsBuilder.criaBotao("Entrar", lp_MATCH_WRAP);	
		button_entrar.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
							
			acaoAposCliqueNoBotaoEntrar(editText_usuario, editText_senha);
			}
		});
		
		//LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(650, 320);
		//para.gravity = Gravity.CENTER;
	
	    ImageView imageView = new ImageView(context);				    
			      imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.tema_ano));
			  	     LayoutParams lp_WRAP_WRAP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);			
			  		 			  lp_WRAP_WRAP.setMargins(0, 0, 0, 0);		
	    imageView.setLayoutParams(lp_WRAP_WRAP);

		TextView tvLinkSiteX10D = new TextView(context);
		tvLinkSiteX10D.setText("                                   www.x10d.com.br");
		tvLinkSiteX10D.setGravity(Gravity.CENTER);
		tvLinkSiteX10D.setTextColor(Color.BLACK);		
        				  LayoutParams lpWRAP_WRAP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);			
					 				   lpWRAP_WRAP.setMargins(0, 80, 0, 0);		
		tvLinkSiteX10D.setLayoutParams(lpWRAP_WRAP);
		tvLinkSiteX10D.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(Intent.ACTION_VIEW);
				       i.setData(Uri.parse("http://www.x10d.com.br"));
				startActivity(i);
			}
		});

		llTela.addView(imageView);
		llTela.addView(textView_usuario);		
		llTela.addView(editText_usuario);
		llTela.addView(textView_senha);
		llTela.addView(editText_senha);	
		llTela.addView(button_entrar);
		llTela.addView(tvLinkSiteX10D);
		
		scrollView.addView(llTela);
		
		setContentView(scrollView);		
	}
		
	private void acaoAposCliqueNoBotaoEntrar(EditText editText_usuario, EditText editText_senha){
		
		String usuario = editText_usuario.getText().toString();

		usuario = usuario.replaceAll(" ","");  
		
		String senha = editText_senha.getText().toString();

		if (usuario.equals("")) {

			Toast.makeText(context, "Informe o usuário", Toast.LENGTH_SHORT).show();
		}
		else if (senha.equals("")) {

			Toast.makeText(context, "Informe a senha", Toast.LENGTH_SHORT).show();
		} 
		else {
			
			autenticacaoWS.buscaUsuarioWS(usuario, senha);
		}
	}
				
}
