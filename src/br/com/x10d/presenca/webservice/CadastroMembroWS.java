package br.com.x10d.presenca.webservice;

import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import br.com.x10d.presenca.model.Membro;
import br.com.x10d.presenca.util.Animacao;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.util.MeuProgressDialog;

public class CadastroMembroWS {

	private Context context;
	private RequestQueue requestQueue;
	private LinearLayout llTela;
	
	public CadastroMembroWS(Context _context, LinearLayout _llTela) {
		llTela = _llTela;
		context = _context;
		requestQueue = VolleySingleton.getInstanciaDoVolleySingleton(_context).getRequestQueue();
	}

	public void cadastrar(Membro membro) {

		final ProgressDialog progressDialog = MeuProgressDialog.criaProgressDialog(context, "Cadastrando Membro...");

		String url = IpURL.URL_SERVER_REST.getValor()+"/membro";

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

				Request.Method.POST, 
				url, 
				DevolveJsonObjectDeUmaClasse.devolve(membro),

				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject jSONObject_response) {

						MeuProgressDialog.encerraProgressDialog(progressDialog);

						new MeuAlerta("Aviso", "Cadastro realizado com sucesso!", context).meuAlertaOk();
						
						limpaTodosOsCampos(llTela);
						
						new Animacao().piscaView(llTela);

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

	private void limpaTodosOsCampos(LinearLayout llTela) {
		
		for(int i=0; i<llTela.getChildCount(); i++) {
			
			Object obj = llTela.getChildAt(i);
			
			if(obj instanceof LinearLayout) {
			
				LinearLayout llLinha = (LinearLayout) obj;
				
				for(int x=0; x<llLinha.getChildCount(); x++) {
				
					Object obj2 = llLinha.getChildAt(x);
					
					if(obj2 instanceof EditText) {
						
						EditText editText = (EditText) obj2;
						editText.setText("");
					}
				}
			}
		}
	}

}
