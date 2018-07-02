package br.com.x10d.presenca.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

public class AcaoSairDoAplicativo implements AcaoAlertDialog{

	private Activity activity;
	
	public AcaoSairDoAplicativo(Activity _activity){
		this.activity = _activity;
	}
	
	@Override
	public void fazAcaoSIM(final Context context) {

		final ProgressDialog progressDialog = MeuProgressDialog.criaProgressDialog(context, "Saindo...");

		new android.os.Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				
				MeuProgressDialog.encerraProgressDialog(progressDialog);	
				
				activity.finishAffinity();
			}
		}, 1000);

	}

	@Override
	public void fazAcaoNAO(Context context) {
		// TODO Auto-generated method stub
		
	}

}
