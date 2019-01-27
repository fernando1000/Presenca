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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import br.com.x10d.presenca.model.ViewPercentualPresenca;

public class CriaRelPercentualPresencaPDF {
	
	private Font font_titulo;
	protected Font font_conteudo;
	private float TAMANHO_FONTE_TITULO = 17;
	private float TAMANHO_FONTE_CONTEUDO = 12;
	private Font fontTabela;
	private Font fontTabelaTitulo;
	
	private float TAMANHO_FONTE_TABELA = 12;

	public CriaRelPercentualPresencaPDF(){
		
		font_titulo = new Font(FontFamily.TIMES_ROMAN, TAMANHO_FONTE_TITULO, Font.BOLD);
		font_conteudo = new Font(FontFamily.TIMES_ROMAN, TAMANHO_FONTE_CONTEUDO);
		fontTabela = new Font(FontFamily.HELVETICA, TAMANHO_FONTE_TABELA, Font.NORMAL);
		fontTabelaTitulo = new Font(FontFamily.HELVETICA, TAMANHO_FONTE_TABELA, Font.BOLD);

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
        document.add(devolveTitulo("RELATÓRIO PERCENTUAL PRESENÇA\r\n"));
        document.add(devolveTitulo("\n"));
        
	    PdfPTable tableExterna = new PdfPTable(5);
	    tableExterna.setWidthPercentage(100);
	    //tableExterna.getDefaultCell().setBorder(0);
		tableExterna.addCell(devolveCellTitulo("Nome", PdfPCell.ALIGN_CENTER));
		tableExterna.addCell(devolveCellTitulo("Evento", PdfPCell.ALIGN_CENTER));
		tableExterna.addCell(devolveCellTitulo("Congregacao", PdfPCell.ALIGN_CENTER));
		tableExterna.addCell(devolveCellTitulo("Total presença", PdfPCell.ALIGN_CENTER));
		tableExterna.addCell(devolveCellTitulo("Percentual presença", PdfPCell.ALIGN_CENTER));
        
	    for(ViewPercentualPresenca pp : lista) {
			
			tableExterna.addCell(devolveCell(pp.getNome(), PdfPCell.ALIGN_CENTER));
			tableExterna.addCell(devolveCell(pp.getEvento(), PdfPCell.ALIGN_CENTER));
			tableExterna.addCell(devolveCell(pp.getCongregacao(), PdfPCell.ALIGN_CENTER));
			tableExterna.addCell(devolveCell(""+pp.getTotal_presenca(), PdfPCell.ALIGN_CENTER));
			tableExterna.addCell(devolveCell(""+pp.getPercentual_presenca()+"%", PdfPCell.ALIGN_CENTER));
		}
	    document.add(tableExterna);
        document.add(devolveData());	
        document.close();        
    }
	public PdfPCell devolveCellTitulo(String texto, int alignment) {
		
	    PdfPCell cell = new PdfPCell(new Phrase(texto, fontTabelaTitulo));
	    cell.setPadding(0);
	    cell.setHorizontalAlignment(alignment);
	    //cell.setBorder(PdfPCell.NO_BORDER);
	    
	    return cell;
	}
	public PdfPCell devolveCell(String texto, int alignment) {
		
	    PdfPCell cell = new PdfPCell(new Phrase(texto, fontTabela));
	    cell.setPadding(0);
	    cell.setHorizontalAlignment(alignment);
	    //cell.setBorder(PdfPCell.NO_BORDER);
	    
	    return cell;
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
