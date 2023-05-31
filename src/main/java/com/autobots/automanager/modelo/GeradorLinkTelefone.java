package com.autobots.automanager.modelo;

import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import com.autobots.automanager.controles.TelefoneControle;
import com.autobots.automanager.entidades.Telefone;

@Component
public class GeradorLinkTelefone implements GeradorLink<Telefone> {
	
	@Override
	public void adicionarLink(List<Telefone> lista) {
		for (Telefone telefone : lista) {
			long id = telefone.getId();
			Link linkProprioTelefone = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(TelefoneControle.class)
							.BuscarTelefone(id))
					.withSelfRel();
			telefone.add(linkProprioTelefone);
		}
	}

	@Override
	public void adicionarLink(Telefone objeto) {
		Link linkTelefone = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelefoneControle.class)
						.Telefone())
				.withRel("telefone");
		objeto.add(linkTelefone);
		Link linkTelefoneAtualizar = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelefoneControle.class)
						.Atualizar(objeto.getId(), objeto))
				.withRel("atualizar/{id}");
		objeto.add(linkTelefoneAtualizar);
		Link linkTelefoneDeletar = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelefoneControle.class)
						.Deletar(objeto.getId()))
				.withRel("deletar/{id}");
		objeto.add(linkTelefoneDeletar);
	}	
}
