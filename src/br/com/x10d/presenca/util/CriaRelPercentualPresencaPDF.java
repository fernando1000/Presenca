package br.com.x10d.presenca.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;
import br.com.x10d.presenca.model.ViewPercentualPresenca;

public class CriaRelPercentualPresencaPDF {
	
	private Font font_titulo;
	protected Font font_conteudo;
	private float TAMANHO_FONTE_TITULO = 13;
	private float TAMANHO_FONTE_CONTEUDO = 12;

	public CriaRelPercentualPresencaPDF(){
		
		font_titulo = new Font(FontFamily.TIMES_ROMAN, TAMANHO_FONTE_TITULO, Font.BOLD);
		font_conteudo = new Font(FontFamily.TIMES_ROMAN, TAMANHO_FONTE_CONTEUDO);
	}
	
	public void criaPDF(String SRC_CONTRATO, List<ViewPercentualPresenca> lista) throws Exception {
    	
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
        document.add(devolveTitulo("ASSEMBL�IA DE DEUS\r\n"));
        
        for(ViewPercentualPresenca viewPercentualPresenca : lista) {

			document.add(new Paragraph(viewPercentualPresenca.getNome(), font_conteudo));
			document.add(new Paragraph(viewPercentualPresenca.getEvento(), font_conteudo));
			document.add(new Paragraph(viewPercentualPresenca.getCongregacao(), font_conteudo));
			document.add(new Paragraph(""+viewPercentualPresenca.getTotal_presenca(), font_conteudo));
			document.add(new Paragraph(""+viewPercentualPresenca.getPercentual_presenca(), font_conteudo));
			document.add(new Paragraph("\n\n", font_conteudo));
		}
        document.add(devolveData());	
        document.close();        
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

}