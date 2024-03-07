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

import br.com.ksm.AppCadastroPessoas.model.Contato;
import br.com.ksm.AppCadastroPessoas.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/contato") //http://localhost:8081/api/contato
public class ContatoResource {

	private ContatoService contatoService;

	@Autowired
	public ContatoResource(ContatoService contatoService) {
		this.contatoService = contatoService;
	}
	
	@Operation(summary = "Cadastra um contato")
	@PostMapping
	public ResponseEntity<Contato> save(@RequestBody Contato contato){
		Contato newContato = contatoService.save(contato);
		if(newContato == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(newContato);
	}
	
	@Operation(summary = "Busca registro de um contato por Id")
	@GetMapping("/{id}") //http://localhost:8081/api/contato/2
	public ResponseEntity<Optional<Contato>> getById(@PathVariable Long id){
		Optional<Contato> findContato = contatoService.getById(id);
		if(findContato == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(findContato);
	}
	
	@Operation(summary = "Busca registros de todos os contatos cadastrados")
	@GetMapping
	public ResponseEntity<List<Contato>> getAll(){				
		List<Contato> findContatos = contatoService.getAll();
		if(findContatos == null)
			return ResponseEntity.notFound().build();
		if(findContatos.size() == 0)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(findContatos);
	}
	
	@Operation(summary = "Atualiza os dados de um contato")
	@PutMapping
	public ResponseEntity<Contato> update(@RequestBody Contato contato){
		Contato findContato = contatoService.update(contato);
		if(findContato == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(findContato);
	}
	
	@Operation(summary = "Exclui um contato")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		contatoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //status code 204
	}
}
