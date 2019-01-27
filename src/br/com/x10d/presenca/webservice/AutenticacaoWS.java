package br.com.x10d.presenca.webservice;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.util.MeuProgressDialog;
import br.com.x10d.presenca.view.MenuSistemaActivity;

public class AutenticacaoWS {

	private Context context;
	private RequestQueue requestQueue;

	public AutenticacaoWS(Context _context) {

		context = _context;
		requestQueue = VolleySingleton.getInstanciaDoVolleySingleton(_context).getRequestQueue();
	}

	public void buscaUsuarioWS(String usuario, String senha) {

		final ProgressDialog progressDialog = MeuProgressDialog.criaProgressDialog(context, "Autenticando Usuário");

		String url = IpURL.URL_SERVER_REST.getValor()+"/login/"+usuario+"/"+senha;

		NetworkResponseRequest networkResponseRequest = new NetworkResponseRequest(

				Request.Method.GET, 
				url,
				
				new Response.Listener<NetworkResponse>() {
					@Override
					public void onResponse(NetworkResponse networkResponse) {

						MeuProgressDialog.encerraProgressDialog(progressDialog);

						if (networkResponse.statusCode == 200) {

							abreDashboard();
						} else {
							new MeuAlerta("Atenção", "Situação inesperada, entre em contato com administrador do sistema", context).meuAlertaOk();
						}
						
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {

						MeuProgressDialog.encerraProgressDialog(progressDialog);
						
						try {
							
							if(volleyError.toString().contains("Timeout")) {
								
								new MeuAlerta("Erro", "Tempo excedido, favor tentar novamente", context).meuAlertaOk();		
							}else {
	
								if (volleyError.networkResponse.statusCode == 401) {
	
									new MeuAlerta("Aviso", "Usuário ou senha inválida ", context).meuAlertaOk();
							
								} else {
									new MeuAlerta("Erro", "Erro inesperado, entre em contato com administrador do sistema", context).meuAlertaOk();
								}
							}
							
						}catch(Exception erro){
							new MeuAlerta("Erro", "Exceção lançada: "+erro, context).meuAlertaOk();			
						}
						
					}
				});
		requestQueue.add(networkResponseRequest);
	}

	private void abreDashboard() {

		context.startActivity(new Intent(context, MenuSistemaActivity.class));
		//((LoginActivity) context).finish();
	}
}
