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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import br.com.x10d.presenca.model.Membro;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.util.MeuProgressDialog;

public class ListaMembroTodosNaTelaWS {

	private Context context;
	private RequestQueue requestQueue;
	private LinearLayout llTela;
	public ListaMembroTodosNaTelaWS(Context _context, LinearLayout _llTela) {
		context = _context;
		llTela = _llTela;
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
		
		if(resposta.has("membros")) {
			
			try {
				List<Membro> lista = new Reflexao().getLista(Membro.class, resposta.getJSONArray("membros"));
				
				if(lista.isEmpty()) {
					
					TextView tvVazio = new TextView(context);
					tvVazio.setText("Não encontrou membros cadastrados");
					llTela.addView(tvVazio);
					
				}else {
						
					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					lp.setMargins(10, 0, 10, 0);
					
						for(Membro membro : lista) {
							
							LinearLayout llLinha = new LinearLayout(context);
							
							TextView tvId = new TextView(context);
							tvId.setText(""+membro.getId());
							tvId.setLayoutParams(lp);
						
							TextView tvNomeMembro = new TextView(context);
							tvNomeMembro.setText(membro.getNome());
							tvNomeMembro.setLayoutParams(lp);
							
							TextView tvCongregacao = new TextView(context);
							tvCongregacao.setText(membro.getCongregacao());
							tvCongregacao.setLayoutParams(lp);
							
							llLinha.addView(tvId);
							llLinha.addView(tvNomeMembro);
							llLinha.addView(tvCongregacao);
							
							llTela.addView(llLinha);
						}

				}
				
			} 
			catch (Exception e) {
				e.printStackTrace();
			}	

		}
	}

}
