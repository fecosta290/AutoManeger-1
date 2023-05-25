
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
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.GeradorLinkEndereco;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@RestController
@RequestMapping("endereco")
public class EnderecoControle {
		
		@Autowired
		private EnderecoRepositorio enderecoRepositorio;
		
		@Autowired
		private GeradorLinkEndereco geradorLink;
		
		@PostMapping("/cadastrar")
		@ResponseStatus(HttpStatus.CREATED)
		public void CadastrarCliente(@RequestBody Endereco endereco) {
			enderecoRepositorio.save(endereco);
		}
		
		@GetMapping("/enderecos")
		public List<Endereco> Endereco(){
			return enderecoRepositorio.findAll();
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Endereco> BuscarEndereco(@PathVariable long id){
			List<Endereco>enderecos = enderecoRepositorio.findAll();
			Endereco endereco = enderecoRepositorio.getById(id);
			if(endereco == null){
				ResponseEntity<Endereco> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				return resposta;
			}
			else{
				geradorLink.adicionarLink(enderecos);
				geradorLink.adicionarLink(endereco);
				ResponseEntity<Endereco> resposta = new ResponseEntity<>(endereco, HttpStatus.FOUND);
				return resposta;
			}
		}
		
		@PutMapping("/atualizar/{id}")
		public ResponseEntity<Endereco> Atualizar(@PathVariable Long id, 
				@RequestBody Endereco endereco){
			if (!enderecoRepositorio.existsById(id)) {
				return ResponseEntity.notFound().build();
			}
			endereco.setId(id);
			endereco = enderecoRepositorio.save(endereco);
			return ResponseEntity.ok(endereco);
		}
		
		@DeleteMapping("/deletar/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public ResponseEntity<Void> Deletar(@PathVariable Long id){
			if(!enderecoRepositorio.existsById(id)) {
				return ResponseEntity.notFound().build();
			}
			enderecoRepositorio.deleteById(id);
			return ResponseEntity.noContent().build();
		}
}
