package br.com.x10d.presenca.provider;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

public class MeuFileProvider extends FileProvider{

	public static void chamaVisualizadorDeArquivo(Context context, String SRC_CONTRATO, String extensaoPDFouDOC) {

		String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extensaoPDFouDOC);
			
		String caminhoComExtensao = SRC_CONTRATO +"."+ extensaoPDFouDOC;
		
		try {		
			String pacoteOndeEstaMeuProvider = context.getApplicationContext().getPackageName()+".provider";

			Uri path = FileProvider.getUriForFile(context, pacoteOndeEstaMeuProvider, new File(caminhoComExtensao));
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(path, mimeType);
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
	
			context.startActivity(intent);
		} catch (Exception ex) {
			Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
		}        
	}

	
}
