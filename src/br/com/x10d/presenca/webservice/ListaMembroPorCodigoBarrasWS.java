package br.com.x10d.presenca.webservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import br.com.x10d.presenca.model.Chamada;
import br.com.x10d.presenca.model.Cadastro;
import br.com.x10d.presenca.util.AcaoAlertDialog;
import br.com.x10d.presenca.util.AcaoVaiParaQualquerActivity;
import br.com.x10d.presenca.util.Animacao;
import br.com.x10d.presenca.util.CriaCodigoDeBarras;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.util.MeuProgressDialog;
import br.com.x10d.presenca.view.CadastroMembroActivity;
import br.com.x10d.presenca.view.ChamadaActivity;

public class ListaMembroPorCodigoBarrasWS {

	private Context context;
	private LinearLayout llListaDosPresentes;
	//private String dataAtualFormatada;
	private RequestQueue requestQueue;
	//private String nomePalestra;
	
	public ListaMembroPorCodigoBarrasWS(Context context, LinearLayout llListaDosPresentes) {
		this.context = context;
		this.llListaDosPresentes = llListaDosPresentes;
		//this.dataAtualFormatada = dataAtualFormatada;
		//this.nomePalestra = nomePalestra;
		requestQueue = VolleySingleton.getInstanciaDoVolleySingleton(context).getRequestQueue();
	}

	public void buscarMembro(Chamada chamada) {
		
		final ProgressDialog progressDialog = MeuProgressDialog.criaProgressDialog(context, "Procurando Membro...");

		String url = IpURL.URL_SERVER_REST.getValor()+"/chamada";

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

						new MeuAlerta("VolleyError: "+volleyError, null, context).meuAlertaOk();
					}
				});
						 
		jsonObjectRequest.setRetryPolicy(VolleyTimeout.devolveTimeout());
		
		requestQueue.add(jsonObjectRequest);
	}
	
	private void trataResposta(JSONObject resposta) {
		
		try {
			if(resposta.has("mensagem")) {
				String mensagem = resposta.getString("mensagem");
				if(mensagem.contains("null")) {

					if(resposta.has("chamadas")) {
						
						limpaMensagemDeChamadaEmBranco();
						
						List<Chamada> listaComUmaChamada = new Reflexao().getLista(Chamada.class, resposta.getJSONArray("chamadas"));
								
						if(!listaComUmaChamada.isEmpty()) {
						
						Chamada chamada = listaComUmaChamada.get(0);
										
						TextView textView = new TextView(context);
						textView.setText(chamada.getMensagem());
						textView.setTextSize(15);
						textView.setTextColor(Color.BLACK);
										
						llListaDosPresentes.addView(textView);
						}
					}
				}else {
					new MeuAlerta("Aviso", mensagem, context).meuAlertaOk();
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private void limpaMensagemDeChamadaEmBranco() {
		
		for(int i=0; i<llListaDosPresentes.getChildCount(); i++ ) {
			Object obj = llListaDosPresentes.getChildAt(i);
			if(obj instanceof TextView) {
				TextView tv = (TextView)obj;
				String texto =tv.getText().toString();
				if(texto.contains("Não encontrou")) {
					
					llListaDosPresentes.removeAllViews();
					break;
				}
			}
		}
		
	}

}
