package br.com.x10d.presenca.webservice;

import java.util.List;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import android.app.ProgressDialog;
import android.content.Context;
import br.com.x10d.presenca.model.Cadastro;
import br.com.x10d.presenca.util.CriaArquivoPDF;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.util.MeuProgressDialog;

public class ListaMembroTodosWS {

	private Context context;
	private RequestQueue requestQueue;
	
	public ListaMembroTodosWS(Context _context) {
		context = _context;
		requestQueue = VolleySingleton.getInstanciaDoVolleySingleton(_context).getRequestQueue();
	}

	public void buscarListaComTodosMembros() {

		final ProgressDialog progressDialog = MeuProgressDialog.criaProgressDialog(context, "Procurando Membros...");

		String url = IpURL.URL_SERVER_REST.getValor()+"/membro/todos";

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
				List<Cadastro> listaComUmMembro = new Reflexao().getLista(Cadastro.class, resposta.getJSONArray("membros"));
				
				if(listaComUmMembro.isEmpty()) {
					
					new MeuAlerta("Aviso", "Não encontrou o membros cadastrados", context).meuAlertaOk();
				}else {
					new CriaArquivoPDF(context).criaEchamaVisualizadorPDF(listaComUmMembro);
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}

}
