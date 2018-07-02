package br.com.x10d.presenca.model;

public class Membro {

	private int id;
	private String nome;
	private String data_nascimento;
	private String naturalidade;
	private String profissao;
	private String email;
	private String fone_residencial;
	private String fone_celular;
	private String whatsapp;
	private String endereco;
	private String data_batismo;
	private String local_batismo;
	private String congregacao;
	private String cargo;
	private String rg;
	private String cpf;
	private String nome_pai;
	private String nome_mae;

	public static final String COLUMN_INTEGER_ID = "id";
	public static final String COLUMN_TEXT_NOME = "nome";
	public static final String COLUMN_TEXT_DATA_NASCIMENTO = "data_nascimento";
	public static final String COLUMN_TEXT_NATURALIDADE = "naturalidade";
	public static final String COLUMN_TEXT_PROFISSAO = "profissao";
	public static final String COLUMN_TEXT_EMAIL = "email";
	public static final String COLUMN_TEXT_FONE_RESIDENCIAL = "fone_residencial";
	public static final String COLUMN_TEXT_FONE_CELULAR = "fone_celular";
	public static final String COLUMN_TEXT_WHATSAPP = "whatsapp";
	public static final String COLUMN_TEXT_ENDERECO = "endereco";
	public static final String COLUMN_TEXT_DATA_BATISMO = "data_batismo";
	public static final String COLUMN_TEXT_LOCAL_BATISMO = "local_batismo";
	public static final String COLUMN_TEXT_CONGREGACAO = "congregacao";
	public static final String COLUMN_TEXT_CARGO = "cargo";
	public static final String COLUMN_TEXT_RG = "rg";
	public static final String COLUMN_TEXT_CPF = "cpf";
	public static final String COLUMN_TEXT_NOME_PAI = "nome_pai";
	public static final String COLUMN_TEXT_NOME_MAE = "nome_mae";
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFone_residencial() {
		return fone_residencial;
	}
	public void setFone_residencial(String fone_residencial) {
		this.fone_residencial = fone_residencial;
	}
	public String getFone_celular() {
		return fone_celular;
	}
	public void setFone_celular(String fone_celular) {
		this.fone_celular = fone_celular;
	}
	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getData_batismo() {
		return data_batismo;
	}
	public void setData_batismo(String data_batismo) {
		this.data_batismo = data_batismo;
	}
	public String getLocal_batismo() {
		return local_batismo;
	}
	public void setLocal_batismo(String local_batismo) {
		this.local_batismo = local_batismo;
	}
	public String getCongregacao() {
		return congregacao;
	}
	public void setCongregacao(String congregacao) {
		this.congregacao = congregacao;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome_pai() {
		return nome_pai;
	}
	public void setNome_pai(String nome_pai) {
		this.nome_pai = nome_pai;
	}
	public String getNome_mae() {
		return nome_mae;
	}
	public void setNome_mae(String nome_mae) {
		this.nome_mae = nome_mae;
	}
	
	

}
