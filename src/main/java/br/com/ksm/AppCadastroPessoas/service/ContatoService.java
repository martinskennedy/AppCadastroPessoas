package br.com.ksm.AppCadastroPessoas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ksm.AppCadastroPessoas.exception.ResourceNotFoundException;
import br.com.ksm.AppCadastroPessoas.model.Contato;
import br.com.ksm.AppCadastroPessoas.model.Pessoa;
import br.com.ksm.AppCadastroPessoas.repository.ContatoRepository;
import br.com.ksm.AppCadastroPessoas.repository.PessoaRepository;
import br.com.ksm.AppCadastroPessoas.service.interfaces.ContatoServiceInterface;

@Service
public class ContatoService implements ContatoServiceInterface {

	private ContatoRepository contatoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	public ContatoService(ContatoRepository contatoRepository) {
		this.contatoRepository = contatoRepository;
	}

	@Override
	public Contato save(Contato contato) {
		// verificar se a pessoa existe, caso não, avisar
				if (contato.getPessoa().getId() != null) {
					Optional<Pessoa> findPessoa = pessoaRepository.findById(contato.getPessoa().getId());
					if (findPessoa.isEmpty()) {
						throw new ResourceNotFoundException("[Contato] Pessoa não encontrada");
					}else {
						contato.setPessoa(findPessoa.get());
						return contatoRepository.save(contato);
					}		
				}else {
					throw new ResourceNotFoundException("[Contato] Pessoa está vazio");
				}		
	}

	@Override
	public Optional<Contato> getById(Long id) {
		return contatoRepository.findById(id);
	}

	@Override
	public List<Contato> getAll() {
		return contatoRepository.findAll();
	}

	@Override
	public Contato update(Contato contato) {
		Optional<Contato> findContato = contatoRepository.findById(contato.getId());
		if (findContato.isPresent()) {
			Contato updateContato = findContato.get(); // set Id
			updateContato.setPessoa(findContato.get().getPessoa()); // evita atualizar produto errado no estoque
			updateContato.setTipoContato(contato.getTipoContato());
			updateContato.setContato(contato.getContato());
			return contatoRepository.save(updateContato);
		}
		return contato;
	}

	@Override
	public void delete(Long id) {
		contatoRepository.deleteById(id);	
	}

}
