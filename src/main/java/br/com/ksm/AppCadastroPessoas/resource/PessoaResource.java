package br.com.ksm.AppCadastroPessoas.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ksm.AppCadastroPessoas.dto.PessoaDTO;
import br.com.ksm.AppCadastroPessoas.model.Pessoa;
import br.com.ksm.AppCadastroPessoas.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/pessoas") // http://localhost:8081/api/pessoas)
public class PessoaResource {

	private PessoaService pessoaService;

	@Autowired
	public PessoaResource(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@Operation(summary = "Cadastra uma pessoa")
	@PostMapping
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
		Pessoa newPessoa = pessoaService.save(pessoa);
		if (newPessoa == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(newPessoa);
	}

	@Operation(summary = "Busca registro de uma pessoa por Id")
	@GetMapping("/{id}") // http://localhost:8081/api/pessoas/2
	public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Long id) {
		Optional<Pessoa> produto = pessoaService.getById(id);
		if (produto == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(produto);
	}

	@Operation(summary = "Busca registros de todas as pessoas cadastradas")
	@GetMapping
	public ResponseEntity<List<Pessoa>> getAllPessoas() {

		List<Pessoa> pessoas = pessoaService.getAll();
		if (pessoas == null)
			return ResponseEntity.notFound().build();
		if (pessoas.size() == 0)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(pessoas);
	}

	@Operation(summary = "Atualiza os dados de uma pessoa")
	@PutMapping
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa) {
		Pessoa upPessoa = pessoaService.update(pessoa);
		if (pessoa == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(upPessoa);
	}

	@Operation(summary = "Exclui uma pessoa")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		pessoaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // status code 204
	}

	@Operation(summary = "Busca pessoa e contato mala direta")
	@GetMapping("/pessoaDTO")
	public ResponseEntity<List<PessoaDTO>> finfPessoaDTO() {
		List<PessoaDTO> listPessoaDTO = pessoaService.findPessoaDTO();
		if (listPessoaDTO == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(listPessoaDTO);
	}
}
