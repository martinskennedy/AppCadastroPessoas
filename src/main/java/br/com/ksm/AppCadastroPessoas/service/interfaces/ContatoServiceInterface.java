package br.com.ksm.AppCadastroPessoas.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.ksm.AppCadastroPessoas.model.Contato;

public interface ContatoServiceInterface {

	//Adicionar	um novo Contato a uma Pessoa
	Contato save(Contato contato);	
	//Obter	Contato por ID
	Optional<Contato> getById(Long id);	
	//Listar todos os Contatos de uma Pessoa
	List<Contato> getAll();	
	//Atualizar	Contato por ID
	Contato update(Contato contato);
	//Deletar Contato por ID
	void delete(Long id);
}
