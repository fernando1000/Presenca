package br.com.x10d.presenca.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import br.com.x10d.presenca.R;
import br.com.x10d.presenca.util.Animacao;

public class MainActivity extends Activity {

	private static int SPLASH_TIME_OUT = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final Context context = MainActivity.this;
		
		LinearLayout llTela = new LinearLayout(this);
		
		ImageView imageView = new ImageView(context);		 
				  imageView.setImageDrawable(getResources().getDrawable(R.drawable.x10d_splash));
					
		fazAnimacao(context, llTela, imageView);
		
		esperaUmPoucoEchamaLogin(context);		

		llTela.addView(imageView);
		
		setContentView(llTela);
	}
	
	private void fazAnimacao(Context context, LinearLayout llTela, ImageView imageView) {

		Animacao animacao = new Animacao();			
		 animacao.iniciaAnimacao(context, imageView, R.anim.translate);
		 animacao.iniciaAnimacao(context, llTela, R.anim.alpha);
	}
	
	private void esperaUmPoucoEchamaLogin(final Context context) {

		new android.os.Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				
				Intent intent = new Intent(context, LoginActivity.class);
				
				startActivity(intent);
				
				finish();

				//overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			}
		}, SPLASH_TIME_OUT);

	}
	
	
}
