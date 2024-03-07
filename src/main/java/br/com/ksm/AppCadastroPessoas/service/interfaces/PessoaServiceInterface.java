package br.com.ksm.AppCadastroPessoas.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.ksm.AppCadastroPessoas.dto.PessoaDTO;
import br.com.ksm.AppCadastroPessoas.model.Pessoa;

public interface PessoaServiceInterface {

	//Salvar
	Pessoa save(Pessoa pessoa);
	
	//Recuperar pessoa por ID
	Optional<Pessoa> getById(Long id);	

	//Pessoa por ID para mala direta
	List<PessoaDTO> findPessoaDTO();

	//Obter todas as Pessoas
	List<Pessoa> getAll();

	//Atualizar	Pessoa por ID
	Pessoa update (Pessoa pessoa);

	//Deletar Pessoa por ID
	void delete(Long id);
}
