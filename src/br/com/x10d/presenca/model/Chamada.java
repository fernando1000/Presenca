package br.com.x10d.presenca.model;

public class Chamada {
	
	private String membroId;
	private String dataDMA;
	private String dataDMAHMS;
	private String palestra;

	public static final String COLUMN_TEXT_MEMBRO_ID = "membroId";
	public static final String COLUMN_TEXT_DATA_DMA = "dataDMA";
	public static final String COLUMN_TEXT_DATA_DMAHMS = "dataDMAHMS";
	public static final String COLUMN_TEXT_PALESTRA = "palestra";
	
	
	public String getPalestra() {
		return palestra;
	}
	public void setPalestra(String palestra) {
		this.palestra = palestra;
	}
	public String getMembroId() {
		return membroId;
	}
	public void setMembroId(String membroId) {
		this.membroId = membroId;
	}
	public String getDataDMA() {
		return dataDMA;
	}
	public void setDataDMA(String dataDMA) {
		this.dataDMA = dataDMA;
	}
	public String getDataDMAHMS() {
		return dataDMAHMS;
	}
	public void setDataDMAHMS(String dataDMAHMS) {
		this.dataDMAHMS = dataDMAHMS;
	}

	

}
