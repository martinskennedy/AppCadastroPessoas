package br.com.ksm.AppCadastroPessoas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ksm.AppCadastroPessoas.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	@Query(value = "SELECT p.id, p.nome, p.endereco, p.cep, p.cidade, p.uf FROM pessoa p", nativeQuery = true)
	List<Object[]> findPessoaDTO();
}
