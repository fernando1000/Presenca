package br.com.x10d.presenca.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

public class AcaoVaiParaQualquerActivity implements AcaoAlertDialog{

	private Activity activityOrigem;
	private Class<?> classeDestino;
	
	public AcaoVaiParaQualquerActivity(Activity _activityOrigem, Class<?> _classeDestino){
		this.activityOrigem = _activityOrigem;
		this.classeDestino = _classeDestino;
	}
	
	@Override
	public void fazAcaoSIM(final Context context) {

		final ProgressDialog progressDialog = MeuProgressDialog.criaProgressDialog(context, "Aguarde...");

		new android.os.Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				
				MeuProgressDialog.encerraProgressDialog(progressDialog);	

				activityOrigem.startActivity(new Intent(context, classeDestino));
				activityOrigem.finish();	
			}
		}, 1000);

	}

	@Override
	public void fazAcaoNAO(Context context) {
		// TODO Auto-generated method stub
		
	}

}
