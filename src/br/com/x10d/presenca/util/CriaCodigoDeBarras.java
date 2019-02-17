package br.com.x10d.presenca.util;

import java.io.File;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import br.com.x10d.presenca.model.Cadastro;
import br.com.x10d.presenca.provider.MeuFileProvider;

public class CriaCodigoDeBarras {
	
	private Context context;
	
	public CriaCodigoDeBarras(Context context){
		this.context = context;
	}
	
	public void criaEchamaVisualizadorPDF(List<Cadastro> listaComMembros) {

	String srcPresenca = Environment.getExternalStorageDirectory()+"/Presenca/CodigoDeBarras";	
	
		try {
			new CriaCodigoDeBarrasPDF().criaPDF(srcPresenca+".pdf", listaComMembros);
			
			Toast.makeText(context, "PDF gerado com sucesso!", Toast.LENGTH_SHORT).show();
			
			//chamaVisualizadorPDF(srcPresenca+".pdf");
			MeuFileProvider.chamaVisualizadorDeArquivo(context, srcPresenca, "pdf");
		} 
		catch (Exception erro) {
		
			new MeuAlerta("Erro", "Erro na criação do PDF: "+erro, context).meuAlertaOk();

			erro.printStackTrace();
		}
	}
	
	/*
	 */
	private void chamaVisualizadorPDF(String caminhoComExtensao){
     	
	   	String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf");
	   		   	
	   				   Intent intent = new Intent(Intent.ACTION_VIEW);
	   		   				  intent.setDataAndType(Uri.fromFile(new File(caminhoComExtensao)), mimeType);		
	   		   				  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   	context.startActivity(intent);
	   	//finish(); 
	}

}
