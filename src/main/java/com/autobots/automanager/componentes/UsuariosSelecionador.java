package com.autobots.automanager.componentes;
import java.util.List;
import com.autobots.automanager.entitades.Usuario;
import org.springframework.stereotype.Component;




@Component
public class UsuariosSelecionador implements Selecionador<Usuario, Long> {
  
	@Override
	public Usuario selecionar(List<Usuario> usuario, Long id) {
		Usuario selecionado = null;
		for(Usuario selecionador: usuario) {
			if(selecionador.getId().longValue() == id.longValue()) {
				selecionado = selecionador;
				break;
			}
		}
		return selecionado;
	}
}
	
