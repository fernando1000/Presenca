package br.com.x10d.presenca.model;

import java.util.Date;
import java.util.List;

public class Cadastro {
	
	private int id;
	private String codigoBarras;
	private String cpf;
	private Date dataCadastro;
	private String nome;	
	private String dataNascimento;
	private String naturalidade;
	private String profissao;
	private String email;
	private String foneResidencial;
	private String foneCelular;
	private String whatsapp;
	private String endereco;
	private String dataBatismo;
	private String localBatismo;
	private String congregacao;
	private String cargo;
	private String rg;
	private String nomePai;
	private String nomeMae;	
	private String camiseta;	
	private List<Cadastro> membros;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	public String getFoneResidencial() {
		return foneResidencial;
	}
	public void setFoneResidencial(String foneResidencial) {
		this.foneResidencial = foneResidencial;
	}
	public String getFoneCelular() {
		return foneCelular;
	}
	public void setFoneCelular(String foneCelular) {
		this.foneCelular = foneCelular;
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
	public String getDataBatismo() {
		return dataBatismo;
	}
	public void setDataBatismo(String dataBatismo) {
		this.dataBatismo = dataBatismo;
	}
	public String getLocalBatismo() {
		return localBatismo;
	}
	public void setLocalBatismo(String localBatismo) {
		this.localBatismo = localBatismo;
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
	public String getNomePai() {
		return nomePai;
	}
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	public String getCamiseta() {
		return camiseta;
	}
	public void setCamiseta(String camiseta) {
		this.camiseta = camiseta;
	}
	public List<Cadastro> getMembros() {
		return membros;
	}
	public void setMembros(List<Cadastro> membros) {
		this.membros = membros;
	}

}
