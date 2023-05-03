package com.autobots.automanager.controles;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
@RequestMapping("documento")
public class DocumentoControle {

	@Autowired
	private DocumentoRepositorio documentoRepositorio;
	
	@PostMapping("/inserir")
	public void CadastrarCliente(@RequestBody Documento documento) {
		documentoRepositorio.save(documento);
	}
	
	@GetMapping("/documentos")
	public List<Documento> Cliente(){
		return documentoRepositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Documento> BuscarDocumento(@PathVariable long id){
		return documentoRepositorio.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Documento> Atualizar(@PathVariable Long id, 
			@RequestBody Documento documento){
		if (!documentoRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		documento.setId(id);
		documento = documentoRepositorio.save(documento);
		return ResponseEntity.ok(documento);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> Deletar(@PathVariable Long id){
		if(!documentoRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		documentoRepositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

