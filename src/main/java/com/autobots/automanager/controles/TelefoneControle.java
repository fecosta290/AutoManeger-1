package com.autobots.automanager.controles;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.GeradorLinkTelefone;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
@RequestMapping("telefone")
public class TelefoneControle {
	
	@Autowired
	private GeradorLinkTelefone geradorLink;

	@Autowired
	private TelefoneRepositorio telefoneRepositorio;
	
	@PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public void CadastrarCliente(@RequestBody Telefone telefone) {
		telefoneRepositorio.save(telefone);
	}
	
	@GetMapping("/telefones")
	public List<Telefone> Telefone(){
		return telefoneRepositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Telefone> BuscarTelefone(@PathVariable long id){
		List<Telefone>telefones = telefoneRepositorio.findAll();
		Telefone telefone = telefoneRepositorio.getById(id);
		if(telefone == null){
			ResponseEntity<Telefone> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		}
		else{
			geradorLink.adicionarLink(telefones);
			geradorLink.adicionarLink(telefone);
			ResponseEntity<Telefone> resposta = new ResponseEntity<>(telefone, HttpStatus.FOUND);
			return resposta;
		}
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Telefone> Atualizar(@PathVariable Long id, 
			@RequestBody Telefone telefone){
		if (!telefoneRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		telefone.setId(id);
		telefone = telefoneRepositorio.save(telefone);
		return ResponseEntity.ok(telefone);
	}
	
	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> Deletar(@PathVariable Long id){
		if(!telefoneRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		telefoneRepositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
