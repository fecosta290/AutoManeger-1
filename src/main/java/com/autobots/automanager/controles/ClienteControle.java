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

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.repositorios.ClienteRepositorio;


@RestController
@RequestMapping("cliente")
public class ClienteControle {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public void CadastrarCliente(@RequestBody Cliente cliente) {
		clienteRepositorio.save(cliente);
	}
	
	@GetMapping("/clientes")
	public List<Cliente> Cliente(){
		return clienteRepositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> BuscarCliente(@PathVariable long id){
		return clienteRepositorio.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Cliente> Atualizar(@PathVariable Long id, 
			@RequestBody Cliente cliente){
		if (!clienteRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(id);
		cliente = clienteRepositorio.save(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> Deletar(@PathVariable Long id){
		if(!clienteRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		clienteRepositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
