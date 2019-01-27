package br.com.x10d.presenca.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class DashboardWebView {
	
	public View devolveWebView(Context context) {

		WebView webView = new WebView(context);
		webView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);
		
		webView.loadData(devolveHTML(), "text/html", "UTF-8");
		
		return webView;
	}

	private String devolveHTML() {
		
		String html = "<html><body><h1>Simples teste para todos os mestres</h1></body></html>";
		
		return html;
	}
}
