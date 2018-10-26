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
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.x10d.presenca.model.Chamada;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.util.MeuProgressDialog;

public class ListaChamadaTodosNaTelaWS {

	private Context context;
	private LinearLayout llListaDosPresentes;
	private RequestQueue requestQueue;
	
	public ListaChamadaTodosNaTelaWS(Context context, LinearLayout llListaDosPresentes) {
		this.context = context;
		this.llListaDosPresentes = llListaDosPresentes;
		requestQueue = VolleySingleton.getInstanciaDoVolleySingleton(context).getRequestQueue();
	}

	public void buscarListaComTodasChamadas(Chamada chamada) {

		final ProgressDialog progressDialog = MeuProgressDialog.criaProgressDialog(context, "Procurando Chamadas...");

		String url = IpURL.URL_SERVER_REST.getValor()+"/chamada/todos";

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

				Request.Method.POST, 
				url, 
				DevolveJsonObjectDeUmaClasse.devolve(chamada),

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

						if(volleyError.toString().contains("Timeout")) {

							new MeuAlerta("Aviso:", "Tempo excedido", context).meuAlertaOk();
						}else {
							new MeuAlerta("VolleyError", ""+volleyError, context).meuAlertaOk();
						}
					}
				});
						 //jsonObjectRequest.setRetryPolicy(VolleyTimeout.devolveTimeout());
		requestQueue.add(jsonObjectRequest);
	}
	
	private void trataResposta(JSONObject resposta) {
		
		if(resposta.has("chamadas")) {
			
			llListaDosPresentes.removeAllViews();
			try {
				List<Chamada> lista = new Reflexao().getLista(Chamada.class, resposta.getJSONArray("chamadas"));
				
				if(lista.isEmpty()) {
					
					TextView tvVazio = new TextView(context);
							 tvVazio.setText("Não encontrou chamadas cadastradas");
					llListaDosPresentes.addView(tvVazio);
				}else {
					
					
					for(Chamada chamada : lista) {
						
						TextView textView = new TextView(context);
						textView.setText(chamada.getMensagem());
						textView.setTextSize(15);
						textView.setTextColor(Color.BLACK);
						
						llListaDosPresentes.addView(textView);

					}
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	

}
