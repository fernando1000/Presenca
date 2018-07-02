package br.com.x10d.presenca.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.x10d.presenca.R;
import br.com.x10d.presenca.util.MeuAlerta;

public class Login extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final Context context = Login.this;
		
		setContentView(criaTela(context));
	}
	
	private LinearLayout criaTela(final Context context) {
		
		LinearLayout llTela = new LinearLayout(context);
		llTela.setOrientation(LinearLayout.VERTICAL);
		llTela.setPadding(20, 0, 20, 0);
		
		llTela.setBackgroundColor(getResources().getColor(android.R.color.white));
		
		LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(650, 320);
		para.gravity = Gravity.CENTER;
	
		ImageView ivLogo = new ImageView(context);
		ivLogo.setImageDrawable(getResources().getDrawable(R.drawable.newlogo));
		ivLogo.setLayoutParams(para);

		ivLogo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.x10d.com.br"));
				
				startActivity(intent);
				
			}
		});
		
		TextView tvUsuario = new TextView(context);
		tvUsuario.setText("Usuário:");
		
		final EditText etUsuario = new EditText(context);
		etUsuario.setHint("Informe seu usuário");
		
		TextView tvSenha = new TextView(context);
		tvSenha.setText("Senha:");
		
		final EditText etSenha = new EditText(context);
		etSenha.setHint("Informe sua senha");
		
		Button btnEntrar = new Button(context);
		btnEntrar.setText("Entrar");
		btnEntrar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				acaoEntrar(context, etUsuario, etSenha);		
			}
		});
		
//		llTela.addView(tvLogo);
		llTela.addView(ivLogo);
		llTela.addView(tvUsuario);
		llTela.addView(etUsuario);
		llTela.addView(tvSenha);
		llTela.addView(etSenha);
		llTela.addView(btnEntrar);
		
		return llTela;
	}
	
	private void acaoEntrar(Context context, EditText etUsuario, EditText etSenha) {
		
		String usuario = etUsuario.getText().toString();
		
		String senha = etSenha.getText().toString();
	
		if(!usuario.isEmpty() && !senha.isEmpty()) {
	
			if(usuario.equals("adm") && senha.equals("123")) {
				
				abrirDashboard(context);
				
			}else {
				
			    new MeuAlerta("Atenção", "Usuário ou Senha inválida!", context).meuAlertaOk();
		        
			}
			
		}else {
			
		    new MeuAlerta("Atenção", "Todos os campos são obrigatórios", context).meuAlertaOk();
		}		
	}
	
	
	private void abrirDashboard(Context context) {
			
		startActivity(new Intent(context, DashboardActivity.class));
		
		finish();
	}
	
}
