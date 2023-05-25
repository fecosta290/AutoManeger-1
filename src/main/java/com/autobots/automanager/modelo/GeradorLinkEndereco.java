package com.autobots.automanager.modelo;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import com.autobots.automanager.controles.EnderecoControle;
import com.autobots.automanager.entidades.Endereco;

@Component
public class GeradorLinkEndereco implements GeradorLink<Endereco> {
	
	@Override
	public void adicionarLink(List<Endereco> lista) {
		for (Endereco endereco : lista) {
			long id = endereco.getId();
			Link linkProprio = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(EnderecoControle.class)
							.BuscarEndereco(id))
					.withSelfRel();
			endereco.add(linkProprio);
		}
	}

	@Override
	public void adicionarLink(Endereco objeto) {
		Link linkEndereco = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EnderecoControle.class)
						.Endereco())
				.withRel("enderecos");
		objeto.add(linkEndereco);
		Link linkEnderecoAtualizar = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EnderecoControle.class)
						.Atualizar(objeto.getId(), objeto))
				.withRel("atualizar/{id}");
		objeto.add(linkEnderecoAtualizar);
		Link linkEnderecoDelete = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EnderecoControle.class)
						.Deletar(objeto.getId()))
				.withRel("deletar/{id}");
		objeto.add(linkEnderecoDelete);
	}
	
}
