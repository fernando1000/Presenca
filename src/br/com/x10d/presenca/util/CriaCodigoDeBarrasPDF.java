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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import br.com.x10d.presenca.model.Cadastro;

public class CriaCodigoDeBarrasPDF {
	
	private Font fontTitulo;
	private Font fontConteudo;
	private Font fontTabela;
	private float TAMANHO_FONTE_TITULO = 17;
	private float TAMANHO_FONTE_CONTEUDO = 12;
	private float TAMANHO_FONTE_TABELA = 12;

	public CriaCodigoDeBarrasPDF(){
		
		fontTitulo = new Font(FontFamily.TIMES_ROMAN, TAMANHO_FONTE_TITULO, Font.BOLD);
		fontConteudo = new Font(FontFamily.TIMES_ROMAN, TAMANHO_FONTE_CONTEUDO);
		fontTabela = new Font(FontFamily.HELVETICA, TAMANHO_FONTE_TABELA, Font.BOLD);
	}
	
	public void criaPDF(String SRC_CONTRATO, List<Cadastro> listaComMembros) throws Exception {
    	
    	File file = new File(SRC_CONTRATO);
    	
    	if(!file.exists()){
    		file.getParentFile().mkdirs();  	
    	}
    	     
    	float margemEsquerda = 10;
    	float margemDireita = 10;
    	float margemEmCima = 10;
    	float margemEmBaixo = 10;
    	
    	Document document = new Document();
    			 document.setMargins(margemEsquerda, margemDireita, margemEmCima, margemEmBaixo);
    	
    	PdfWriter.getInstance(document, new FileOutputStream(SRC_CONTRATO));

        document.open();    
        document.add(devolveTitulo("ASSEMBLÉIA DE DEUS\r\n"));
		document.add(new Paragraph("\n\n", fontConteudo));
		   
        int width = 50; 
        int height = 20;

	    PdfPTable tableExterna = new PdfPTable(3);
	    tableExterna.setWidthPercentage(100);
	    tableExterna.getDefaultCell().setBorder(0);
	  
	    for(Cadastro membro : listaComMembros) {
			
				String codigoBarras = String.format("%08d", membro.getId());
				Bitmap bitmapDoCodigoDeBarras = criaBitmapDoCodigoDeBarras(codigoBarras, width, height);
				Image imageDoCodigoDeBarras = transformaBitMapEmImage(bitmapDoCodigoDeBarras);        	 
					  
			PdfPTable tableInterna = new PdfPTable(1);
					  tableInterna.setWidthPercentage(100);
					  tableInterna.getDefaultCell().setBorder(0);
					  
					  String nome = membro.getNome().toUpperCase();
					  int tamanhoNome = nome.length();
					  if(tamanhoNome > 20 ) {
						  nome = nome.substring(0, 20);
					  }
					  tableInterna.addCell(devolveCell(nome, PdfPCell.ALIGN_CENTER, fontTabela));
					  tableInterna.addCell(devolveCell(membro.getCongregacao().toUpperCase(), PdfPCell.ALIGN_CENTER, fontConteudo));
					  tableInterna.addCell(imageDoCodigoDeBarras);
					  tableInterna.addCell(devolveCell(codigoBarras, PdfPCell.ALIGN_CENTER, fontTabela));
					  tableInterna.addCell(devolveCell("\n", PdfPCell.ALIGN_CENTER, fontTabela));
		tableExterna.addCell(tableInterna);														
		}
	    document.add(tableExterna);
        document.add(devolveData());	
        document.close();        
    }
	
	public PdfPCell devolveCell(String texto, int alignment, Font fontTabela) {
		
	    PdfPCell cell = new PdfPCell(new Phrase(texto, fontTabela));
	    cell.setPadding(0);
	    cell.setHorizontalAlignment(alignment);
	    cell.setBorder(PdfPCell.NO_BORDER);
	    
	    return cell;
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
    	
    	Paragraph paragraph_conteudo1 = new Paragraph(conteudo, fontConteudo);
				  paragraph_conteudo1.setAlignment(Element.ALIGN_JUSTIFIED);
     return paragraph_conteudo1;
    }
    
    private Paragraph devolveTitulo(String titulo){
		
    	Paragraph paragraph_tituloPrincipal = new Paragraph(titulo, fontTitulo);
		  		  paragraph_tituloPrincipal.setAlignment(Element.ALIGN_CENTER);			  
       return paragraph_tituloPrincipal;
    }

    private Paragraph devolveData(){

    	Paragraph paragraph_dataAtual = new Paragraph(devolveDataAtualFormatada() + "\n\n\n\n\n", fontConteudo);
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
