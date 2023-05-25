package com.autobots.automanager.modelo;

import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import com.autobots.automanager.controles.DocumentoControle;
import com.autobots.automanager.entidades.Documento;

@Component
public class GeradorLinkDocumento implements GeradorLink<Documento> {
	
	@Override
	public void adicionarLink(List<Documento> lista) {
		for (Documento documento : lista) {
			long id = documento.getId();
			Link linkProprioDocumento = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(DocumentoControle.class)
							.BuscarDocumento(id))
					.withSelfRel();
			documento.add(linkProprioDocumento);
		}
	}

	@Override
	public void adicionarLink(Documento objeto) {
		Link linkDocumento = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(DocumentoControle.class)
						.Cliente())
				.withRel("documentos");
		objeto.add(linkDocumento);
		Link linkDocumentoAtualizar = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(DocumentoControle.class)
						.Atualizar(objeto.getId(), objeto))
				.withRel("atualizar/{id}");
		objeto.add(linkDocumentoAtualizar);
		Link linkDocumentoDeletar = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(DocumentoControle.class)
						.Deletar(objeto.getId()))
				.withRel("deletar/{id}");
		objeto.add(linkDocumentoDeletar);
	}	
}