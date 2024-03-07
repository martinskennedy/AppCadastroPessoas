package br.com.ksm.AppCadastroPessoas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ksm.AppCadastroPessoas.dto.PessoaDTO;
import br.com.ksm.AppCadastroPessoas.model.Pessoa;
import br.com.ksm.AppCadastroPessoas.repository.PessoaRepository;
import br.com.ksm.AppCadastroPessoas.service.interfaces.PessoaServiceInterface;

@Service
public class PessoaService implements PessoaServiceInterface {

	private PessoaRepository pessoaRepository;
	
	@Autowired
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	@Override
	public Pessoa save(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Override
	public Optional<Pessoa> getById(Long id) {
		return pessoaRepository.findById(id);
	}
	
	@Override
	public List<Pessoa> getAll() {
		return pessoaRepository.findAll();
	}

	@Override
	public Pessoa update(Pessoa pessoa) {
		Optional<Pessoa> findPessoa = pessoaRepository.findById(pessoa.getId());
		if (findPessoa.isPresent()) {
			Pessoa updatePessoa = findPessoa.get(); // set Id
			updatePessoa.setNome(pessoa.getNome());
			updatePessoa.setEndereco(pessoa.getEndereco());
			updatePessoa.setCep(pessoa.getCep());
			updatePessoa.setCidade(pessoa.getCidade());
			updatePessoa.setUf(pessoa.getUf());
			return pessoaRepository.save(updatePessoa);
		}
		return pessoa;
	}

	@Override
	public void delete(Long id) {
		pessoaRepository.deleteById(id);	
	}

	@Override
	public List<PessoaDTO> findPessoaDTO() {
		List<Object[]> listResult = pessoaRepository.findPessoaDTO();
		List<PessoaDTO> listPessoaDTO = new ArrayList<>();
		//iteracao no listResult e lancando no listPessoaDTO
		for(Object[] result : listResult){
			PessoaDTO pessoaDTO = new PessoaDTO();
			pessoaDTO.setId(((Long)result[0]).longValue()); //id
			pessoaDTO.setNome((String)result[1]);           //nome
			pessoaDTO.setMalaDireta((String)result[2] +     //endereco
					" - CEP: " + (String)result[3] +        //cep
					" - " + (String)result[4] +             //cidade
					"/" + (String)result[5]);               //uf
			
			listPessoaDTO.add(pessoaDTO);
		}		
		if(listPessoaDTO.size() > 0)
			return listPessoaDTO;
		return null;
	}

}
