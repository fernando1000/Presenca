package br.com.x10d.presenca.webservice;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import br.com.x10d.presenca.model.Login;
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

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

				Request.Method.GET, 
				url,
				null,
				
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject jSONObject_response) {

						MeuProgressDialog.encerraProgressDialog(progressDialog);

						if (jSONObject_response != null) {

							abreDashboard(jSONObject_response);
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
		requestQueue.add(jsonObjectRequest);
	}

	private void abreDashboard(JSONObject jSONObject_response) {

		try {
			Login login = (Login) new DeJsonParaObjeto().transforma(Login.class, jSONObject_response);
	
			Intent intent = new Intent(context, MenuSistemaActivity.class);
				   intent.putExtra(Login.class.getSimpleName(), login);
						
			context.startActivity(intent);
			//((LoginActivity) context).finish();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
