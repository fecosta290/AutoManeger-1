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
import com.autobots.automanager.modelo.GeradorLinkCliente;
import com.autobots.automanager.repositorios.ClienteRepositorio;


@RestController
@RequestMapping("cliente")
public class ClienteControle {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Autowired
	private GeradorLinkCliente geradorLink;
	
	@PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public void CadastrarCliente(@RequestBody Cliente cliente) {
		clienteRepositorio.save(cliente);
	}
	
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> Cliente(){
		List<Cliente> clientes = clienteRepositorio.findAll();
		if(clientes.isEmpty()){
			ResponseEntity<List<Cliente>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			geradorLink.adicionarLink(clientes);
			ResponseEntity<List<Cliente>> resposta = new ResponseEntity<>(clientes, HttpStatus.FOUND);
			return resposta;
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> BuscarCliente(@PathVariable long id){
		List<Cliente>clientes = clienteRepositorio.findAll();
		Cliente cliente = clienteRepositorio.getById(id);
		if(cliente == null){
			ResponseEntity<Cliente> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		}
		else{
			geradorLink.adicionarLink(clientes);
			geradorLink.adicionarLink(cliente);
			ResponseEntity<Cliente> resposta = new ResponseEntity<>(cliente, HttpStatus.FOUND);
			return resposta;
		}
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
