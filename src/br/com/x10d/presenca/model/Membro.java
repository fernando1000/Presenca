package br.com.x10d.presenca.model;

import java.util.Date;
import java.util.List;

public class Membro {
	
	private Integer id;

	private String cpf;
	
	private Date data;
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
	private String nome_pai;
	private String nome_mae;
	
	private List<Membro> membros;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}
	
	
	

}
