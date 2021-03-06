package br.com.x10d.presenca.webservice;

import java.util.List;
import org.json.JSONObject;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import android.app.ProgressDialog;
import android.content.Context;
import br.com.x10d.presenca.model.ViewPercentualPresenca;
import br.com.x10d.presenca.util.CriaRelPercentualPresenca;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.util.MeuProgressDialog;

public class RelatorioPercentualPresencaWS {

	private Context context;
	private RequestQueue requestQueue;
	
	public RelatorioPercentualPresencaWS(Context _context) {
		context = _context;
		requestQueue = VolleySingleton.getInstanciaDoVolleySingleton(_context).getRequestQueue();
	}

	public void buscaRelatorio() {

		final ProgressDialog progressDialog = MeuProgressDialog.criaProgressDialog(context, "Buscando Relat�rio...");

		String url = IpURL.URL_SERVER_REST.getValor()+"/relatorio/PercentualPresenca";

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

				Request.Method.GET, 
				url, 
				null,

				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject resposta) {

						MeuProgressDialog.encerraProgressDialog(progressDialog);

						trataResposta(resposta);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {

						MeuProgressDialog.encerraProgressDialog(progressDialog);

						NetworkResponse networkResponse = volleyError.networkResponse;
						
						int statusCode = networkResponse.statusCode;
			
						new MeuAlerta("Erro", "VolleyError: "+volleyError+" StatusCode: "+statusCode, context).meuAlertaOk();
					}
				});
						 //jsonObjectRequest.setRetryPolicy(VolleyTimeout.devolveTimeout());
		requestQueue.add(jsonObjectRequest);
	}
	
	private void trataResposta(JSONObject resposta) {
		
		if(resposta.has("lista")) {
			try {
				List<ViewPercentualPresenca> lista = new DeListaJsonParaListaObjeto().getLista(ViewPercentualPresenca.class, resposta.getJSONArray("lista"));
				
				if(lista.isEmpty()) {
					
					new MeuAlerta("Aviso", "N�o encontrou dados no relat�rio", context).meuAlertaOk();
				}else {
					new CriaRelPercentualPresenca(context).criaEchamaVisualizadorPDF(lista);
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}

}
