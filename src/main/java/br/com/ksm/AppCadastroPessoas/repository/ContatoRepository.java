package br.com.ksm.AppCadastroPessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ksm.AppCadastroPessoas.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
