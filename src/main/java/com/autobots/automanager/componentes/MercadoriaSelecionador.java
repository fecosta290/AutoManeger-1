package com.autobots.automanager.componentes;
import java.util.List;
import com.autobots.automanager.entitades.Mercadoria;
import org.springframework.stereotype.Component;




@Component
public class MercadoriaSelecionador implements Selecionador<Mercadoria, Long> {
	  
		@Override
		public Mercadoria selecionar(List<Mercadoria> mercadoria, Long id) {
			Mercadoria selecionado = null;
			for(Mercadoria selecionador: mercadoria) {
				if(selecionador.getId().longValue() == id.longValue()) {
					selecionado = selecionador;
					break;
				}
			}
			return selecionado;
		}
	}