package br.com.x10d.presenca.util;

import java.io.File;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import br.com.x10d.presenca.model.ViewAproveitamentoPorDia;

public class CriaRelAproveitamentoPorDia {
	
	private Context context;
	
	public CriaRelAproveitamentoPorDia(Context context){
		this.context = context;
	}
	
	public void criaEchamaVisualizadorPDF(List<ViewAproveitamentoPorDia> lista) {

	String srcPresenca = Environment.getExternalStorageDirectory()+"/Presenca/AproveitamentoPorDia";	
	
		try {
			new CriaRelAproveitamentoPorDiaPDF().criaPDF(srcPresenca+".pdf", lista);
			
			Toast.makeText(context, "PDF gerado com sucesso!", Toast.LENGTH_SHORT).show();
			
			chamaVisualizadorPDF(srcPresenca+".pdf");
		} 
		catch (Exception erro) {
		
			new MeuAlerta("Erro", "Erro n�o cria��o do PDF: "+erro, context).meuAlertaOk();

			erro.printStackTrace();
		}
	}
	
	private void chamaVisualizadorPDF(String caminhoComExtensao){
     	
	   	String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(".pdf");
	   		   	
	   				   Intent intent = new Intent(Intent.ACTION_VIEW);
	   		   				  intent.setDataAndType(Uri.fromFile(new File(caminhoComExtensao)), mimeType);		
	   		   				  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   	context.startActivity(intent);
	   	//finish(); 
	}


}
