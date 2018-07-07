package br.com.x10d.presenca.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import br.com.x10d.presenca.model.Membro;

public class GeraPDF {
	
	private Font font_titulo;
	protected Font font_conteudo;
	private float TAMANHO_FONTE_TITULO = 13;
	private float TAMANHO_FONTE_CONTEUDO = 12;

	public GeraPDF(){
		
		font_titulo = new Font(FontFamily.TIMES_ROMAN, TAMANHO_FONTE_TITULO, Font.BOLD);
		font_conteudo = new Font(FontFamily.TIMES_ROMAN, TAMANHO_FONTE_CONTEUDO);
	}
	
	public void criaPDF(String SRC_CONTRATO, List<Membro> listaComMembros) throws Exception {
    	
    	File file = new File(SRC_CONTRATO);
    	
    	if(!file.exists()){
    		file.getParentFile().mkdirs();  	
    	}
    	     
    	float margemEsquerda = 30;
    	float margemDireita = 60;
    	float margemEmCima = 60;
    	float margemEmBaixo = 40;
    	
    	Document document = new Document();
    			 document.setMargins(margemEsquerda, margemDireita, margemEmCima, margemEmBaixo);
    	
    	PdfWriter.getInstance(document, new FileOutputStream(SRC_CONTRATO));

        document.open();    
	        	
        document.add(devolveTitulo("ASSEMBLÉIA DE DEUS\r\n"));
       
        int width = 100; 
        int height = 25;
        
        for(Membro membro : listaComMembros) {

			document.add(new Paragraph(membro.getCongregacao().toUpperCase(), font_conteudo));
			document.add(new Paragraph(membro.getNome(), font_conteudo));
			
			String codigoBarras = String.format("%08d", membro.getId());
			Bitmap bitmapDoCodigoDeBarras = criaBitmapDoCodigoDeBarras(codigoBarras, width, height);
			Image imageDoCodigoDeBarras = transformaBitMapEmImage(bitmapDoCodigoDeBarras);        	 
		    document.add(imageDoCodigoDeBarras);
		    
			document.add(new Paragraph("        "+codigoBarras, font_conteudo));
			document.add(new Paragraph("\n\n", font_conteudo));
		}
  
        document.add(devolveData());	
    
        document.close();        
    }
	
	   public Image transformaBitMapEmImage(Bitmap bitmap){
	    	
	        //Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
	        
				  							 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				  bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
				  
				  
			Image image = null;
				try {
					image = Image.getInstance(byteArrayOutputStream.toByteArray());
				} 
				catch (Exception e) {
					e.printStackTrace();
				}

	        return image;
	    }

    private Paragraph devolveConteudo(String conteudo){
    	
    	Paragraph paragraph_conteudo1 = new Paragraph(conteudo, font_conteudo);
				  paragraph_conteudo1.setAlignment(Element.ALIGN_JUSTIFIED);
     return paragraph_conteudo1;
    }
    
    private Paragraph devolveTitulo(String titulo){
		
    	Paragraph paragraph_tituloPrincipal = new Paragraph(titulo, font_titulo);
		  		  paragraph_tituloPrincipal.setAlignment(Element.ALIGN_CENTER);			  
       return paragraph_tituloPrincipal;
    }

    private Paragraph devolveData(){

    	Paragraph paragraph_dataAtual = new Paragraph(devolveDataAtualFormatada() + "\n\n\n\n\n", font_conteudo);
		 		  paragraph_dataAtual.setAlignment(Element.ALIGN_RIGHT);    	
	  return paragraph_dataAtual;	 		  
    }
    
	private String devolveDataAtualFormatada(){
    	
    	Date data =  new Date();
    	Locale locale = new Locale("pt","BR");
    	DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", locale); 
    	String dataFormatada = dateFormat.format(data);
    	String dataAtual = "Itapevi, "+dataFormatada+". \n";
  
    	return dataAtual;
    }
	
	private Bitmap criaBitmapDoCodigoDeBarras(String texto, int width, int height) throws WriterException {
		   
		MultiFormatWriter writer = new MultiFormatWriter();
	    String finalData = Uri.encode(texto);

	    // Use 1 as the height of the matrix as this is a 1D Barcode.
	    BitMatrix bm = writer.encode(finalData, BarcodeFormat.CODE_128, width, 1);
	    
	    int bmWidth = bm.getWidth();

	    Bitmap imageBitmap = Bitmap.createBitmap(bmWidth, height, Config.ARGB_8888);

	    for (int i = 0; i < bmWidth; i++) {
	        // Paint columns of width 1
	        int[] column = new int[height];
	        Arrays.fill(column, bm.get(i, 0) ? Color.BLACK : Color.WHITE);
	        imageBitmap.setPixels(column, 0, 1, i, 0, 1, height);
	    }

	    return imageBitmap;
	}

}
