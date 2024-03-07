package br.com.ksm.AppCadastroPessoas.dto;

public class PessoaDTO {

	private Long id;
	private String nome;
	private String malaDireta;
	
	public PessoaDTO() {}

	public PessoaDTO(Long id, String nome, String malaDireta) {
		this.id = id;
		this.nome = nome;
		this.malaDireta = malaDireta;
	}

	public String getMalaDireta() {
		return malaDireta;
	}

	public void setMalaDireta(String malaDireta) {
		this.malaDireta = malaDireta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
