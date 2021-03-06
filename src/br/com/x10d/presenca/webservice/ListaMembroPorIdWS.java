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
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import br.com.x10d.presenca.model.Cadastro;
import br.com.x10d.presenca.util.Animacao;
import br.com.x10d.presenca.util.CriaCodigoDeBarras;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.util.MeuProgressDialog;

public class ListaMembroPorIdWS {

	private Context context;
	private RequestQueue requestQueue;
	
	public ListaMembroPorIdWS(Context _context) {
		context = _context;
		requestQueue = VolleySingleton.getInstanciaDoVolleySingleton(_context).getRequestQueue();
	}

	public void buscarListaDeMembros(int id) {

		final ProgressDialog progressDialog = MeuProgressDialog.criaProgressDialog(context, "Procurando Membro...");

		String url = IpURL.URL_SERVER_REST.getValor()+"/membro/"+id;

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

						new MeuAlerta("VolleyError: "+volleyError, null, context).meuAlertaOk();
					}
				});
						 //jsonObjectRequest.setRetryPolicy(VolleyTimeout.devolveTimeout());
		requestQueue.add(jsonObjectRequest);
	}
	
	private void trataResposta(JSONObject resposta) {
		
		if(resposta.has("membros")) {
			try {
				List<Cadastro> listaComUmMembro = new DeListaJsonParaListaObjeto().getLista(Cadastro.class, resposta.getJSONArray("membros"));
				
				if(listaComUmMembro.isEmpty()) {
					
					new MeuAlerta("Aviso", "N�o encontrou o membro selecionado", context).meuAlertaOk();
				}else {
					new CriaCodigoDeBarras(context).criaEchamaVisualizadorPDF(listaComUmMembro);
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}

}
