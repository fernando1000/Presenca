package br.com.x10d.presenca.webservice;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import br.com.x10d.presenca.model.Membro;
import br.com.x10d.presenca.util.Animacao;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.util.MeuProgressDialog;

public class ListaMembroWS {

	private Context context;
	private RequestQueue requestQueue;
	
	public ListaMembroWS(Context _context) {
		context = _context;
		requestQueue = VolleySingleton.getInstanciaDoVolleySingleton(_context).getRequestQueue();
	}

	public void buscarListaDeMembros() {

		final ProgressDialog progressDialog = MeuProgressDialog.criaProgressDialog(context, "Procurando Membros...");

		String url = IpURL.URL_SERVER_REST.getValor()+"/membro";

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

				Request.Method.GET, 
				url, 
				null,

				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject resposta) {

						MeuProgressDialog.encerraProgressDialog(progressDialog);

						if(resposta.has("membros")) {
							
							try {
								List<Membro> lista = new Reflexao().getLista(Membro.class, resposta.getJSONArray("membros"));
								
								Log.i("tag","lista: "+lista);
							} 
							catch (Exception e) {
								e.printStackTrace();
							}	

						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {

						MeuProgressDialog.encerraProgressDialog(progressDialog);

						new MeuAlerta("VolleyError: "+volleyError, null, context).meuAlertaOk();
					}
				});
						 //jsonObjectRequest.setRetryPolicy(VolleyTimeout.devolveTimeout());
		requestQueue.add(jsonObjectRequest);
	}

}
